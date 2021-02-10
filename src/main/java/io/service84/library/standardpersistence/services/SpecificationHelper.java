package io.service84.library.standardpersistence.services;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationHelper {
  public static <T> Specification<T> simpleFalse() {
    return (root, query, builder) -> builder.or();
  }

  public static <T> Specification<T> simpleTrue() {
    return (root, query, builder) -> builder.and();
  }
}
