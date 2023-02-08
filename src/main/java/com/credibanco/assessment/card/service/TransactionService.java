package com.credibanco.assessment.card.service;

import static com.credibanco.assessment.card.exceptions.AssessmentCardExceptions.validation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.constats.Status;
import com.credibanco.assessment.card.dto.AssessmentCardDTO;
import com.credibanco.assessment.card.dto.TransactionDTO;
import com.credibanco.assessment.card.dto.TrasactionResponse;
import com.credibanco.assessment.card.exceptions.ExceptionsCons;
import com.credibanco.assessment.card.jpa.JpaTrasaction;

import lombok.extern.log4j.Log4j2;

/** @author cdrincon */
@Log4j2
@Service
public class TransactionService {

  @Autowired private AssessmentCardService assessmentCardService;
  @Autowired private JpaTrasaction jpaTransaction;

  public TrasactionResponse createTransaction(String tId, TransactionDTO transactionDTO)
      throws Exception {
    log.info("[TransactionService] -> tId: {}", tId);
    TrasactionResponse responseDto = new TrasactionResponse();
    validation(transactionDTO);
    List<AssessmentCardDTO> response =
        assessmentCardService.getInformationCard(tId, transactionDTO.getNumberCard());
    if (!response.isEmpty()) {
      Optional<AssessmentCardDTO> consult =
          response
              .stream()
              .filter(x -> x.getStatus().equalsIgnoreCase(Status.ENROLADO))
              .findFirst();

      if (consult.isPresent()) {
        TransactionDTO consultExist =
            jpaTransaction.cancelTrasaction(tId, transactionDTO.getReferenceNumber());
        if (Objects.isNull(consultExist)) {
          transactionDTO.setCreateDate(new Date());
          transactionDTO.setStatus(Status.APROBADO);
          jpaTransaction.createTrasaction(tId, transactionDTO, consult.get());
          responseDto =
              message(
                  ExceptionsCons.CERO,
                  ExceptionsCons.SUCCESSFUL_PURCHASE,
                  Status.APROBADO,
                  transactionDTO.getReferenceNumber(),
                  responseDto);
        } else {
          responseDto =
              message(
                  ExceptionsCons.DOS,
                  ExceptionsCons.EXIST + transactionDTO.getReferenceNumber(),
                  Status.RECHAZADO,
                  transactionDTO.getReferenceNumber(),
                  responseDto);
        }

      } else {
        responseDto =
            message(
                ExceptionsCons.DOS,
                "Tarjeta no enrolada",
                Status.RECHAZADO,
                transactionDTO.getReferenceNumber(),
                responseDto);
      }
    } else {
      responseDto =
          message(
              ExceptionsCons.UNO,
              "Tarjeta no existe",
              Status.RECHAZADO,
              transactionDTO.getReferenceNumber(),
              responseDto);
    }
    return responseDto;
  }

  public TrasactionResponse cancelTransaction(
      String tId, String numberCard, String numberReference, String fullPurchase) {
    log.info("[TransactionService] -> tId: {}", tId);
    TrasactionResponse dto = new TrasactionResponse();
    List<AssessmentCardDTO> response = assessmentCardService.getInformationCard(tId, numberCard);
    if (!response.isEmpty()) {
      TransactionDTO old = jpaTransaction.cancelTrasaction(tId, numberReference);
      if (Objects.nonNull(old)
          && Objects.nonNull(old.getFullPurchase())
          && old.getFullPurchase().equalsIgnoreCase(fullPurchase)) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(old.getCreateDate());
        calendar.setTime(date);
        int difference = (calendar2.get(Calendar.MINUTE) - calendar.get(Calendar.MINUTE));
        if (calendar.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
            && calendar.get(Calendar.HOUR) == calendar2.get(Calendar.HOUR)
            && calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
            && difference <= 5) {
          old.setStatus(Status.CANCELADO);
          jpaTransaction.putTrasaction(tId, old);
          dto = message(ExceptionsCons.CERO, "Compra anulada", Status.EXIT, numberReference, dto);
        } else {
          dto =
              message(
                  ExceptionsCons.DOS,
                  "No se puede anular transaccion",
                  Status.RECHAZADO,
                  numberReference,
                  dto);
        }
      } else {
        dto =
            message(
                ExceptionsCons.UNO,
                ExceptionsCons.REFERENCE_INVALID,
                Status.RECHAZADO,
                numberReference,
                dto);
      }
    } else {
      dto =
          message(
              ExceptionsCons.UNO,
              ExceptionsCons.CARD_NOT_EXIST,
              Status.RECHAZADO,
              numberReference,
              dto);
    }
    return dto;
  }

  private TrasactionResponse message(
      String code,
      String message,
      String status,
      String referenceNumber,
      TrasactionResponse responseDto) {
    return responseDto
        .builder()
        .code(code)
        .message(message)
        .numberReference(referenceNumber)
        .status(status)
        .build();
  }

  public List<TransactionDTO> allTransaction(String tId) {
    log.info("[TransactionService] -> tId: {}", tId);
    return jpaTransaction.allTransactions();
  }

  public List<TransactionDTO> oneTransaction(String tId, int id, String numberCard) {
    log.info("[TransactionService] -> tId: {}", tId);
    return jpaTransaction.oneTransactions(tId, id, numberCard);
  }
}
