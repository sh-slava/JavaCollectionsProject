package com.collection;

import java.util.Map;

public class Result {

  private final String input;
  private final Map<Character, Long> numberOfChars;

  public Result(String input, Map<Character, Long> numberOfChars) {
    this.input = input;
    this.numberOfChars = numberOfChars;
  }

  public String getInput() {
    return input;
  }

  public Map<Character, Long> getNumberOfChars() {
    return numberOfChars;
  }
}
