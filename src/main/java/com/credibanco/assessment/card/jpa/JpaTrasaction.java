package com.credibanco.assessment.card.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.dto.AssessmentCardDTO;
import com.credibanco.assessment.card.dto.TransactionDTO;
import com.credibanco.assessment.card.entity.TransactionEntity;
import com.credibanco.assessment.card.model.TrannsactionReposotiry;
import com.credibanco.assessment.card.translate.Translate;

import lombok.extern.log4j.Log4j2;

/** @author cdrincon */
@Log4j2
@Service
public class JpaTrasaction implements IJpaTrasaction {

  @Autowired private TrannsactionReposotiry jpaTransactionRepository;

  @Autowired
  @Qualifier("transactionDtoToEntityTranslate")
  private Translate<TransactionDTO, TransactionEntity> transactionDtoToEntityTranslate;

  @Autowired
  @Qualifier("transactionSaveResponseTranslate")
  private Translate<TransactionEntity, TransactionDTO> transactionSaveResponseTranslate;

  @Override
  public TransactionDTO createTrasaction(
      String tId, TransactionDTO transactionDTO, AssessmentCardDTO assessmentCardDTO) {
    log.info("[TransactionService] -> tId: {}", tId);
    transactionDTO.setCardId(assessmentCardDTO.getId());
    return transactionSaveResponseTranslate.translate(
        jpaTransactionRepository.save(transactionDtoToEntityTranslate.translate(transactionDTO)));
  }

  @Override
  public TransactionDTO cancelTrasaction(String tId, String numberReference) {
    log.info("[TransactionService] -> tId: {}", tId);
    TransactionEntity response = jpaTransactionRepository.findByReferenceNumber(numberReference);
    if (Objects.nonNull(response)) {
      return transactionSaveResponseTranslate.translate(response);
    }
    return null;
  }

  public void putTrasaction(String tId, TransactionDTO old) {
    log.info("[TransactionService] -> tId: {}", tId);
    jpaTransactionRepository.save(transactionDtoToEntityTranslate.translate(old));
  }

  @Override
  public List<TransactionDTO> allTransactions() {
    List<TransactionDTO> response = new ArrayList<>();
    List<TransactionEntity> transactionResponse =
        (List<TransactionEntity>) jpaTransactionRepository.findAll();
    if (!transactionResponse.isEmpty()) {
      for (TransactionEntity transactionEntity : transactionResponse) {
        response.add(transactionSaveResponseTranslate.translate(transactionEntity));
      }
    }
    return response;
  }

  @Override
  public List<TransactionDTO> oneTransactions(String tId, int id, String numberCard) {
    log.info("[TransactionService] -> tId: {}", tId);
    List<TransactionDTO> response = new ArrayList<>();
    List<TransactionEntity> cosunlt = jpaTransactionRepository.findByCardId(id);
    if (!cosunlt.isEmpty()) {
      for (TransactionEntity transactionEntity : cosunlt) {
        response.add(transactionSaveResponseTranslate.translate(transactionEntity));
      }
    }
    return response;
  }
}
