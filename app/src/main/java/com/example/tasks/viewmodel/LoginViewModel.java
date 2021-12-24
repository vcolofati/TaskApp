package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tasks.entities.Account;
import com.example.tasks.entities.Response;
import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.repository.AccountRepository;

public class LoginViewModel extends AndroidViewModel {

    private final AccountRepository mRepository;

    private MutableLiveData<Response> mLoginResponse = new MutableLiveData<>();
    public LiveData<Response> loginResponse = this.mLoginResponse;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AccountRepository(application);
    }

    public void login(String email, String password) {
        this.mRepository.login(email, password, new APIListener<Account>() {
            @Override
            public void onSuccess(Account result) {
                // Salvar dados login
                mRepository.saveUserData(result);

                // Informo resposta para activity
                mLoginResponse.setValue(new Response());
            }

            @Override
            public void onFailure(String message) {
                mLoginResponse.setValue(new Response(message));
            }
        });
    }
}
