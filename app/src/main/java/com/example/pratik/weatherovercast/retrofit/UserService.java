package com.example.pratik.weatherovercast.retrofit;

import com.example.pratik.weatherovercast.model.ResObj;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pratik on 2/8/2018.
 */

public interface UserService
{
    @GET("weather?APPID=5ba0930c707828ea8bb43acf7a93a029&")
    Call<ResObj> login(@Query("q") String cityname);
}