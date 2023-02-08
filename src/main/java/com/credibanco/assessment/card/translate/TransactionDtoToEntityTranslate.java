package com.credibanco.assessment.card.translate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.credibanco.assessment.card.dto.TransactionDTO;
import com.credibanco.assessment.card.entity.TransactionEntity;

/** @author cdrincon */
@Component
@Qualifier("transactionDtoToEntityTranslate")
public class TransactionDtoToEntityTranslate
    implements Translate<TransactionDTO, TransactionEntity> {

  @Override
  public TransactionEntity translate(TransactionDTO input) {
    return TransactionEntity.builder()
        .fullPurchase(input.getFullPurchase())
        .purchaseAddress(input.getPurchaseAddress())
        .referenceNumber(input.getReferenceNumber())
        .trasactionId(input.getId())
        .cardId(input.getCardId())
        .creationDate(input.getCreateDate())
        .status(input.getStatus())
        .build();
  }
}
