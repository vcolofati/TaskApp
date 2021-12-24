package com.example.tasks.entities;

public class Response {
    private final boolean success;
    private final String message;

    public Response() {
        this.success = true;
        this.message = null;
    }

    public Response(String message) {
        this.success = false;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return  this.message;
    }
}
