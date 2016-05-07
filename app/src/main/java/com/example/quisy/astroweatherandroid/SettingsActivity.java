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
    private Settings _settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _settings = new Settings();

        btnSave = (Button) findViewById(R.id.btnSave);
        txtLatitude = (EditText) findViewById(R.id.txtLatitude);
        txtLatitude.setText(String.valueOf(_settings.Location.Latitude));
        txtLongitude = (EditText) findViewById(R.id.txtLongitude);
        txtLongitude.setText(String.valueOf(_settings.Location.Longitude));
        txtRefreshTime = (EditText) findViewById(R.id.txtRefreshTime);
        txtRefreshTime.setText(String.valueOf(_settings.Time.RefreshTime));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSettings();
            }
        });

    }

    private void SaveSettings()
    {
        _settings.Location.Longitude = Double.parseDouble(txtLongitude.getText().toString());
        _settings.Location.Latitude = Double.parseDouble(txtLatitude.getText().toString());
        _settings.Time.RefreshTime = Integer.parseInt(txtRefreshTime.getText().toString());

        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
