package com.example.tasks.service.repository.remote.services;

import com.example.tasks.entities.Priority;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PriorityService {

    @GET("Priority")
    Call<List<Priority>> all();
}
