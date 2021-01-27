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
    if (input == null || input.isEmpty()) {
      return new Result(input, Collections.emptyMap());
    }
    
    if (cache.contains(input)) {
      return new Result(input, cache.getCharsFromCache(input));
    }
    
    Map<Character, Long> numberOfUniqueChars = countUniqueChars(input);
    cache.putResultInCache(input, numberOfUniqueChars);
    return new Result(input, numberOfUniqueChars);
  }
  
  private Map<Character, Long> countUniqueChars(String input) {
    return input.chars().mapToObj(symbol -> (char) symbol)
        .collect(Collectors.groupingBy(symbol -> symbol, LinkedHashMap::new, Collectors.counting()));
  }
}
