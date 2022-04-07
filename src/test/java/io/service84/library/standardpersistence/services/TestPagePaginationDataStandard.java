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
