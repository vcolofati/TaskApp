package com.example.tasks.service.repository.remote;

import com.example.tasks.entities.Account;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AccountService {

    @FormUrlEncoded
    @POST("Authentication/Create")
    Call<Account> signup(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("Authentication/Login")
    Call<Account> login(
            @Field("email") String email,
            @Field("password") String password
    );
}
