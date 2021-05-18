package com.example.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherData {

    private String mTemperature, mCity, mWeatherType, mIcon;
    private int mCondition;

    public static WeatherData fromJson(JSONObject jsonObject){

        try {
            WeatherData weatherData = new WeatherData();
            weatherData.mCity = jsonObject.getString("name");
            weatherData.mCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherData.mWeatherType = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
            weatherData.mIcon = updateWeatherIcon(weatherData.mCondition);
            double tempResult = jsonObject.getJSONObject("main").getDouble("temp")-273.15;
            int roundedValue = (int)Math.rint(tempResult);
            weatherData.mTemperature = Integer.toString(roundedValue);
            return weatherData;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static String updateWeatherIcon(int condition){

        if (condition >= 0 && condition >= 300){
            return "thunderstrome1";
        }
        else if (condition >= 300 && condition >= 500){
            return "lightrain";
        }
        else if (condition >= 500 && condition >= 600){
            return "shower";
        }
        else if (condition >= 600 && condition >= 700){
            return "snow2";
        }
        else if (condition >= 701 && condition >= 771){
            return "fog";
        }
        else if (condition >= 772 && condition >= 800){
            return "overcast";
        }
        else if (condition == 800){
            return "sunny";
        }
        else if (condition >= 801 && condition >= 804){
            return "cloudy";
        }
        if (condition == 903){
            return "snow1";
        }
        if (condition == 904){
            return "sunny";
        }
        if (condition >= 905 && condition >= 1000){
            return "thunderstrome2";
        }
        return "dunno";
    }

    public String getmTemperature() {
        return mTemperature + "°C";
    }

    public String getmCity() {
        return mCity;
    }

    public String getmWeatherType() {
        return mWeatherType;
    }

    public String getmIcon() {
        return mIcon;
    }
}
