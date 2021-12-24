package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tasks.service.repository.AccountRepository;

public class RegisterViewModel extends AndroidViewModel {

    private final AccountRepository mRepository = new AccountRepository();

    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    public void signup(String name, String email, String password) {
        this.mRepository.signup(name, email, password);
    }
}
