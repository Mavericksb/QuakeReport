package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ROBERTO on 08/03/2017.
 */

public class Earthquake {

    private String magnitude = "";
    private String location = "";
    private String date = "";

    public Earthquake(String mag, String place, String time){
        magnitude = mag;
        location = place;
        date = time;
    }


    public String getMagnitude(){
        return magnitude;
    }

    public String getLocation(){
        return location;
    }

    public String getDate(){
        long dv = Long.valueOf(date);
        Date df = new java.util.Date(dv);
        date = new SimpleDateFormat("dd MMM, yyyy HH:mm").format(df);
        return date;
    }

}
