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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PaginationTranslatorTests {
  @TestConfiguration
  public static class Configuration {
    @Bean
    public PaginationTranslator getPaginationTranslator() {
      return new PaginationTranslator();
    }
  }

  // Test Subject
  @Autowired private PaginationTranslator paginationTranslator;

  @SuppressWarnings({"unchecked", "deprecation"})
  @Test
  public void getMetadataFirst() {
    Page<Object> page = mock(Page.class);
    String expectedIndex = paginationTranslator.translatePageIndex(0);
    String expectedNextIndex = paginationTranslator.translatePageIndex(1);
    when(page.getNumber()).thenReturn(0);
    when(page.getNumberOfElements()).thenReturn(100);
    when(page.getSize()).thenReturn(100);
    when(page.getTotalElements()).thenReturn(990l);
    when(page.getTotalPages()).thenReturn(10);
    when(page.isLast()).thenReturn(Boolean.FALSE);
    TestPaginationDataStandard paginationData =
        paginationTranslator.metadata(page, TestPaginationDataStandard.class);
    assertEquals(100, paginationData.count);
    assertEquals(expectedIndex, paginationData.index);
    assertEquals(expectedNextIndex, paginationData.nextIndex);
    assertEquals(990, paginationData.total);
  }

  @SuppressWarnings({"unchecked"})
  @Test
  public void getCursorMetadataFirst() {
    Page<Object> page = mock(Page.class);
    String expectedCursor = paginationTranslator.translateIndexToCursor(0);
    String expectedNextCursor = paginationTranslator.translateIndexToCursor(1);
    when(page.getNumber()).thenReturn(0);
    when(page.getNumberOfElements()).thenReturn(100);
    when(page.getSize()).thenReturn(100);
    when(page.getTotalElements()).thenReturn(990l);
    when(page.getTotalPages()).thenReturn(10);
    when(page.isLast()).thenReturn(Boolean.FALSE);
    TestCursorPaginationDataStandard paginationData =
        paginationTranslator.cursorMetadata(page, TestCursorPaginationDataStandard.class);
    assertEquals(100, paginationData.count);
    assertEquals(expectedCursor, paginationData.cursor);
    assertEquals(expectedNextCursor, paginationData.nextCursor);
    assertEquals(990, paginationData.total);
  }

  @SuppressWarnings({"unchecked"})
  @Test
  public void getIndexMetadataFirst() {
    Page<Object> page = mock(Page.class);
    Integer expectedIndex = paginationTranslator.translateIndexToPage(0);
    Integer expectedNextIndex = paginationTranslator.translateIndexToPage(1);
    when(page.getNumber()).thenReturn(0);
    when(page.getNumberOfElements()).thenReturn(100);
    when(page.getSize()).thenReturn(100);
    when(page.getTotalElements()).thenReturn(990l);
    when(page.getTotalPages()).thenReturn(10);
    when(page.isLast()).thenReturn(Boolean.FALSE);
    TestPagePaginationDataStandard paginationData =
        paginationTranslator.pageMetadata(page, TestPagePaginationDataStandard.class);
    assertEquals(100, paginationData.count);
    assertEquals(expectedIndex, paginationData.index);
    assertEquals(expectedNextIndex, paginationData.nextIndex);
    assertEquals(990, paginationData.total);
  }

  @SuppressWarnings({"unchecked", "deprecation"})
  @Test
  public void getMetadataLastPage() {
    Page<Object> page = mock(Page.class);
    String expectedIndex = paginationTranslator.translatePageIndex(9);
    when(page.getNumber()).thenReturn(9);
    when(page.getNumberOfElements()).thenReturn(90);
    when(page.getSize()).thenReturn(100);
    when(page.getTotalElements()).thenReturn(990l);
    when(page.getTotalPages()).thenReturn(10);
    when(page.isLast()).thenReturn(Boolean.TRUE);
    TestPaginationDataStandard paginationData =
        paginationTranslator.metadata(page, TestPaginationDataStandard.class);
    assertEquals(90, paginationData.count);
    assertEquals(expectedIndex, paginationData.index);
    assertEquals(null, paginationData.nextIndex);
    assertEquals(990, paginationData.total);
  }

  @SuppressWarnings({"unchecked"})
  @Test
  public void getCursorMetadataLastPage() {
    Page<Object> page = mock(Page.class);
    String expectedCursor = paginationTranslator.translateIndexToCursor(9);
    when(page.getNumber()).thenReturn(9);
    when(page.getNumberOfElements()).thenReturn(90);
    when(page.getSize()).thenReturn(100);
    when(page.getTotalElements()).thenReturn(990l);
    when(page.getTotalPages()).thenReturn(10);
    when(page.isLast()).thenReturn(Boolean.TRUE);
    TestCursorPaginationDataStandard paginationData =
        paginationTranslator.cursorMetadata(page, TestCursorPaginationDataStandard.class);
    assertEquals(90, paginationData.count);
    assertEquals(expectedCursor, paginationData.cursor);
    assertEquals(null, paginationData.nextCursor);
    assertEquals(990, paginationData.total);
  }

  @SuppressWarnings({"unchecked"})
  @Test
  public void getPageMetadataLastPage() {
    Page<Object> page = mock(Page.class);
    Integer expectedIndex = paginationTranslator.translateIndexToPage(9);
    when(page.getNumber()).thenReturn(9);
    when(page.getNumberOfElements()).thenReturn(90);
    when(page.getSize()).thenReturn(100);
    when(page.getTotalElements()).thenReturn(990l);
    when(page.getTotalPages()).thenReturn(10);
    when(page.isLast()).thenReturn(Boolean.TRUE);
    TestPagePaginationDataStandard paginationData =
        paginationTranslator.pageMetadata(page, TestPagePaginationDataStandard.class);
    assertEquals(90, paginationData.count);
    assertEquals(expectedIndex, paginationData.index);
    assertEquals(null, paginationData.nextIndex);
    assertEquals(990, paginationData.total);
  }

  @SuppressWarnings({"deprecation"})
  @Test
  public void getPageableNullTest() {
    String cursor = null;
    Integer pageIndex = 0;
    Integer pageSize = 100;
    Pageable pageable = paginationTranslator.getPageable(cursor, pageSize);
    Integer gotPageNumber = pageable.getPageNumber();
    Integer gotPageSize = pageable.getPageSize();
    Long gotOffset = pageable.getOffset();
    assertEquals(pageIndex, gotPageNumber);
    assertEquals(pageSize, gotPageSize);
    assertEquals(pageIndex * pageSize, gotOffset);
  }

  @Test
  public void getCursorPageableNullTest() {
    String cursor = null;
    Integer pageIndex = 0;
    Integer pageSize = 100;
    Pageable pageable = paginationTranslator.getCursorPageable(cursor, pageSize);
    Integer gotPageNumber = pageable.getPageNumber();
    Integer gotPageSize = pageable.getPageSize();
    Long gotOffset = pageable.getOffset();
    assertEquals(pageIndex, gotPageNumber);
    assertEquals(pageSize, gotPageSize);
    assertEquals(pageIndex * pageSize, gotOffset);
  }

  @Test
  public void getPagePageableNullTest() {
    Integer page = null;
    Integer pageIndex = 0;
    Integer pageSize = 100;
    Pageable pageable = paginationTranslator.getPagePageable(page, pageSize);
    Integer gotPageNumber = pageable.getPageNumber();
    Integer gotPageSize = pageable.getPageSize();
    Long gotOffset = pageable.getOffset();
    assertEquals(pageIndex, gotPageNumber);
    assertEquals(pageSize, gotPageSize);
    assertEquals(pageIndex * pageSize, gotOffset);
  }

  @SuppressWarnings({"deprecation"})
  @Test
  public void getPageableTest() {
    Integer pageIndex = 5;
    Integer pageSize = 100;
    String stringPageIndex = paginationTranslator.translatePageIndex(pageIndex);
    Pageable pageable = paginationTranslator.getPageable(stringPageIndex, pageSize);
    Integer gotPageNumber = pageable.getPageNumber();
    Integer gotPageSize = pageable.getPageSize();
    Long gotOffset = pageable.getOffset();
    assertEquals(pageIndex, gotPageNumber);
    assertEquals(pageSize, gotPageSize);
    assertEquals(pageIndex * pageSize, gotOffset);
  }

  @Test
  public void getCursorPageableTest() {
    Integer pageIndex = 5;
    Integer pageSize = 100;
    String cursor = paginationTranslator.translateIndexToCursor(pageIndex);
    Pageable pageable = paginationTranslator.getCursorPageable(cursor, pageSize);
    Integer gotPageNumber = pageable.getPageNumber();
    Integer gotPageSize = pageable.getPageSize();
    Long gotOffset = pageable.getOffset();
    assertEquals(pageIndex, gotPageNumber);
    assertEquals(pageSize, gotPageSize);
    assertEquals(pageIndex * pageSize, gotOffset);
  }

  @Test
  public void getPagePageableTest() {
    Integer pageIndex = 5;
    Integer pageSize = 100;
    Integer page = paginationTranslator.translateIndexToPage(pageIndex);
    Pageable pageable = paginationTranslator.getPagePageable(page, pageSize);
    Integer gotPageNumber = pageable.getPageNumber();
    Integer gotPageSize = pageable.getPageSize();
    Long gotOffset = pageable.getOffset();
    assertEquals(pageIndex, gotPageNumber);
    assertEquals(pageSize, gotPageSize);
    assertEquals(pageIndex * pageSize, gotOffset);
  }

  @SuppressWarnings({"deprecation"})
  @Test
  public void translate100PageIndex() {
    Integer pageIndex = 100;
    String intermediatePageIndex = paginationTranslator.translatePageIndex(pageIndex);
    Integer translatedPageIndex = paginationTranslator.translatePageIndex(intermediatePageIndex);
    Assertions.assertEquals(pageIndex, translatedPageIndex);
  }

  @Test
  public void translate100Page() {
    Integer pageIndex = 100;
    Integer intermediate = paginationTranslator.translateIndexToPage(pageIndex);
    Integer translatedPageIndex = paginationTranslator.translatePageToIndex(intermediate);
    Assertions.assertEquals(pageIndex, translatedPageIndex);
  }

  @Test
  public void translate100Cursor() {
    Integer pageIndex = 100;
    String intermediate = paginationTranslator.translateIndexToCursor(pageIndex);
    Integer translatedPageIndex = paginationTranslator.translateCursorToIndex(intermediate);
    Assertions.assertEquals(pageIndex, translatedPageIndex);
  }

  @SuppressWarnings({"deprecation"})
  @Test
  public void translate1PageIndex() {
    Integer pageIndex = 1;
    String intermediatePageIndex = paginationTranslator.translatePageIndex(pageIndex);
    Integer translatedPageIndex = paginationTranslator.translatePageIndex(intermediatePageIndex);
    Assertions.assertEquals(pageIndex, translatedPageIndex);
  }

  @Test
  public void translate1Page() {
    Integer pageIndex = 1;
    Integer intermediate = paginationTranslator.translateIndexToPage(pageIndex);
    Integer translatedPageIndex = paginationTranslator.translatePageToIndex(intermediate);
    Assertions.assertEquals(pageIndex, translatedPageIndex);
  }

  @Test
  public void translate1Cursor() {
    Integer pageIndex = 1;
    String intermediate = paginationTranslator.translateIndexToCursor(pageIndex);
    Integer translatedPageIndex = paginationTranslator.translateCursorToIndex(intermediate);
    Assertions.assertEquals(pageIndex, translatedPageIndex);
  }

  @SuppressWarnings({"deprecation"})
  @Test
  public void translateNullPageIndex() {
    Integer pageIndex = null;
    String intermediatePageIndex = paginationTranslator.translatePageIndex(pageIndex);
    Integer translatedPageIndex = paginationTranslator.translatePageIndex(intermediatePageIndex);
    Assertions.assertEquals(pageIndex, translatedPageIndex);
  }

  @Test
  public void translateNullPage() {
    Integer pageIndex = null;
    Integer intermediate = paginationTranslator.translateIndexToPage(pageIndex);
    Integer translatedPageIndex = paginationTranslator.translatePageToIndex(intermediate);
    Assertions.assertEquals(pageIndex, translatedPageIndex);
  }

  @Test
  public void translateNullCursor() {
    Integer pageIndex = null;
    String intermediate = paginationTranslator.translateIndexToCursor(pageIndex);
    Integer translatedPageIndex = paginationTranslator.translateCursorToIndex(intermediate);
    Assertions.assertEquals(pageIndex, translatedPageIndex);
  }

  @Test
  public void translatorExists() {
    assertNotNull(paginationTranslator);
  }
}
