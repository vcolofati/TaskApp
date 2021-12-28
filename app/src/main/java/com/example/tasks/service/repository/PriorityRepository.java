package com.example.tasks.service.repository;

import android.content.Context;

import com.example.tasks.R;
import com.example.tasks.entities.Priority;
import com.example.tasks.service.constants.TaskConstants;
import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.repository.local.DAO.PriorityDAO;
import com.example.tasks.service.repository.local.TaskDatabase;
import com.example.tasks.service.repository.remote.RetrofitClient;
import com.example.tasks.service.repository.remote.services.PriorityService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PriorityRepository extends BaseRepository {
    private final PriorityService mService;
    private final PriorityDAO mPriorityDAO;

    public PriorityRepository(Context context) {
        super(context);
        this.mService = RetrofitClient.createService(PriorityService.class);
        this.mPriorityDAO = TaskDatabase.getDataBase(context).priorityDAO();
    }

    public void all(final APIListener<List<Priority>> listener) {
        Call<List<Priority>> call = this.mService.all();
        call.enqueue(new Callback<List<Priority>>() {
            @Override
            public void onResponse(Call<List<Priority>> call, Response<List<Priority>> response) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(handleFailure(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<List<Priority>> call, Throwable t) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED));
            }
        });
    }

    public List<Priority> getList() {
        return this.mPriorityDAO.getAll();
    }

    public void save(List<Priority> result) {
        this.mPriorityDAO.clear();
        this.mPriorityDAO.save(result);
    }

    public String getDescription(int id) {
        return this.mPriorityDAO.getDescription(id);
    }
}
