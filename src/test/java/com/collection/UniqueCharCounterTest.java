package com.collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class UniqueCharCounterTest {

  private UniqueCharCounter counter;
  private CacheMemory cacheMemory;

  @BeforeEach
  void init() {
    counter = new UniqueCharCounter();
    cacheMemory = mock(CacheMemory.class);
  }

  @ParameterizedTest
  @MethodSource("resultProvider")
  void getResultOfCounting_shouldReturnMap(String input, Map<Character, Long> expected) {

    Result result = counter.getResultOfCounting(input);
    Map<Character, Long> actual = result.getNumberOfChars();

    assertEquals(expected, actual);
  }
  
	private static Object[][] resultProvider() {
		return new Object[][] { 
			{ "abcde", Map.of('a', 1L, 'b', 1L, 'c', 1L, 'd', 1L, 'e', 1L) },
			{ "4**2!+*!", Map.of('4', 1L, '*', 3L, '2', 1L, '!', 2L, '+', 1L) },
			{ "aRtAWwW", Map.of('a', 1L, 'R', 1L, 't', 1L, 'A', 1L, 'W', 2L, 'w', 1L) },
			{ "ab 1a@", Map.of('a', 2L, 'b', 1L, ' ', 1L, '1', 1L, '@', 1L) }, 
			{ "   ", Map.of(' ', 3L) }
			};
	}
  
  @Test
  void getResultOfCounting_shouldPutInCacheOnce() {  
    String input = "abc";
    Map<Character, Long> chars = new LinkedHashMap<>();
    chars.put('a', 1L);
    chars.put('b', 1L);
    chars.put('c', 1L);
    
    counter = new UniqueCharCounter(cacheMemory);
    when(cacheMemory.contains(input)).thenReturn(false);
    
    counter.getResultOfCounting(input);
    
    verify(cacheMemory).contains(input);
    verify(cacheMemory, times(1)).putResultInCache(input, chars);
    verifyNoMoreInteractions(cacheMemory);
  }
  
  @Test
  void getResultOfCounting_shouldGetFromCacheOnce() {
    String input = "abc"; 
    counter = new UniqueCharCounter(cacheMemory);
    when(cacheMemory.contains(input)).thenReturn(true);
    
    counter.getResultOfCounting(input);
    
    verify(cacheMemory).contains(input);
    verify(cacheMemory, times(1)).getCharsFromCache(input);
    verifyNoMoreInteractions(cacheMemory);
  }

  @Test
  void getResultOfCounting_shouldReturnEmptyMap_whenEmptyStringInput() {
    String input = "";
    Map<Character, Long> chars = counter.getResultOfCounting(input).getNumberOfChars();
    assertEquals(Collections.emptyMap(), chars);
  }
  
  @Test
  void getResultOfCounting_shouldReturnEmptyMap_whenNullInput() {
    String input = null;
    Result result = counter.getResultOfCounting(input);
    
    assertAll(
        () -> assertEquals(Collections.emptyMap(), result.getNumberOfChars()),
        () -> assertNull(result.getInput())
        );
  }
}
