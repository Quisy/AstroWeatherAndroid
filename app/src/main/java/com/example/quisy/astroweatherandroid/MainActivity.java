package com.example.quisy.astroweatherandroid;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.astrocalculator.AstroCalculator;
import com.example.quisy.astroweatherandroid.Models.Moon;
import com.example.quisy.astroweatherandroid.Models.Settings;
import com.example.quisy.astroweatherandroid.Models.SharedData;
import com.example.quisy.astroweatherandroid.Models.Sun;
import com.example.quisy.astroweatherandroid.Services.AstroWeatherService;
import com.example.quisy.astroweatherandroid.Services.LocationService;
import com.example.quisy.astroweatherandroid.Services.WeatherService;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private AstroWeatherService _astroWeatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        _astroWeatherService = new AstroWeatherService();

        LoadData();
        GetAstroData();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Handler h = new Handler();
        final int delay = Settings.Time.RefreshTime * 60000;




        if (delay > 0) {

            h.postDelayed(new Runnable() {
                public void run() {

                    GetAstroData();

                    h.postDelayed(this, delay);
                }
            }, delay);

        }



        GetWeatherData();

        //new Test().execute();

    }

    private void LoadData() {
        LocationService ls = new LocationService(getApplicationContext());
        ls.loadCurrentLocation();
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

    public void GetWeatherData()
    {
        Context context = getApplicationContext();
        LocationService ls = new LocationService(context);
        //context.deleteFile("locations.txt");
        //context.deleteFile("weather.txt");
        //context.deleteFile("location.txt");
        //ls.Add("Lodz");
        //ls.Add("Warszawa");
        WeatherService ws = new WeatherService(context);
        ws.getWeatherInfo(SharedData.currentLocation.getWoeid());
    }

    class Test extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri) {
            GetWeatherData();
            return "ss";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Do anything with response..
        }
    }

}
