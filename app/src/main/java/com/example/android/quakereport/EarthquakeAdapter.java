package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Created by ROBERTO on 08/03/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    private String primaryLocation = "";
    private String offSet = "";
    private static final String LOCATION_SEPARATOR = "of";


    public EarthquakeAdapter(Context context, int resource, ArrayList earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //getItem(position);
        Earthquake currentEarthquake = getItem(position);

        TextView magnitude = (TextView)listItemView.findViewById(R.id.magnitude_text);
        DecimalFormat formatter = new DecimalFormat("0.00");
        magnitude.setText(formatter.format(currentEarthquake.getMagnitude()));

        TextView offSet = (TextView)listItemView.findViewById(R.id.offset_text);
        offSet.setText(getOffset(currentEarthquake));

        TextView location = (TextView)listItemView.findViewById(R.id.location_text);
        location.setText(primaryLocation);


        TextView date = (TextView)listItemView.findViewById(R.id.date_text);
        date.setText(currentEarthquake.getDate());

        TextView time = (TextView)listItemView.findViewById(R.id.time_text);
        time.setText(currentEarthquake.getTime());


        return listItemView;

    }

    private String getOffset(Earthquake currentEarthquake){
         String currentLocation = currentEarthquake.getLocation();
        if(currentLocation.contains(LOCATION_SEPARATOR)){
            String[] splittedLocation = currentLocation.split(LOCATION_SEPARATOR);
            offSet = splittedLocation[0] + LOCATION_SEPARATOR;
            primaryLocation = splittedLocation[1];
        }
        else{
            offSet = getContext().getString(R.string.near_of);
            primaryLocation = currentLocation;
        }
     return offSet;
    }
}
