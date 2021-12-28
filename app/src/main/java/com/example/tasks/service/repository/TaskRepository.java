package com.example.tasks.service.repository;

import android.app.Application;

import com.example.tasks.R;
import com.example.tasks.entities.Task;
import com.example.tasks.service.constants.TaskConstants;
import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.repository.remote.RetrofitClient;
import com.example.tasks.service.repository.remote.services.TaskService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskRepository extends BaseRepository {

    private final TaskService mService;

    public TaskRepository(Application application) {
        super(application);
        this.mService = RetrofitClient.createService(TaskService.class);
    }

    public void save(Task task, final APIListener<Boolean> listener) {
        Call<Boolean> call = this.mService.create(
                task.getPriorityId(),
                task.getDescription(),
                task.getDueDate(),
                task.getComplete()
        );
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(handleFailure(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED));
            }
        });
    }

    private void list(Call<List<Task>> call, final APIListener<List<Task>> listener) {
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(handleFailure(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED));
            }
        });
    }

    public void getAll(final APIListener<List<Task>> listener) {
        Call<List<Task>> call = this.mService.getAll();
        this.list(call, listener);
    }

    public void nextWeek(final APIListener<List<Task>> listener) {
        Call<List<Task>> call = this.mService.nextWeek();
        this.list(call, listener);
    }

    public void overdue(final APIListener<List<Task>> listener) {
        Call<List<Task>> call = this.mService.overdue();
        this.list(call, listener);
    }

}
