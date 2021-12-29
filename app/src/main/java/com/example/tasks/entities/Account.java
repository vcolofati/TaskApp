package com.example.tasks.entities;

import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("token")
    private final String token;

    @SerializedName("personKey")
    private final String personKey;

    @SerializedName("name")
    private final String name;

    private String email;

    public Account(String token, String personKey, String name, String email) {
        this.token = token;
        this.personKey = personKey;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
