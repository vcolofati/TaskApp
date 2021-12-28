package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tasks.entities.Priority;
import com.example.tasks.entities.Response;
import com.example.tasks.entities.Task;
import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.repository.PriorityRepository;
import com.example.tasks.service.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private final PriorityRepository mPriorityRepository;
    private final TaskRepository mTaskRepository;

    private final MutableLiveData<Response> mTaskResponse = new MutableLiveData<>();
    public final LiveData<Response> taskResponse = this.mTaskResponse;

    private final MutableLiveData<Task> mTask = new MutableLiveData<>();
    public final LiveData<Task> task = this.mTask;

    private final MutableLiveData<List<Priority>> mListPriority = new MutableLiveData<>();
    public LiveData<List<Priority>> listPriority = this.mListPriority;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        this.mPriorityRepository = new PriorityRepository(application);
        this.mTaskRepository = new TaskRepository(application);
    }

    public void getList() {
        List<Priority> list = this.mPriorityRepository.getList();
        this.mListPriority.setValue(list);
    }

    public void save(Task task) {
        this.mTaskRepository.save(task, new APIListener<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                mTaskResponse.setValue(new Response());
            }

            @Override
            public void onFailure(String message) {
                mTaskResponse.setValue(new Response(message));
            }
        });
    }

    public void get(int id) {
        this.mTaskRepository.get(id, new APIListener<Task>() {
            @Override
            public void onSuccess(Task result) {
                mTask.setValue(result);
            }

            @Override
            public void onFailure(String message) {
                mTaskResponse.setValue(new Response(message));
            }
        });
    }
}
