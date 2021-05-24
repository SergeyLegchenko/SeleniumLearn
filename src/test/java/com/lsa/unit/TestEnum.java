package com.lsa.unit;

import lombok.Getter;

@Getter
public enum TestEnum {
    CASE_NO(4, "no"),
    CASE_HALF(15, "half"),
    CASE_YES(33, "yes");

    private int age;
    private String response;

    TestEnum(int age, String response) {
        this.age = age;
        this.response = response;
    }
}
