package com.credibanco.assessment.card.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.credibanco.assessment.card.entity.TransactionEntity;

/** @author cdrincon */
@Repository
public interface TrannsactionReposotiry extends CrudRepository<TransactionEntity, Integer> {

  TransactionEntity findByReferenceNumber(String numberReference);

  List<TransactionEntity> findByCardId(int id);
}
