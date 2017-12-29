package com.mylab.cromero.springdataredis.domain;

import org.springframework.data.redis.core.index.Indexed;

public class Phone {

    @Indexed
    private String number;
    private String description;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
