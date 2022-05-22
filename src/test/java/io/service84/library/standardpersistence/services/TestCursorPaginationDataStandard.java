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

import io.service84.library.standardpersistence.services.PaginationTranslator.CursorPaginationDataStandard;

public class TestCursorPaginationDataStandard implements CursorPaginationDataStandard {
  public String cursor;
  public String nextCursor;
  public Integer count;
  public Integer total;

  @Override
  public void setCursor(String cursor) {
    this.cursor = cursor;
  }

  @Override
  public void setNextCursor(String nextCursor) {
    this.nextCursor = nextCursor;
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
