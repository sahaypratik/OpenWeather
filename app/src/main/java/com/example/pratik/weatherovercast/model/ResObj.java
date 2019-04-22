package com.example.pratik.weatherovercast.model;

/**
 * Created by Pratik on 2/8/2018.
 */

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

@Entity(tableName = "ResObj")
public class ResObj {
    @Override
    public String toString() {
        return
                coord + "\n" +
                        "weather=" + weather + "\n" +

                        main +
                        "visibility=" + visibility + "\n" +
                        "wind" + wind + "\n" +

                        sys +

                        "name='" + name;

    }


    @PrimaryKey(autoGenerate = true)
   public int auto_id;

    @TypeConverters(CoordDataConverter.class)
    @SerializedName("coord")
    @Expose
    private Coord coord;

    @TypeConverters(WeatherDataConverter.class)
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;


    @SerializedName("base")
    @Expose
    private String base;

    @TypeConverters(MainDataConverter.class)
    @SerializedName("main")
    @Expose
    private Main main;

    @SerializedName("visibility")
    @Expose
    private int visibility;

    @TypeConverters(WindDataConverter.class)
    @SerializedName("wind")
    @Expose
    private Wind wind;


    @TypeConverters(DataConverter.class)
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;

    @SerializedName("dt")
    @Expose
    private int dt;

    @TypeConverters(SysDataConverter.class)
    @SerializedName("sys")
    @Expose
    private Sys sys;


    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("cod")
    @Expose
    private int cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }



}
