package com.credibanco.assessment.card.translate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.AssessmentCardDTO;
import com.credibanco.assessment.card.entity.AssessmentCardEntity;

/** @author cdrincon */
@Component
@Qualifier("assessmentSaveResponseTranslate")
public class AssessmentSaveResponseTranslate
    implements Translate<AssessmentCardEntity, AssessmentCardDTO> {

  @Override
  public AssessmentCardDTO translate(AssessmentCardEntity input) {
    return AssessmentCardDTO.builder()
        .createDate(input.getCreationDate())
        .identification(input.getIdentification())
        .modificationDate(input.getModificationDate())
        .numberCard(input.getNumberCard())
        .phone(input.getPhone())
        .title(input.getHeadline())
        .type(input.getType())
        .numberValidation(input.getNumberValidation())
        .status(input.getStatus())
        .id(input.getCardId())
        .build();
  }
}
