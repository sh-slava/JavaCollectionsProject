package com.collection;

import java.util.*;

public class CacheMemory {
  private Map<String, Map<Character, Long>> cache;

  public CacheMemory() {
    this.cache = new HashMap<>();
  }

  public void putResultInCache(String sentence, Map<Character, Long> uniqueChars) {
    cache.put(sentence, uniqueChars);
  }

  public Map<Character, Long> getCharsFromCache(String input) {
    if (cache.containsKey(input)) {
      return cache.get(input);
    }
    return Collections.EMPTY_MAP;
  }
  
  public boolean contains(String input) {
    return cache.containsKey(input);
  }

  public Map<String, Map<Character, Long>> getCache() {
    return new HashMap<>(cache);
  }
}
