package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tasks.entities.Account;
import com.example.tasks.entities.Priority;
import com.example.tasks.entities.Response;
import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.repository.AccountRepository;
import com.example.tasks.service.repository.PriorityRepository;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private final AccountRepository mAccountRepository;
    private final PriorityRepository mPriorityRepository;

    private final MutableLiveData<Response> mLoginResponse = new MutableLiveData<>();
    public final LiveData<Response> loginResponse = this.mLoginResponse;

    private final MutableLiveData<Boolean> mUserLogged = new MutableLiveData<>();
    public final LiveData<Boolean> userLogged = this.mUserLogged;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mAccountRepository = new AccountRepository(application);
        mPriorityRepository = new PriorityRepository(application);
    }

    public void login(String email, String password) {
        this.mAccountRepository.login(email, password, new APIListener<Account>() {
            @Override
            public void onSuccess(Account result) {
                // Salvar dados login
                mAccountRepository.saveUserData(result);

                // Informo resposta para activity
                mLoginResponse.setValue(new Response());
            }

            @Override
            public void onFailure(String message) {
                mLoginResponse.setValue(new Response(message));
            }
        });
    }

    public void IsUserLogged() {
        Account account = this.mAccountRepository.getUserData();

        boolean isLogged = !"".equals(account.getName());

        // Se usuário não está logado
        if (!isLogged) {
            this.mPriorityRepository.all(new APIListener<List<Priority>>() {
                @Override
                public void onSuccess(List<Priority> result) {
                    mPriorityRepository.save(result);
                }

                @Override
                public void onFailure(String message) {

                }
            });
        }
        this.mUserLogged.setValue(isLogged);
    }
}
