package com.credibanco.assessment.card.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
public class AssessmentCardDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;

  @NotEmpty
  @Size(min = 16, max = 19, message = "Debe ser minimo de 16 a 19")
  private String numberCard;

  @NotEmpty private String title;

  @NotEmpty
  @Size(min = 10, max = 15, message = "Debe ser minimo de 10 a 15")
  private String identification;

  private String type;

  @NotEmpty
  @Size(min = 10, max = 10, message = "Debe ser de 10")
  private String phone;

  private String status;
  private Integer numberValidation;
  private Date createDate;
  private Date modificationDate;
}
