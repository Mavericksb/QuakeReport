/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static EarthquakeAdapter mAdapter;
    private String earthquakeUrl = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=20";
    private final int EARTHQUAKE_LOADER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Prepare the loader.  Either re-connect with an existing one,
        // or start a new one.
        LoaderManager earthquakeLoader = getLoaderManager();
        earthquakeLoader.initLoader(EARTHQUAKE_LOADER, null, this);

        /*Start an AsynTask to collect earthquake events to put in an ArrayList.
        EarthquakeTask task = new EarthquakeTask();
        task.execute(earthquakeUrl); */

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new EarthquakeAdapter(this, 0, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        //On Item click to open Browser and navigate to earthquake Url for details on USGS website.
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //find the associate earthquake object associated with the given view.
                Earthquake currentEarthquake = (Earthquake)adapterView.getItemAtPosition(position);
                //retrieve url string
                String earthQuakeUrl = currentEarthquake.getUrl();
                //prepairing and starting an implicit intent to device browsers
                Intent browseDetails = new Intent(Intent.ACTION_VIEW);
                browseDetails.setData(Uri.parse(earthQuakeUrl));
                startActivity(browseDetails);
            }
        });

    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {

        Log.e(LOG_TAG, "Creating Earthquake Loader");

        return new EarthquakeLoader(this, earthquakeUrl);

    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (earthquakes != null && !earthquakes.isEmpty()) {
            Log.e(LOG_TAG, "OnLoadFinished submission!");
            mAdapter.addAll(earthquakes);
        }
    }


    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        mAdapter.clear();
    }

 /*   public class EarthquakeTask extends AsyncTask<String, Void, List<Earthquake>>{
        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Earthquake> earthquakes = QueryUtils.extractEarthquakes(urls[0]);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
                // Clear the adapter of previous earthquake data
            Log.e(LOG_TAG, "On Post Execute!");
            mAdapter.clear();

                // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
                // data set. This will trigger the ListView to update.
                if (earthquakes != null && !earthquakes.isEmpty()) {
                    Log.e(LOG_TAG, "On Post Execute submission!");
                    mAdapter.addAll(earthquakes);
                }
            }
        }*/


}

