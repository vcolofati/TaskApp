package com.example.tasks.service.repository;

import android.content.Context;

import com.example.tasks.R;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;

public class BaseRepository {

    protected final Context mContext;

    protected BaseRepository(Context context) {
        this.mContext = context;
    }

    public String handleFailure(ResponseBody responseBody) {
        try {
            return new Gson().fromJson(responseBody.string(), String.class);
        } catch (Exception e) {
            return mContext.getString(R.string.ERROR_UNEXPECTED);
        }
    }
}
