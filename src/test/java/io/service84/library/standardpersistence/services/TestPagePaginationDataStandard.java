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

import io.service84.library.standardpersistence.services.PaginationTranslator.PagePaginationDataStandard;

public class TestPagePaginationDataStandard implements PagePaginationDataStandard {
  public Integer index;
  public Integer nextIndex;
  public Integer count;
  public Integer total;

  @Override
  public void setPage(Integer index) {
    this.index = index;
  }

  @Override
  public void setNextPage(Integer nextIndex) {
    this.nextIndex = nextIndex;
  }

  @Override
  public void setCount(Integer count) {
    this.count = count;
  }

  @Override
  public void setTotal(Integer total) {
    this.total = total;
  }
}
