package com.example.pratik.weatherovercast.Util;

public class Utility
{
  public static String convertKtoC(float degree)
  {
      return String.format("%.2f",degree-273)+" C";
  }
}
