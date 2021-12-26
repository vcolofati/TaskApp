package com.example.tasks.entities;

import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("token")
    private final String token;

    @SerializedName("personKey")
    private final String personKey;

    @SerializedName("name")
    private final String name;

    public Account(String token, String personKey, String name) {
        this.token = token;
        this.personKey = personKey;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public String getPersonKey() {
        return personKey;
    }

    public String getName() {
        return name;
    }
}
