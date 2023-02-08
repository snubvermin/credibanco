package com.credibanco.assessment.card.service.impl;

import java.util.List;

import com.credibanco.assessment.card.dto.AssessmentCardDTO;
import com.credibanco.assessment.card.dto.CardResponseDTO;

/** @author cdrincon */
public interface AssessmentCardServiceImpl {

  public CardResponseDTO createCard(String tId, AssessmentCardDTO assessmentBodyDTO)
      throws Exception;

  public List<AssessmentCardDTO> getInformationCard(String tId, String numberCard);

  public CardResponseDTO updateInformationCard(String tId, String numberCard, int numberValidation)
      throws Exception;

  public CardResponseDTO deleteCard(String tId, String numberCard, int numberValidation);
}
