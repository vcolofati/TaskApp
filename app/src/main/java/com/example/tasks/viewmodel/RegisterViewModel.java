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

public class RegisterViewModel extends AndroidViewModel {

    private final AccountRepository mRepository;

    private final MutableLiveData<Response> mSignupResponse = new MutableLiveData<>();
    public final LiveData<Response> signResponse = this.mSignupResponse;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AccountRepository(application);
    }

    public void signup(String name, final String email, String password) {
        this.mRepository.signup(name, email, password, new APIListener<Account>() {
            @Override
            public void onSuccess(Account result) {
                result.setEmail(email);
                mRepository.saveUserData(result);
                mSignupResponse.setValue(new Response());
            }

            @Override
            public void onFailure(String message) {
                mSignupResponse.setValue(new Response(message));
            }
        });
    }
}
