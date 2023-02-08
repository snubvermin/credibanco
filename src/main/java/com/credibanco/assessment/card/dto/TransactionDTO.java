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
public class TransactionDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private int id;

  @NotEmpty
  @Size(min = 16, max = 19, message = "Debe ser minimo de 16 a 19")
  private String numberCard;

  @NotEmpty
  @Size(min = 6, max = 6, message = "Debe ser igual a 6 ")
  private String referenceNumber;

  private String fullPurchase;
  private String purchaseAddress;
  private int cardId;
  private Date createDate;
  private String status;
}
