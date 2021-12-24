package com.example.tasks.service.repository;

import com.example.tasks.entities.Account;
import com.example.tasks.service.repository.remote.AccountService;
import com.example.tasks.service.repository.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {

    private AccountService mService;

    public AccountRepository() {
        this.mService = RetrofitClient.createService(AccountService.class);
    }

    public void signup(String name, String email, String password) {
        Call<Account> call = this.mService.signup(name, email, password);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Account account = response.body();
                int code = response.code();
                String s = "";
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                String s = "";
            }
        });
    }
}
