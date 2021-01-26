package io.service84.library.standardpersistence.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PaginationTranslator {
  public static interface PaginationDataDTOStandard {
    void setCount(Integer count);

    void setIndex(String index);

    void setNextIndex(String nextIndex);

    void setTotal(Integer total);
  }

  protected Integer getNextPageIndex(Page<?> page) {
    if (page.isLast()) {
      return null;
    }

    return page.getNumber() + 1;
  }

  public Pageable getPageable(String pageIndex, Integer pageSize) {
    Integer pageNumber = ObjectUtils.firstNonNull(translatePageIndex(pageIndex), 0);
    return PageRequest.of(pageNumber, pageSize);
  }

  protected <T extends PaginationDataDTOStandard> T metadata(Page<?> page, Class<T> clazz) {
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
    if (pageIndex == null) {
      return null;
    }

    return Base64.getUrlEncoder().encodeToString(pageIndex.toString().getBytes());
  }

  public Integer translatePageIndex(String pageIndex) {
    if (pageIndex == null || pageIndex.trim().equals("")) {
      return null;
    }

    return Integer.valueOf(new String(Base64.getUrlDecoder().decode(pageIndex.getBytes())));
  }
}
