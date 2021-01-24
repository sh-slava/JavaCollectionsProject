package com.collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import java.lang.reflect.Field;
import java.util.*;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

class CounterTest {

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

    assertThat(actual, is(expected));
  }

  @Test
  void getResultOfCounting_shouldHaveSameSize() {
    Map<Character, Long> expected = new LinkedHashMap<>();
    expected.put('a', 1L);
    expected.put('b', 2L);
    expected.put('c', 4L);
    expected.put('d', 3L);
    expected.put('e', 5L);

    Result result = counter.getResultOfCounting("abbccccdddeeeee");
    Map<Character, Long> actual = result.getNumberOfChars();

    assertThat(actual.size(), is(5));
  }

  @Test
  void getResultOfCounting_shouldPutInCacheOnce_whenTheSameInput() throws NoSuchFieldException, IllegalAccessException {

    String input = "abc";

    counter.getResultOfCounting(input);
    counter.getResultOfCounting(input);
    counter.getResultOfCounting(input);

    Field privateField = UniqueCharCounter.class.getDeclaredField("cache");
    privateField.setAccessible(true);

    CacheMemory cache = (CacheMemory) privateField.get(counter);

    int expected = 1;
    int actual = cache.getCache().size();

    assertEquals(expected, actual);
  }

  @Test
  void getResultOfCounting_shouldInvokeCountUniqueCharsOnce_whenTheSameInput() {
    try (MockedStatic<CharCounter> mockedStatic = mockStatic(CharCounter.class)) {
      String input = "abc";
      Map<Character, Long> map = new LinkedHashMap<>();
      map.put('a', 1L);
      map.put('b', 1L);
      map.put('c', 1L);

      mockedStatic.when(() -> CharCounter.countUniqueChars(input)).thenReturn(map).thenReturn(new LinkedHashMap<>());

      Result firstResult = counter.getResultOfCounting(input);
      Result secondResult = counter.getResultOfCounting(input);

      assertThat(firstResult.getNumberOfChars(), is(secondResult.getNumberOfChars()));
    }
  }

  @Test
  void getResultOfCounting_shouldThrowRuntimeException_whenEmptyStringInput() {
    String input = "";
    assertThrows(IllegalArgumentException.class, () -> counter.getResultOfCounting(input));
  }
}
