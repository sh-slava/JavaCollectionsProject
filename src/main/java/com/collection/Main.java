package com.collection;

public class Main {

  public static void main(String[] args) {

    String input = "hello world!";

    CacheMemory cacheMemory = new CacheMemory();
    UniqueCharCounter counter = new UniqueCharCounter(cacheMemory);
    Formatter formatter = new Formatter();
    Result result = counter.getResultOfCounting(input);

    System.out.println(formatter.format(result));
  }
}
