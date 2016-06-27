package com.example.quisy.astroweatherandroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.astrocalculator.AstroCalculator;
import com.example.quisy.astroweatherandroid.Models.Moon;
import com.example.quisy.astroweatherandroid.Models.Settings;
import com.example.quisy.astroweatherandroid.Models.Sun;


public class MainActivity extends AppCompatActivity {

    private AstroWeatherService _astroWeatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Handler h = new Handler();
        final int delay = Settings.Time.RefreshTime * 60000;

        _astroWeatherService = new AstroWeatherService();

        GetAstroData();

        if (delay > 0) {

            h.postDelayed(new Runnable() {
                public void run() {

                    GetAstroData();

                    h.postDelayed(this, delay);
                }
            }, delay);

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar wil
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void GetAstroData()
    {
        AstroCalculator.SunInfo sunInfo = _astroWeatherService.getSunInfo();
        AstroCalculator.MoonInfo moonInfo = _astroWeatherService.getMoonInfo();

        Sun.Sunrise = sunInfo.getSunrise().toString();
        Sun.SunriseAzimuth = String.valueOf(sunInfo.getAzimuthRise());
        Sun.Sunset = sunInfo.getSunset().toString();
        Sun.SunsetAzimuth = String.valueOf(sunInfo.getAzimuthSet());
        Sun.TwilightMorning = sunInfo.getTwilightMorning().toString();
        Sun.TwilightEvening = sunInfo.getTwilightEvening().toString();

        Moon.Moonrise = moonInfo.getMoonrise().toString();
        Moon.MoonSet = moonInfo.getMoonset().toString();
        Moon.NextFullMoon = moonInfo.getNextFullMoon().toString();
        Moon.NextNewMoon = moonInfo.getNextNewMoon().toString();
        Moon.Age = Double.toString(moonInfo.getAge());
        Moon.Illumination = Double.toString(moonInfo.getIllumination());

    }

}
