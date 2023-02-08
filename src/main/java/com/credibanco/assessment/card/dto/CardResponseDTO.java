package com.credibanco.assessment.card.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author cdrincon */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String message;
  private AssessmentCardDTO assessmentCard;
}
