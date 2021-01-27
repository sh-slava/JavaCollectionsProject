package com.collection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

class FormatterTest {

  private final String LINE_SEPARATOR = System.lineSeparator();
  private Formatter formatter = new Formatter();

  @Test
  void formatToPrinting_shouldPrint_whenSimpleString() {
    String input = "hello world!";
    Map<Character, Long> chars = new LinkedHashMap<>();
    chars.put('h', 1L);
    chars.put('e', 1L);
    chars.put('l', 3L);
    chars.put('o', 2L);
    chars.put(' ', 1L);
    chars.put('w', 1L);
    chars.put('r', 1L);
    chars.put('d', 1L);
    chars.put('!', 1L);
    Result result = new Result(input, chars);

    String expected = 
        "hello world!" + LINE_SEPARATOR 
        + "\"h\" - 1" + LINE_SEPARATOR 
        + "\"e\" - 1" + LINE_SEPARATOR
        + "\"l\" - 3" + LINE_SEPARATOR 
        + "\"o\" - 2" + LINE_SEPARATOR 
        + "\" \" - 1" + LINE_SEPARATOR 
        + "\"w\" - 1" + LINE_SEPARATOR 
        + "\"r\" - 1" + LINE_SEPARATOR 
        + "\"d\" - 1" + LINE_SEPARATOR 
        + "\"!\" - 1" + LINE_SEPARATOR;

    String actual = formatter.format(result);

    assertEquals(expected, actual);
  }

  @Test
  void formatToPrinting_shouldPrint_whenSpaces() {
    String input = "   ";
    Map<Character, Long> chars = new LinkedHashMap<>();
    chars.put(' ', 3L);
    Result result = new Result(input, chars);

    String expected = 
          "Only spaces were entered: " + LINE_SEPARATOR 
        + "\" \" - 3" + LINE_SEPARATOR;
    String actual = formatter.format(result);

    assertEquals(expected, actual);
  }

  @Test
  void formatToPrinting_shouldPrint_whenOnlySymbols() {
    String input = "34=/*$$";
    Map<Character, Long> chars = new LinkedHashMap<>();
    chars.put('3', 1L);
    chars.put('4', 1L);
    chars.put('=', 1L);
    chars.put('/', 1L);
    chars.put('*', 1L);
    chars.put('$', 2L);
    Result result = new Result(input, chars);

    String expected = 
          "34=/*$$" + LINE_SEPARATOR 
        + "\"3\" - 1" + LINE_SEPARATOR 
        + "\"4\" - 1" + LINE_SEPARATOR
        + "\"=\" - 1" + LINE_SEPARATOR 
        + "\"/\" - 1" + LINE_SEPARATOR 
        + "\"*\" - 1" + LINE_SEPARATOR 
        + "\"$\" - 2"+ LINE_SEPARATOR;
    String actual = formatter.format(result);

    assertEquals(expected, actual);
  }

  @Test
  void formatToPrinting_shouldPrintCapitalLetters() {
    String input = "TtRrDDT";
    Map<Character, Long> chars = new LinkedHashMap<>();
    chars.put('T', 2L);
    chars.put('t', 1L);
    chars.put('R', 1L);
    chars.put('r', 1L);
    chars.put('D', 2L);
    Result result = new Result(input, chars);

    String expected = 
          "TtRrDDT" + LINE_SEPARATOR 
        + "\"T\" - 2" + LINE_SEPARATOR 
        + "\"t\" - 1" + LINE_SEPARATOR
        + "\"R\" - 1" + LINE_SEPARATOR 
        + "\"r\" - 1" + LINE_SEPARATOR 
        + "\"D\" - 2" + LINE_SEPARATOR;
    String actual = formatter.format(result);

    assertEquals(expected, actual);
  }

  @Test
  void formatToPrinting_shouldThrowRuntimeException_whenEmptyStringInput() {
    String input = "";
    Map<Character, Long> chars = new LinkedHashMap<>();
    Result result = new Result(input, chars);
    assertThrows(RuntimeException.class, () -> formatter.format(result));
  }

  @Test
  void formatToPrinting_shouldThrowRuntimeException_whenEmptyMap() {
    String input = "ert";
    Map<Character, Long> chars = new LinkedHashMap<>();
    Result result = new Result(input, chars);
    assertThrows(RuntimeException.class, () -> formatter.format(result));
  }
}
