package com.collection;

import java.util.*;
import java.util.stream.Collectors;

public class CharCounter {

  private CharCounter() {

  }

  public static Map<Character, Long> countUniqueChars(String input) {
    Map<Character, Long> result;

    result = input.chars().mapToObj(symbol -> (char) symbol)
        .collect(Collectors.groupingBy(symbol -> symbol, LinkedHashMap::new, Collectors.counting()));
    return result;
  }
}
