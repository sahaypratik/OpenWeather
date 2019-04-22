package com.example.pratik.weatherovercast.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.example.pratik.weatherovercast.model.ResObj;

@Database(entities = {ResObj.class},version = 1,exportSchema = false)
public abstract class MyAppDatabase extends RoomDatabase
{
    public abstract MyDao myDao();
}
