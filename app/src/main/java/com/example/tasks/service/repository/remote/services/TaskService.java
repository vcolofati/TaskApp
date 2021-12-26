package com.example.tasks.service.repository.remote.services;

import com.example.tasks.entities.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TaskService {

    @GET("Task")
    Call<List<Task>> getAll();

    @GET("Task/Next7days")
    Call<List<Task>> nextWeek();

    @GET("Task/Overdue")
    Call<List<Task>> overdue();

    @GET("Task/{id}")
    Call<Task> get(@Path(value = "id", encoded = true) int id);

    @FormUrlEncoded
    @POST("Task")
    Call<Boolean> create(
            @Field("PriorityId") int priorityId,
            @Field("Description") String description,
            @Field("DueDate") String dueDate,
            @Field("Complete") boolean complete
    );

    @FormUrlEncoded
    @PUT("Task")
    Call<Boolean> update(
            @Field("id") int id,
            @Field("PriorityId") int priorityId,
            @Field("Description") String description,
            @Field("DueDate") String dueDate,
            @Field("Complete") boolean complete
    );

    @PATCH("Task/Complete")
    Call<Boolean> markAsRead(@Field("Id") int id);

    @PATCH("Task/Undo")
    Call<Boolean> undoRead(@Field("Id") int id);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Task", hasBody = true)
    Call<Boolean> delete(@Field("Id") int id);
}
