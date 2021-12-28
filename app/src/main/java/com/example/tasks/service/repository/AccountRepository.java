package com.example.tasks.service.repository;

import android.content.Context;

import com.example.tasks.R;
import com.example.tasks.entities.Account;
import com.example.tasks.service.constants.TaskConstants;
import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.repository.local.SecurityPreferences;
import com.example.tasks.service.repository.remote.services.AccountService;
import com.example.tasks.service.repository.remote.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository extends BaseRepository {

    private final AccountService mService;
    private final SecurityPreferences mSecurityPreferences;

    public AccountRepository(Context context) {
        super(context);
        this.mService = RetrofitClient.createService(AccountService.class);
        this.mSecurityPreferences = new SecurityPreferences(context);
    }

    public void signup(String name, String email, String password, final APIListener<Account> listener) {
        Call<Account> call = this.mService.signup(name, email, password);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(handleFailure(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED));
            }
        });
    }

    public void login(String email, String password, final APIListener<Account> listener) {
        Call<Account> call = this.mService.login(email, password);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(handleFailure(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED));
            }
        });
    }

    public void saveUserData(Account account) {
        this.mSecurityPreferences.storeString(TaskConstants.SHARED.TOKEN_KEY, account.getToken());
        this.mSecurityPreferences.storeString(TaskConstants.SHARED.PERSON_KEY, account.getPersonKey());
        this.mSecurityPreferences.storeString(TaskConstants.SHARED.PERSON_NAME, account.getName());
        RetrofitClient.saveHeaders(account.getToken(), account.getPersonKey());
    }

    public Account getUserData() {
        String token = this.mSecurityPreferences.getStoredString(TaskConstants.SHARED.TOKEN_KEY);
        String personKey = this.mSecurityPreferences.getStoredString(TaskConstants.SHARED.PERSON_KEY);
        String name = this.mSecurityPreferences.getStoredString(TaskConstants.SHARED.PERSON_NAME);
        RetrofitClient.saveHeaders(token,personKey);
        return new Account(token, personKey, name);
    }
}
