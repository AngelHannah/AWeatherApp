package com.example.aweatherapp.Domains;

public class Future {
    private String day;
    private String picturePath;
    private String status;
    private int highTemp;
    private int lowTemp;

    public Future(String day, String picturePath, String status, int highTemp, int lowTemp) {
        this.day = day;
        this.picturePath = picturePath;
        this.status = status;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(int highTemp) {
        this.highTemp = highTemp;
    }

    public int getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(int lowTemp) {
        this.lowTemp = lowTemp;
    }
}
