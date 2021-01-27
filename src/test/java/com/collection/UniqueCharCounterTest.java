package com.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;

class UniqueCharCounterTest {

  UniqueCharCounter counter;

  @BeforeEach
  void init() {
    counter = new UniqueCharCounter();
  }

  @Test
  void getResultOfCounting_shouldReturnMap() {
    Map<Character, Long> expected = new LinkedHashMap<>();
    expected.put('a', 1L);
    expected.put('b', 2L);
    expected.put('c', 4L);
    expected.put('d', 3L);
    expected.put('e', 5L);

    Result result = counter.getResultOfCounting("abbccccdddeeeee");
    Map<Character, Long> actual = result.getNumberOfChars();

    assertEquals(expected, actual);
  }

  @Test
  void getResultOfCounting_shouldHaveSameSize() {
    Result result = counter.getResultOfCounting("abbccccdddeeeee");
    Map<Character, Long> actual = result.getNumberOfChars();

    assertEquals(5, actual.size());
  }
  
  @Test
  void getResultOfCounting_shouldPutInCacheOnce_whenTheSameInput() {
    String input = "abc";
    Map<Character, Long> chars = new LinkedHashMap<>();
    chars.put('a', 1L);
    chars.put('b', 1L);
    chars.put('c', 1L);
    
    CacheMemory cacheMemoryMock = mock(CacheMemory.class);
    counter = new UniqueCharCounter(cacheMemoryMock);
    when(cacheMemoryMock.contains(input)).thenReturn(false).thenReturn(true).thenReturn(true);
    
    counter.getResultOfCounting(input);
    counter.getResultOfCounting(input);
    counter.getResultOfCounting(input);
    
    verify(cacheMemoryMock, times(1)).putResultInCache(input, chars);
    verify(cacheMemoryMock, times(2)).getCharsFromCache(input); 
  }

  @Test
  void getResultOfCounting_shouldReturnEmptyMap_whenEmptyStringInput() {
    String input = "";
    Map<Character, Long> chars = counter.getResultOfCounting(input).getNumberOfChars();
    assertEquals(Collections.emptyMap(), chars);
  }
}
