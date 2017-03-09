package com.example.android.quakereport;

/**
 * Created by ROBERTO on 08/03/2017.
 */

public class Earthquake {

    private double magnitude;
    private String location = "";
    private String date = "";

    public Earthquake(double mag, String place, String time){
        magnitude = mag;
        location = place;
        date = time;
    }


    public double getMagnitude(){
        return magnitude;
    }

    public String getLocation(){
        return location;
    }

    public String getDate(){
        return date;
    }

}
