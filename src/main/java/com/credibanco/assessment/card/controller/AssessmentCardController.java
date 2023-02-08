package com.credibanco.assessment.card.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.card.dto.AssessmentCardDTO;
import com.credibanco.assessment.card.dto.CardResponseDTO;
import com.credibanco.assessment.card.service.AssessmentCardService;

/** @author cdrincon */
@RestController
@CrossOrigin("*")
@RequestMapping("/card/v1.0")
public class AssessmentCardController {

  @Autowired private AssessmentCardService cardService;

  @PostMapping
  public ResponseEntity<CardResponseDTO> createCard(
      @RequestBody AssessmentCardDTO assessmentBodyDTO) throws Exception {
    String tId = UUID.randomUUID().toString();
    return new ResponseEntity<CardResponseDTO>(
        cardService.createCard(tId, assessmentBodyDTO), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<AssessmentCardDTO>> getInformationCard(
      @RequestParam(name = "numberCard", required = false) String numberCard) {
    String tId = UUID.randomUUID().toString();
    List<AssessmentCardDTO> response = cardService.getInformationCard(tId, numberCard);
    if (response.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(response);
  }

  @PutMapping("/enroll")
  public ResponseEntity<CardResponseDTO> updateInformation(
      @RequestParam(name = "numberCard", required = true) String numberCard,
      @RequestParam(name = "numberValidation", required = true) int numberValidation)
      throws Exception {
    String tId = UUID.randomUUID().toString();
    return new ResponseEntity<CardResponseDTO>(
        cardService.updateInformationCard(tId, numberCard, numberValidation), HttpStatus.CREATED);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<CardResponseDTO> deleteCard(
      @RequestParam(name = "numberCard", required = true) String numberCard,
      @RequestParam(name = "numberValidation", required = true) int numberValidation) {
    String tId = UUID.randomUUID().toString();
    return new ResponseEntity<CardResponseDTO>(
        cardService.deleteCard(tId, numberCard, numberValidation), HttpStatus.OK);
  }
}
