package com.example.quisy.astroweatherandroid;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quisy.astroweatherandroid.Models.Location;
import com.example.quisy.astroweatherandroid.Models.Settings;
import com.example.quisy.astroweatherandroid.Models.SharedData;
import com.example.quisy.astroweatherandroid.Services.LocationService;
import com.example.quisy.astroweatherandroid.Services.WeatherService;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button btnSave;
    private EditText txtLatitude, txtLongitude, txtRefreshTime;
    private Spinner spinner;
    private LocationService _locationService;
    private WeatherService _weatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        _weatherService = new WeatherService(getApplicationContext());
        _locationService = new LocationService(getApplicationContext());
        List<Location> locations = _locationService.getLocations();
        List<String> locationNames = new ArrayList<>();

        for(Location location : locations)
        {
            locationNames.add(location.getName());
        }

        spinner = (Spinner) findViewById(R.id.locations);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locationNames);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        Location currentLocation = SharedData.currentLocation;
        spinner.setSelection(locationNames.indexOf(currentLocation.getName()));

        btnSave = (Button) findViewById(R.id.btnSave);
        txtLatitude = (EditText) findViewById(R.id.txtLatitude);
        txtLatitude.setText(String.valueOf(Settings.Location.Latitude));
        txtLongitude = (EditText) findViewById(R.id.txtLongitude);
        txtLongitude.setText(String.valueOf(Settings.Location.Longitude));
        txtRefreshTime = (EditText) findViewById(R.id.txtRefreshTime);
        txtRefreshTime.setText(String.valueOf(Settings.Time.RefreshTime));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSettings();
            }
        });

    }

    private void SaveSettings()
    {
        _locationService = new LocationService(getApplicationContext());
        _weatherService = new WeatherService(getApplicationContext());
        List<Location> locations = _locationService.getLocations();
        _locationService.changeCurrentLocation(locations.get(spinner.getSelectedItemPosition()));
        _weatherService.downloadWeatherInfo(locations.get(spinner.getSelectedItemPosition()).getWoeid());

        Settings.Location.Longitude = Double.parseDouble(txtLongitude.getText().toString());
        Settings.Location.Latitude = Double.parseDouble(txtLatitude.getText().toString());
        Settings.Time.RefreshTime = Integer.parseInt(txtRefreshTime.getText().toString());

        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();


        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
