package com.example.tasks.service.repository.local.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tasks.entities.Priority;

import java.util.List;

@Dao
public interface PriorityDAO {

    @Insert
    void save(List<Priority> list);

    @Query("SELECT * FROM PRIORITY")
    List<Priority> getAll();

    @Query("SELECT DESCRIPTION FROM PRIORITY WHERE id = :id")
    String getDescription(int id);

    @Query("DELETE FROM PRIORITY")
    void clear();
}
