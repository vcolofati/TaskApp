package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tasks.entities.Account;
import com.example.tasks.service.repository.AccountRepository;

public class MainViewModel extends AndroidViewModel {

    private final AccountRepository mRepository;

    private final MutableLiveData<Account> mUserData = new MutableLiveData<>();
    public LiveData<Account> userData = this.mUserData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new AccountRepository(application);
    }

    public void logout() {
        this.mRepository.clearUserData();
    }

    public void loadUserData() {
        this.mUserData.setValue(this.mRepository.getUserData());
    }
}
