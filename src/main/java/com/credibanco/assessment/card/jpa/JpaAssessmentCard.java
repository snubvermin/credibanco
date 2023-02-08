package com.credibanco.assessment.card.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.card.dto.AssessmentCardDTO;
import com.credibanco.assessment.card.dto.CardResponseDTO;
import com.credibanco.assessment.card.entity.AssessmentCardEntity;
import com.credibanco.assessment.card.model.AssessmentCardRepository;
import com.credibanco.assessment.card.translate.Translate;

/** @author cdrincon */
@Service
public class JpaAssessmentCard implements IjpaAssessmentCard {

  @Autowired private AssessmentCardRepository repository;

  @Autowired
  @Qualifier("assessmentDtoToEntityTranslate")
  private Translate<AssessmentCardDTO, AssessmentCardEntity> assessmentDtoToEntityTranslate;

  @Autowired
  @Qualifier("assessmentSaveResponseTranslate")
  private Translate<AssessmentCardEntity, AssessmentCardDTO> assessmentSaveResponseTranslate;

  @Override
  public AssessmentCardDTO saveCard(String tId, AssessmentCardDTO assessmentBodyDTO) {
    return assessmentSaveResponseTranslate.translate(
        repository.save(assessmentDtoToEntityTranslate.translate(assessmentBodyDTO)));
  }

  @Override
  public List<AssessmentCardDTO> getCard(String tId, String numberCard) {
    List<AssessmentCardDTO> add = new ArrayList<>();
    Optional<AssessmentCardEntity> response = repository.findByNumberCard(numberCard);
    if (response.isPresent()) {
      response
          .stream()
          .forEach(
              x -> {
                add.add(assessmentSaveResponseTranslate.translate(x));
              });
    }
    return add;
  }

  @Override
  public CardResponseDTO deleteCard(String tId, String numberCard, int numberValidation) {
    CardResponseDTO card = new CardResponseDTO();
    AssessmentCardEntity response =
        repository.findByNumberCardAndNumberValidation(numberCard, numberValidation);
    if (Objects.nonNull(response)) {
      card.setCode("00");
      card.setMessage("Se ha eliminado la tarjeta");
      repository.delete(response);
    } else {
      card.setCode("01");
      card.setMessage("No se ha eliminado la tarjeta");
    }
    return card;
  }

  public List<AssessmentCardDTO> getCardAll() {
    List<AssessmentCardDTO> dto = new ArrayList<>();
    List<AssessmentCardEntity> response = (List<AssessmentCardEntity>) repository.findAll();
    if (!response.isEmpty()) {
      for (AssessmentCardEntity assessmentCardEntity : response) {
        dto.add(assessmentSaveResponseTranslate.translate(assessmentCardEntity));
      }
    }
    return dto;
  }
}
