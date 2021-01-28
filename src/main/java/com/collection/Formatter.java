package com.collection;

import java.util.Map;

public class Formatter {

  private final String LINE_SEPARATOR = System.lineSeparator();

  public String format(Result input) {
    StringBuilder result = new StringBuilder();
    String sentence = input.getInput();
    Map<Character, Long> chars = input.getNumberOfChars();

    validateInput(sentence, chars);

    if (sentence.trim().equals("")) {
      result.append("Only spaces were entered: ").append(LINE_SEPARATOR);
    } 
    else {
      result.append(sentence).append(LINE_SEPARATOR);
    }
    
    input.getNumberOfChars().forEach((key, value) -> result.append("\"").append(key).append("\"").append(" - ").append(value)
        .append(LINE_SEPARATOR));
    return result.toString();
  }

  private void validateInput(String sentence, Map<Character, Long> chars) {
    if (sentence.isEmpty() || chars.isEmpty()) {
      throw new IllegalArgumentException("Empty string!");
    }
  }
}
