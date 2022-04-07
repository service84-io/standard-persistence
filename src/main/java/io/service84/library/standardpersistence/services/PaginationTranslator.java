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

import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("8AB25BF4-A488-4BFA-90E2-0567CA2B862A")
public class PaginationTranslator {
  private static final Logger logger = LoggerFactory.getLogger(PaginationTranslator.class);

  /*
   * @deprecated Use CursorPaginationDataStandard or PagePaginationDataStandard instead
   */
  @Deprecated(since = "1.3.0")
  public static interface PaginationDataStandard {
    void setIndex(String index);

    void setNextIndex(String nextIndex);

    void setCount(Integer count);

    void setTotal(Integer total);
  }

  public static interface CursorPaginationDataStandard {
    void setCursor(String cursor);

    void setNextCursor(String nextCursor);

    void setCount(Integer count);

    void setTotal(Integer total);
  }

  public static interface PagePaginationDataStandard {
    void setPage(Integer page);

    void setNextPage(Integer nextPage);

    void setCount(Integer count);

    void setTotal(Integer total);
  }

  /*
   * @deprecated Use getNextIndex
   */
  @Deprecated(since = "1.3.0")
  protected Integer getNextPageIndex(Page<?> page) {
    logger.debug("getNextPageIndex");
    return getNextIndex(page);
  }

  protected Integer getNextIndex(Page<?> page) {
    logger.debug("getNextIndex");
    if (page.isLast()) {
      return null;
    }

    return page.getNumber() + 1;
  }

  /*
   * @deprecated Use getCursorPageable
   */
  @Deprecated(since = "1.3.0")
  public Pageable getPageable(String cursor, Integer pageSize) {
    logger.debug("getPageable");
    return getCursorPageable(cursor, pageSize);
  }

  public Pageable getCursorPageable(String cursor, Integer pageSize) {
    logger.debug("getCursorPageable");
    Integer pageIndex = ObjectUtils.firstNonNull(translateCursorToIndex(cursor), 0);
    return PageRequest.of(pageIndex, pageSize);
  }

  public Pageable getPagePageable(Integer page, Integer pageSize) {
    logger.debug("getPagePageable");
    Integer pageIndex = ObjectUtils.firstNonNull(translatePageToIndex(page), 0);
    return PageRequest.of(pageIndex, pageSize);
  }

  /*
   * @deprecated Use cursorMetadata or pageMetadata
   */
  @Deprecated(since = "1.3.0")
  protected <T extends PaginationDataStandard> T metadata(Page<?> page, Class<T> clazz) {
    logger.debug("metadata");
    try {
      T dto = clazz.getConstructor().newInstance();
      dto.setIndex(translatePageIndex(page.getNumber()));
      dto.setNextIndex(translatePageIndex(getNextPageIndex(page)));
      dto.setCount(page.getNumberOfElements());
      dto.setTotal((int) page.getTotalElements());
      return dto;
    } catch (InstantiationException
        | IllegalAccessException
        | IllegalArgumentException
        | InvocationTargetException
        | NoSuchMethodException
        | SecurityException e) {
      return null;
    }
  }

  protected <T extends CursorPaginationDataStandard> T cursorMetadata(
      Page<?> page, Class<T> clazz) {
    logger.debug("cursorMetadata");
    try {
      T dto = clazz.getConstructor().newInstance();
      dto.setCursor(translateIndexToCursor(page.getNumber()));
      dto.setNextCursor(translateIndexToCursor(getNextIndex(page)));
      dto.setCount(page.getNumberOfElements());
      dto.setTotal((int) page.getTotalElements());
      return dto;
    } catch (InstantiationException
        | IllegalAccessException
        | IllegalArgumentException
        | InvocationTargetException
        | NoSuchMethodException
        | SecurityException e) {
      return null;
    }
  }

  protected <T extends PagePaginationDataStandard> T pageMetadata(Page<?> page, Class<T> clazz) {
    logger.debug("pageMetadata");
    try {
      T dto = clazz.getConstructor().newInstance();
      dto.setPage(translateIndexToPage(page.getNumber()));
      dto.setNextPage(translateIndexToPage(getNextIndex(page)));
      dto.setCount(page.getNumberOfElements());
      dto.setTotal((int) page.getTotalElements());
      return dto;
    } catch (InstantiationException
        | IllegalAccessException
        | IllegalArgumentException
        | InvocationTargetException
        | NoSuchMethodException
        | SecurityException e) {
      return null;
    }
  }

  /*
   * @deprecated Use translateIndexToCursor
   */
  @Deprecated(since = "1.3.0")
  public String translatePageIndex(Integer index) {
    logger.debug("translatePageIndex");
    return translateIndexToCursor(index);
  }

  public String translateIndexToCursor(Integer index) {
    logger.debug("translateIndexToCursor");
    if ((index == null) || index < 0) {
      return null;
    }

    return Base64.getUrlEncoder().encodeToString(index.toString().getBytes());
  }

  public Integer translateIndexToPage(Integer index) {
    logger.debug("translateIndexToPage");
    if ((index == null) || index < 0) {
      return null;
    }

    return index + 1;
  }

  /*
   * @deprecated Use translateCursorToIndex
   */
  @Deprecated(since = "1.3.0")
  public Integer translatePageIndex(String cursor) {
    logger.debug("translatePageIndex");
    return translateCursorToIndex(cursor);
  }

  public Integer translateCursorToIndex(String cursor) {
    logger.debug("translateCursorToIndex");
    if ((cursor == null) || cursor.trim().equals("")) {
      return null;
    }

    return Integer.valueOf(new String(Base64.getUrlDecoder().decode(cursor.getBytes())));
  }

  public Integer translatePageToIndex(Integer page) {
    logger.debug("translatePageToIndex");
    if ((page == null) || page < 1) {
      return null;
    }

    return page - 1;
  }
}
