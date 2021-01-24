package com.collection;

import java.util.Map;

public class Result {

  private final String sentence;
  private final Map<Character, Long> numberOfChars;

  public Result(String sentence, Map<Character, Long> numberOfChars) {
    this.sentence = sentence;
    this.numberOfChars = numberOfChars;
  }

  public String getSentence() {
    return sentence;
  }

  public Map<Character, Long> getNumberOfChars() {
    return numberOfChars;
  }
}
