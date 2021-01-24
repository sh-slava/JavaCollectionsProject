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
    Map<Character, Long> result = new LinkedHashMap<>();
    if (cache.containsKey(input)) {
      result = cache.get(input);
    }
    return result;
  }

  public Map<String, Map<Character, Long>> getCache() {
    return new HashMap<>(cache);
  }
}
