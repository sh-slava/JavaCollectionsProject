package com.collection;

import java.util.*;

public class UniqueCharCounter {

  private CacheMemory cache = new CacheMemory();

  public Result getResultOfCounting(String input) {
    if (input.isEmpty()) {
      return new Result(input, Collections.EMPTY_MAP);
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
