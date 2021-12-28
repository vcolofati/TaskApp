package com.example.tasks.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

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

    public int getId() {
        return id;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean isComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
