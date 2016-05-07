package com.example.quisy.astroweatherandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    private Button btnSave;
    private EditText txtLatitude, txtLongitude, txtRefreshTime;
    private Settings.Time timeSettings;
    private Settings.Location locationSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timeSettings = new Settings.Time();
        locationSettings = new Settings.Location();

        btnSave = (Button) findViewById(R.id.btnSave);
        txtLatitude = (EditText) findViewById(R.id.txtLatitude);
        txtLatitude.setText(String.valueOf(locationSettings.Latitude));
        txtLongitude = (EditText) findViewById(R.id.txtLongitude);
        txtLongitude.setText(String.valueOf(locationSettings.Longitude));
        txtRefreshTime = (EditText) findViewById(R.id.txtRefreshTime);
        txtRefreshTime.setText(String.valueOf(timeSettings.RefrehTime));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSettings();
            }
        });

    }

    private void SaveSettings()
    {
        locationSettings.Longitude = Double.parseDouble(txtLongitude.getText().toString());
        locationSettings.Latitude = Double.parseDouble(txtLatitude.getText().toString());
        timeSettings.RefrehTime = Integer.parseInt(txtRefreshTime.getText().toString());

        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
