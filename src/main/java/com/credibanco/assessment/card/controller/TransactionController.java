package com.credibanco.assessment.card.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.card.dto.TransactionDTO;
import com.credibanco.assessment.card.dto.TrasactionResponse;
import com.credibanco.assessment.card.service.TransactionService;

/** @author cdrincon */
@RestController
@CrossOrigin("*")
@RequestMapping("/transaction/v1.0")
public class TransactionController {

  @Autowired private TransactionService transactionService;

  @PostMapping
  public ResponseEntity<TrasactionResponse> createCard(@RequestBody TransactionDTO transactionDTO)
      throws Exception {
    String tId = UUID.randomUUID().toString();
    return new ResponseEntity<TrasactionResponse>(
        transactionService.createTransaction(tId, transactionDTO), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<TrasactionResponse> cancelTrasaction(
      @RequestParam(name = "numberCard", required = true) String numberCard,
      @RequestParam(name = "numberReference", required = true) String numberReference,
      @RequestParam(name = "fullPurchase", required = true) String fullPurchase) {
    String tId = UUID.randomUUID().toString();
    return new ResponseEntity<TrasactionResponse>(
        transactionService.cancelTransaction(tId, numberCard, numberReference, fullPurchase),
        HttpStatus.OK);
  }

  @GetMapping("/allTransaction")
  public ResponseEntity<List<TransactionDTO>> cancelTrasaction() {
    String tId = UUID.randomUUID().toString();
    return new ResponseEntity<List<TransactionDTO>>(
        transactionService.allTransaction(tId), HttpStatus.OK);
  }

  @GetMapping("/oneTransaction/{numberCard}/{idCard}")
  public ResponseEntity<List<TransactionDTO>> oneTrasaction(
      @PathVariable(name = "numberCard") String numberCard, @PathVariable(name = "idCard") int id) {
    String tId = UUID.randomUUID().toString();
    return new ResponseEntity<List<TransactionDTO>>(
        transactionService.oneTransaction(tId, id, numberCard), HttpStatus.OK);
  }
}
