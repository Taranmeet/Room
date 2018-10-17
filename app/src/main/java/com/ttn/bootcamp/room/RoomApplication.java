package com.ttn.bootcamp.room;

import android.app.Application;
import android.arch.persistence.room.Room;
import com.ttn.bootcamp.room.database.AppDatabase;

public class RoomApplication extends Application {

    public static AppDatabase mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "database-name").build();
    }
}
