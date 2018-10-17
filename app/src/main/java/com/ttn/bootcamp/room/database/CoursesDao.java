package com.ttn.bootcamp.room.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CoursesDao {
    @Query("SELECT * from courses")
    List<Courses> getAll();

    @Insert
    void insertAll(Courses... courses);
}
