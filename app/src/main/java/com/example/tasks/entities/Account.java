package com.example.tasks.entities;

import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("token")
    private String token;

    @SerializedName("personKey")
    private String personKey;

    @SerializedName("name")
    private String name;
}
