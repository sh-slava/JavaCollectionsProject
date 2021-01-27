package com.collection;

import java.util.*;
import java.util.stream.Collectors;

public class UniqueCharCounter {

  private CacheMemory cache;
  
  public UniqueCharCounter() {
    this.cache = new CacheMemory();
  }
  
  public UniqueCharCounter(CacheMemory cache) {
    this.cache = cache;
  }

  public Result getResultOfCounting(String input) {
    if (input.isEmpty()) {
      return new Result(input, Collections.emptyMap());
    }
    
    Map<Character, Long> numberOfUniqueChars;
    if (cache.contains(input)) {
      numberOfUniqueChars = cache.getCharsFromCache(input);
      return new Result(input, numberOfUniqueChars);
    }
    
    numberOfUniqueChars = countUniqueChars(input);
    cache.putResultInCache(input, numberOfUniqueChars);
    return new Result(input, numberOfUniqueChars);
  }
  
  private Map<Character, Long> countUniqueChars(String input) {
    Map<Character, Long> result;

    result = input.chars().mapToObj(symbol -> (char) symbol)
        .collect(Collectors.groupingBy(symbol -> symbol, LinkedHashMap::new, Collectors.counting()));
    return result;
  }
}
