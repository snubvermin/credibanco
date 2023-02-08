package com.credibanco.assessment.card.translate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.TransactionDTO;
import com.credibanco.assessment.card.entity.TransactionEntity;

/** @author cdrincon */
@Component
@Qualifier("transactionSaveResponseTranslate")
public class TransactionSaveResponseTranslate
    implements Translate<TransactionEntity, TransactionDTO> {

  @Override
  public TransactionDTO translate(TransactionEntity input) {
    return TransactionDTO.builder()
        .fullPurchase(input.getFullPurchase())
        .id(input.getTrasactionId())
        .purchaseAddress(input.getPurchaseAddress())
        .referenceNumber(input.getReferenceNumber())
        .createDate(input.getCreationDate())
        .status(input.getStatus())
        .cardId(input.getCardId())
        .build();
  }
}
