package com.example.tasks.entities;

import com.google.gson.annotations.SerializedName;

public class Task {
    @SerializedName("Id")
    private int id;

    @SerializedName("PriorityId")
    private int priorityId;

    @SerializedName("Description")
    private String description;

    @SerializedName("DueDate")
    private String dueDate;

    @SerializedName("Complete")
    private Boolean complete;
}
