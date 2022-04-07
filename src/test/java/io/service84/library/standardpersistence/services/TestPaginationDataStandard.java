package io.service84.library.standardpersistence.services;

import io.service84.library.standardpersistence.services.PaginationTranslator.PaginationDataStandard;

@SuppressWarnings({"deprecation"})
public class TestPaginationDataStandard implements PaginationDataStandard {
  public String index;
  public String nextIndex;
  public Integer count;
  public Integer total;

  @Override
  public void setIndex(String index) {
    this.index = index;
  }

  @Override
  public void setNextIndex(String nextIndex) {
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
