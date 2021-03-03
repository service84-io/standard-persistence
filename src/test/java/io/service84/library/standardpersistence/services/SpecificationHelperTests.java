/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
