package com.collection;

import java.util.*;

public class CacheMemory {
  
  private Map<String, Map<Character, Long>> cache;

  public CacheMemory() {
    this.cache = new HashMap<>();
  }

  public void putResultInCache(String input, Map<Character, Long> uniqueChars) {
    cache.put(input, uniqueChars);
  }

  public Map<Character, Long> getCharsFromCache(String input) {   
    return cache.getOrDefault(input, Collections.emptyMap());
  }
  
  public boolean contains(String input) {
    return cache.containsKey(input);
  }
}
