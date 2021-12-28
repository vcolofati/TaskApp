package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tasks.entities.Response;
import com.example.tasks.entities.Task;
import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskListViewModel extends AndroidViewModel {

    private final TaskRepository mRepository;

    private final MutableLiveData<List<Task>> mList = new MutableLiveData<>();
    public LiveData<List<Task>> list = this.mList;

    private final MutableLiveData<Response> mResponse = new MutableLiveData<>();
    public LiveData<Response> response = this.mResponse;

    public TaskListViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new TaskRepository(application);
    }

    public void getAll() {
        this.mRepository.getAll(new APIListener<List<Task>>() {
            @Override
            public void onSuccess(List<Task> result) {
                mList.setValue(result);
            }

            @Override
            public void onFailure(String message) {
                mList.setValue(new ArrayList<Task>());
                mResponse.setValue(new Response(message));
            }
        });
    }
}