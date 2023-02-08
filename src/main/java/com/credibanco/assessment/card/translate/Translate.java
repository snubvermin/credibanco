package com.credibanco.assessment.card.translate;
/** @author cdrincon */
@FunctionalInterface
public interface Translate<z, x> {
  x translate(z input);
}
