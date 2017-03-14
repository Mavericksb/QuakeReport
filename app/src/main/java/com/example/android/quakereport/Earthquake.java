package com.example.android.quakereport;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ROBERTO on 08/03/2017.
 */

public class Earthquake {

    private Double magnitude;
    private String location = "";
    private Date wholetime; //Store full date and time not formatted
    private String earthQuakeUrl = "";


    public Earthquake(Double mag, String place, String time, String url){
        magnitude = mag;
        location = place;

        wholetime = new java.util.Date(Long.valueOf(time)); //Store full date and time not formatted
        //date = new SimpleDateFormat("dd MMM, yyyy HH:mm").format(df);
        earthQuakeUrl = url;
    }


    public Double getMagnitude(){
        return magnitude;
    }

    public String getDate(){
        return new SimpleDateFormat("dd MMM, yyyy").format(wholetime);
    }

    public String getTime(){
        return new SimpleDateFormat("HH:mm").format(wholetime);
    }

    public String getLocation(){
        return location;
    }

    public String getUrl(){
        return earthQuakeUrl;
    }



}


