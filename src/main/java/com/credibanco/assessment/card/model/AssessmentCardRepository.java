package com.credibanco.assessment.card.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.credibanco.assessment.card.entity.AssessmentCardEntity;

/** @author cdrincon */
@Repository
public interface AssessmentCardRepository extends CrudRepository<AssessmentCardEntity, Integer> {

  Optional<AssessmentCardEntity> findByNumberCard(String numberCard);

  AssessmentCardEntity findByNumberCardAndNumberValidation(String numberCard, int numberValidation);
}
