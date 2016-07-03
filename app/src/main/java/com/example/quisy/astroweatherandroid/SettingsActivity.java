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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quisy.astroweatherandroid.Models.Location;
import com.example.quisy.astroweatherandroid.Models.Settings;
import com.example.quisy.astroweatherandroid.Models.SharedData;
import com.example.quisy.astroweatherandroid.Models.Units;
import com.example.quisy.astroweatherandroid.Services.LocationService;
import com.example.quisy.astroweatherandroid.Services.WeatherService;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button btnSave, btnAddCity;
    private EditText txtLatitude, txtLongitude, txtRefreshTime, txtNewCity;
    private Spinner spinner;
    private LocationService _locationService;
    private WeatherService _weatherService;
    private RadioButton radioCelsius, radioKelvin, radioKmH, radioMS;
    private RadioGroup tempUnitsGroup, speedUnitsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        _weatherService = new WeatherService(getApplicationContext());

        tempUnitsGroup = (RadioGroup) findViewById(R.id.tempUnitsGroup);
        speedUnitsGroup = (RadioGroup) findViewById(R.id.speedUnitsGroup);

        radioCelsius = (RadioButton) findViewById(R.id.radioCelsius);
        radioKelvin = (RadioButton) findViewById(R.id.radioKelvin);
        radioKmH = (RadioButton) findViewById(R.id.radioKmH);
        radioMS = (RadioButton) findViewById(R.id.radioMS);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnAddCity = (Button) findViewById(R.id.btnAddCity);
        txtNewCity = (EditText) findViewById(R.id.txtNewCity);
        txtRefreshTime = (EditText) findViewById(R.id.txtRefreshTime);
        txtRefreshTime.setText(String.valueOf(Settings.Time.RefreshTime));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings();
            }
        });
        btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCity();
            }
        });

        getLocations();
        getUnits();

    }

    private void getUnits() {
        Units units = SharedData.units;

        switch(units.getTempUnit()){
            case CELSIUS:
                radioCelsius.setChecked(true);
                break;
            case KELVIN:
                radioKelvin.setChecked(true);
                break;
        }

        switch(units.getSpeedUnit()){
            case KM_PER_H:
                radioKmH.setChecked(true);
                break;
            case METERS_PER_SEC:
                radioMS.setChecked(true);
                break;
        }



    }

    private void addCity()
    {
        _locationService = new LocationService(getApplicationContext());
        Location location = _locationService.Add(txtNewCity.getText().toString());
        getLocations();
        txtNewCity.setText("");
        if(location != null)
            Toast.makeText(getApplicationContext(), "Added: " + location.getName(), Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "Location doesn't exist!", Toast.LENGTH_LONG).show();

    }


    private void saveSettings()
    {
        _locationService = new LocationService(getApplicationContext());
        _weatherService = new WeatherService(getApplicationContext());
        List<Location> locations = _locationService.getLocations();
        _locationService.changeCurrentLocation(locations.get(spinner.getSelectedItemPosition()));
        _weatherService.downloadWeatherInfo(locations.get(spinner.getSelectedItemPosition()).getWoeid());

        saveUnits();

        //Settings.Location.Longitude = Double.parseDouble(txtLongitude.getText().toString());
        //Settings.Location.Latitude = Double.parseDouble(txtLatitude.getText().toString());
        Settings.Time.RefreshTime = Integer.parseInt(txtRefreshTime.getText().toString());

        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void getLocations()
    {
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
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();


        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    public void saveUnits()
    {
        //        // get selected radio button from radioGroup
        int tempSelectedId = tempUnitsGroup.getCheckedRadioButtonId();
        int speedSelectedId = speedUnitsGroup.getCheckedRadioButtonId();

        switch(tempSelectedId){
            case R.id.radioCelsius:
                SharedData.units.setTempUnit(Units.Temperature.CELSIUS);
                break;
            case R.id.radioKelvin:
                SharedData.units.setTempUnit(Units.Temperature.KELVIN);
                break;
        }

        switch(speedSelectedId){
            case R.id.radioKmH:
                SharedData.units.setSpeedUnit(Units.Speed.KM_PER_H);
                break;
            case R.id.radioMS:
                SharedData.units.setSpeedUnit(Units.Speed.METERS_PER_SEC);
                break;
        }

        _weatherService.SaveUnits(SharedData.units);

    }


//    @Override
//    public void onClick(View v) {
//
//        // get selected radio button from radioGroup
//        int tempSelectedId = tempUnitsGroup.getCheckedRadioButtonId();
//        int speedSelectedId = speedUnitsGroup.getCheckedRadioButtonId();
//
//        // find the radiobutton by returned id
//        radioSexButton = (RadioButton) findViewById(selectedId);
//
//        Toast.makeText(MyAndroidAppActivity.this,
//                radioSexButton.getText(), Toast.LENGTH_SHORT).show();
//
//    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioCelsius:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radioKelvin:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radioKmH:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.radioMS:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }

}
