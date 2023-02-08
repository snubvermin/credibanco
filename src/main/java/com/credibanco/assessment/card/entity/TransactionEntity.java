package com.credibanco.assessment.card.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author cdrincon */
@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction", schema = "credibanCO")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class TransactionEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @Column(name = "transaction_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer trasactionId;

  @Column(name = "reference_number")
  private String referenceNumber;

  @Column(name = "full_purchase")
  private String fullPurchase;

  @Column(name = "purchase_address")
  private String purchaseAddress;

  @Column(name = "card_id")
  private Integer cardId;

  @Column(name = "creation_date")
  private Date creationDate;

  @Column(name = "status")
  private String status;
}
