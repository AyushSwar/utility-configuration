package com.example.common;

public interface Validator<T> {

    void validate(T request);
}
