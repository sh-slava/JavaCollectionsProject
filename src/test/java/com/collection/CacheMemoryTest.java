package com.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.*;

class CacheMemoryTest {
  
  private CacheMemory cacheMemory;
  
  @BeforeEach
  void init() {
    cacheMemory = new CacheMemory();
  }

  @Test
  void putResultInCache_shouldPutInCache() {

      String input = "abc";
      Map<Character, Long> expectedChars = new LinkedHashMap<>();
      expectedChars.put('a', 1L);
      expectedChars.put('b', 1L);
      expectedChars.put('c', 1L);

      cacheMemory.putResultInCache(input, expectedChars);
      Map<Character, Long> actual = cacheMemory.getCharsFromCache(input);

      assertEquals(expectedChars, actual);
  }

  @Test
  void getCharsFromCache_shouldReturnEmptyMap_whenCacheDoNotHaveMatchers() {
      String input = "abc";
      Map<Character, Long> actual = cacheMemory.getCharsFromCache(input);
      
      assertEquals(Collections.emptyMap(), actual);
  }
}
