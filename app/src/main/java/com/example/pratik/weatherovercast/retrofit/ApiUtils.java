package com.example.pratik.weatherovercast.retrofit;

/**
 * Created by Pratik on 2/8/2018.
 */

public class ApiUtils
{
    public static final String BASE_URL="http://api.openweathermap.org/data/2.5/";

    public static UserService getUserService()
    {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);

    }
}
