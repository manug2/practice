package com.manug2.practice;


public interface TextAnalyzer<T> {
    boolean parse(String input);
    T evaluate(String input);
    String respond(String input);
}
