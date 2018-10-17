package com.ttn.bootcamp.room.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    List<Student> getAll();

    @Query("SELECT * FROM student WHERE rollNumber IN (:roll)")
    List<Student> loadAllByIds(int[] roll);

    @Insert
    void insertAll(Student... students);

    @Delete
    void delete(Student student);
}