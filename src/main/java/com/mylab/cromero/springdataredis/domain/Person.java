package com.mylab.cromero.springdataredis.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

@RedisHash("persons")
public class Person {

    @Id
    private String id;
    @Indexed
    private String firstname;
    @Indexed
    private String lastname;

    List<Address> adress;

    @TimeToLive
    private Long expiration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Address> getAdress() {
        return adress;
    }

    public void setAdress(List<Address> adress) {
        this.adress = adress;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}
