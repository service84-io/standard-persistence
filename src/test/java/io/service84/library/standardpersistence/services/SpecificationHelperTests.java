package io.service84.library.standardpersistence.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SpecificationHelperTests {
  @Test
  public void simpleFalseExists() {
    Specification<Object> simpleFalse = SpecificationHelper.simpleFalse();
    assertNotNull(simpleFalse);
  }

  @Test
  public void simpleTrueExists() {
    Specification<Object> simpleTrue = SpecificationHelper.simpleTrue();
    assertNotNull(simpleTrue);
  }
}
