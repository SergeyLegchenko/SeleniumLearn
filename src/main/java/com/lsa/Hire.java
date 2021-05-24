package com.lsa;

public class Hire {
    public String isHired(int age) {
        if (age<14)
            return "no";
        else if (age<18)
            return "half";
        else
            return "yes";

    }
}
