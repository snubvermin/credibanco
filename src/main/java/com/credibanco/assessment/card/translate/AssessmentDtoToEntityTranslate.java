package com.credibanco.assessment.card.translate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.AssessmentCardDTO;
import com.credibanco.assessment.card.entity.AssessmentCardEntity;

/** @author cdrincon */
@Component
@Qualifier("assessmentDtoToEntityTranslate")
public class AssessmentDtoToEntityTranslate
    implements Translate<AssessmentCardDTO, AssessmentCardEntity> {

  @Override
  public AssessmentCardEntity translate(AssessmentCardDTO input) {
    return AssessmentCardEntity.builder()
        .creationDate(input.getCreateDate())
        .headline(input.getTitle())
        .identification(input.getIdentification())
        .modificationDate(input.getModificationDate())
        .numberCard(input.getNumberCard())
        .phone(input.getPhone())
        .numberValidation(input.getNumberValidation())
        .status(input.getStatus())
        .type(input.getType())
        .cardId(input.getId())
        .build();
  }
}
