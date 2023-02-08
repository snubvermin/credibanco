package com.credibanco.assessment.card.jpa;

import java.util.List;

import com.credibanco.assessment.card.dto.AssessmentCardDTO;
import com.credibanco.assessment.card.dto.TransactionDTO;

/** @author cdrincon */
public interface IJpaTrasaction {

  public TransactionDTO createTrasaction(
      String tId, TransactionDTO transactionDTO, AssessmentCardDTO assessmentCardDTO);

  public TransactionDTO cancelTrasaction(String tId, String numberReference);

  public List<TransactionDTO> allTransactions();

  public List<TransactionDTO> oneTransactions(String tId, int id, String numberCard);
}
