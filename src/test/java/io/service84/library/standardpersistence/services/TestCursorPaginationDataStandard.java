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
