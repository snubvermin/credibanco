package com.credibanco.assessment.card.exceptions;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/** @author cdrincon */
public class AssessmentCardExceptions {

  public static <T> void validation(T t) throws Exception {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validation = factory.getValidator();
    Set<ConstraintViolation<T>> validations = validation.validate(t);
    for (ConstraintViolation<T> constraintViolation : validations) {
      throw new Exception(constraintViolation.getMessage().toString());
    }
  }
}
