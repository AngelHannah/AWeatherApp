package com.example.aweatherapp.Domains;

public class Hourly {
    private String hour;
    private int temperature;
    private String picturePath;

    public Hourly(String hour, int temperature, String picturePath) {
        this.hour = hour;
        this.temperature = temperature;
        this.picturePath = picturePath;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
