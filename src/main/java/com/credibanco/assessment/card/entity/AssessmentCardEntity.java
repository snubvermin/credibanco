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
@Table(name = "assessment_card", schema = "credibanCO")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class AssessmentCardEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @Column(name = "card_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer cardId;

  @Column(name = "card_number")
  private String numberCard;

  @Column(name = "headline")
  private String headline;

  @Column(name = "identification")
  private String identification;

  @Column(name = "card_type")
  private String type;

  @Column(name = "phone")
  private String phone;

  @Column(name = "card_status")
  private String status;

  @Column(name = "card_number_validation")
  private Integer numberValidation;

  @Column(name = "creation_date")
  private Date creationDate;

  @Column(name = "modification_date")
  private Date modificationDate;
}
