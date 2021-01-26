package com.collection;

import java.util.Map;

public class UniqueCharCounter {

  private CacheMemory cache = new CacheMemory();

  public Result getResultOfCounting(String input) {
    if (input.isEmpty()) {
      throw new IllegalArgumentException("Empty string!");
    }
    
    Map<Character, Long> numberOfUniqueChars;
    if (cache.contains(input)) {
      numberOfUniqueChars = cache.getCharsFromCache(input);
      return new Result(input, numberOfUniqueChars);
    }
    
    numberOfUniqueChars = CharCounter.countUniqueChars(input);
    cache.putResultInCache(input, numberOfUniqueChars);
    return new Result(input, numberOfUniqueChars);
  }
}
