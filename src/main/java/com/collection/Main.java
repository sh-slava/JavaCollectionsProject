package com.collection;

public class Main {

  public static void main(String[] args) {

    String test = "hello world!";

    UniqueCharCounter counter = new UniqueCharCounter();
    Formatter formatter = new Formatter();
    Result result = counter.getResultOfCounting(test);

    System.out.println(formatter.format(result));
  }
}
