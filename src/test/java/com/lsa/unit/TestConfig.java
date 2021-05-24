package com.lsa.unit;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:prj.properties")
public interface TestConfig extends Config {
    @Key("hire.age")
    int age();

    @Key("hire.response")
    @DefaultValue("yes")
    String response();
}
