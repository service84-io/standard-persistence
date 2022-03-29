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

  public static interface PaginationDataStandard {
    void setCount(Integer count);

    void setIndex(String index);

    void setNextIndex(String nextIndex);

    void setTotal(Integer total);
  }

  protected Integer getNextPageIndex(Page<?> page) {
    logger.debug("getNextPageIndex");
    if (page.isLast()) {
      return null;
    }

    return page.getNumber() + 1;
  }

  public Pageable getPageable(String pageIndex, Integer pageSize) {
    logger.debug("getPageable");
    Integer pageNumber = ObjectUtils.firstNonNull(translatePageIndex(pageIndex), 0);
    return PageRequest.of(pageNumber, pageSize);
  }

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

  public String translatePageIndex(Integer pageIndex) {
    logger.debug("translatePageIndex");
    if (pageIndex == null) {
      return null;
    }

    return Base64.getUrlEncoder().encodeToString(pageIndex.toString().getBytes());
  }

  public Integer translatePageIndex(String pageIndex) {
    logger.debug("translatePageIndex");
    if ((pageIndex == null) || pageIndex.trim().equals("")) {
      return null;
    }

    return Integer.valueOf(new String(Base64.getUrlDecoder().decode(pageIndex.getBytes())));
  }
}
