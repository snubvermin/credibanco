package com.credibanco.assessment.card.jpa;

import java.util.List;

import com.credibanco.assessment.card.dto.AssessmentCardDTO;
import com.credibanco.assessment.card.dto.CardResponseDTO;

/** @author cdrincon */
public interface IjpaAssessmentCard {

  public AssessmentCardDTO saveCard(String tId, AssessmentCardDTO assessmentBodyDTO);

  public List<AssessmentCardDTO> getCard(String tId, String numberCard);

  public CardResponseDTO deleteCard(String tId, String numberCard, int numberValidation);
}
