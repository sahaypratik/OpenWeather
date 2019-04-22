package com.example.pratik.weatherovercast.model;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class WindDataConverter
{
    @TypeConverter()
    public String fromTechniquesItemList(Wind techniquesItems){
        if (techniquesItems == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>() {
        }.getType();
        String json = gson.toJson(techniquesItems, type);
        return json;
    }

    @TypeConverter()
    public Wind toTechniquesItemList(String techniquesItemString){
        if (techniquesItemString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>() {
        }.getType();
        Wind techniquesItemList = gson.fromJson(techniquesItemString, type);
        return techniquesItemList;
    }

}
