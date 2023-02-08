package com.credibanco.assessment.card.service;

import static com.credibanco.assessment.card.exceptions.AssessmentCardExceptions.validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.constats.Status;
import com.credibanco.assessment.card.dto.AssessmentCardDTO;
import com.credibanco.assessment.card.dto.CardResponseDTO;
import com.credibanco.assessment.card.exceptions.ExceptionsCons;
import com.credibanco.assessment.card.jpa.JpaAssessmentCard;
import com.credibanco.assessment.card.service.impl.AssessmentCardServiceImpl;

import lombok.extern.log4j.Log4j2;

/** @author cdrincon */
@Log4j2
@Service
public class AssessmentCardService implements AssessmentCardServiceImpl {

  @Autowired private JpaAssessmentCard jpaAssessmentCard;

  @Override
  public CardResponseDTO createCard(String tId, AssessmentCardDTO assessmentBodyDTO)
      throws Exception {
    log.info("[AssessmentCardService] -> tId: {}", tId);
    validation(assessmentBodyDTO);
    assessmentBodyDTO.setCreateDate(new Date());
    assessmentBodyDTO.setModificationDate(new Date());
    assessmentBodyDTO.setStatus("Creada");
    Random numberRandom = new Random();
    assessmentBodyDTO.setNumberValidation(numberRandom.nextInt(100 - 1 + 1) + 1);
    AssessmentCardDTO response = jpaAssessmentCard.saveCard(tId, assessmentBodyDTO);
    response.setNumberCard(enmascarar(response));
    return CardResponseDTO.builder()
        .code(ExceptionsCons.CERO)
        .message(Status.EXIT)
        .assessmentCard(response)
        .build();
  }

  private String enmascarar(AssessmentCardDTO response) {
    String enma = response.getNumberCard().replace("", ",");
    String[] enmas = enma.split(",");
    enmas[7] = "*";
    enmas[8] = "*";
    enmas[9] = "*";
    enmas[10] = "*";
    enmas[11] = "*";
    StringBuilder sb = new StringBuilder();
    for (String str : enmas) {
      sb.append(str).append(",");
      sb.substring(0, sb.length() - 1);
    }
    return sb.toString().replace(",", "");
  }

  @Override
  public List<AssessmentCardDTO> getInformationCard(String tId, String numberCard) {
    log.info("[AssessmentCardService] -> tId: {}", tId);
    List<AssessmentCardDTO> resnuevo = new ArrayList<>();
    if (Objects.nonNull(numberCard)) {
      List<AssessmentCardDTO> response = jpaAssessmentCard.getCard(tId, numberCard);
      forEnmas(resnuevo, response);

      return resnuevo;
    }
    List<AssessmentCardDTO> response = jpaAssessmentCard.getCardAll();
    forEnmas(resnuevo, response);
    return resnuevo;
  }

  private void forEnmas(List<AssessmentCardDTO> resnuevo, List<AssessmentCardDTO> response) {
    for (AssessmentCardDTO assessmentCardDTO : response) {
      assessmentCardDTO.setNumberCard(enmascarar(assessmentCardDTO));
      resnuevo.add(assessmentCardDTO);
    }
  }

  @Override
  public CardResponseDTO updateInformationCard(String tId, String numberCard, int numberValidation)
      throws Exception {
    log.info("[AssessmentCardService] -> tId: {}", tId);
    List<AssessmentCardDTO> consult = jpaAssessmentCard.getCard(tId, numberCard);
    if (!consult.isEmpty()) {
      Optional<AssessmentCardDTO> old =
          consult
              .stream()
              .filter(
                  x ->
                      x.getNumberValidation().equals(numberValidation)
                          && x.getStatus().equalsIgnoreCase("Creada"))
              .findFirst();
      if (!old.isEmpty()) {
        old.get().setStatus("Enrolada");
        return CardResponseDTO.builder()
            .code(ExceptionsCons.CERO)
            .message(Status.EXIT)
            .assessmentCard(old.get())
            .build();
      } else {
        return CardResponseDTO.builder()
            .code(ExceptionsCons.DOS)
            .message("Numero de validacion invalido")
            .build();
      }
    } else {
      return CardResponseDTO.builder()
          .code(ExceptionsCons.UNO)
          .message("Tarjeta no existe")
          .build();
    }
  }

  @Override
  public CardResponseDTO deleteCard(String tId, String numberCard, int numberValidation) {
    log.info("[AssessmentCardService] -> tId: {}", tId);
    return jpaAssessmentCard.deleteCard(tId, numberCard, numberValidation);
  }
}
