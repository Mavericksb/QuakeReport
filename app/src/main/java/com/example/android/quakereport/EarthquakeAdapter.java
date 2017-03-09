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

import java.util.ArrayList;


/**
 * Created by ROBERTO on 08/03/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

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
        magnitude.setText(Double.toString(currentEarthquake.getMagnitude()));

        TextView location = (TextView)listItemView.findViewById(R.id.location_text);
        location.setText(currentEarthquake.getLocation());

        TextView date = (TextView)listItemView.findViewById(R.id.date_text);
        date.setText(currentEarthquake.getDate());


        return listItemView;

    }
}
