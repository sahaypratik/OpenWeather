package com.example.pratik.weatherovercast.model;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class CoordDataConverter implements Serializable
{
    @TypeConverter()
    public String fromTechniquesItemList(Coord techniquesItems){
        if (techniquesItems == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>() {
        }.getType();
        String json = gson.toJson(techniquesItems, type);
        return json;
    }

    @TypeConverter()
    public Coord toTechniquesItemList(String techniquesItemString){
        if (techniquesItemString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>() {
        }.getType();
        Coord techniquesItemList = gson.fromJson(techniquesItemString, type);
        return techniquesItemList;
    }
}
