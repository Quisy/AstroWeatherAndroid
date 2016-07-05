package com.example.quisy.astroweatherandroid.Services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.quisy.astroweatherandroid.Models.Atmosphere;
import com.example.quisy.astroweatherandroid.Models.SharedData;
import com.example.quisy.astroweatherandroid.Models.Units;
import com.example.quisy.astroweatherandroid.Models.WeatherInfo;
import com.example.quisy.astroweatherandroid.Models.Wind;
import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;

/**
 * Created by Mariusz on 2016-07-02.
 */
public class WeatherService {

    final Gson gson = Converters.registerDateTime(new GsonBuilder()).create();
    private Context _context;

    public WeatherService(Context context) {
        _context = context;
    }

    public void downloadWeatherInfo(String woeid) {
        if (isConnected())
            getFromApi(woeid);
        else
            Toast.makeText(_context, "No internet connection!", Toast.LENGTH_LONG).show();
    }

    public void getWeatherInfo(String woeid) {
        WeatherInfo info = getFromLocalData();
        if (info != null) {
            if (isOldData(info.gettingDate) && isConnected())
                getFromApi(woeid);
            else {
                SharedData.weatherInfo = info;
                if(!isConnected())
                    Toast.makeText(_context, "No internet connection! Data may be not current!", Toast.LENGTH_LONG).show();
            }
        } else if (isConnected())
            getFromApi(woeid);
        else {
            Toast.makeText(_context, "No internet connection! Cannot get data!", Toast.LENGTH_LONG).show();
        }
    }

    private void saveToLocalData(String data) {
        String filename = "weather.txt";
        FileOutputStream outputStream;

        try {
            outputStream = _context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private WeatherInfo getFromLocalData() {
        String filename = "weather.txt";
        FileInputStream inputstream;
        StringBuilder builder = new StringBuilder();

        try {
            inputstream = _context.openFileInput(filename);
            int ch;
            while ((ch = inputstream.read()) != -1) {
                builder.append((char) ch);
            }
            String data = builder.toString();
            return gson.fromJson(data, WeatherInfo.class);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void getFromApi(String woeid) {
        try {
            String urll = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%3D" + woeid + "%20and%20u%3D%22c%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
            URL url = new URL(urll);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line, response = "";
            while ((line = rd.readLine()) != null) {
                response += line;
            }

            JsonElement jelement = new JsonParser().parse(response);
            JsonObject jobject = jelement.getAsJsonObject();
            jobject = jobject.getAsJsonObject("query");
            jobject = jobject.getAsJsonObject("results");
            JsonElement channelElement = jobject.get("channel");

            SharedData.weatherInfo = gson.fromJson(channelElement, WeatherInfo.class);
            SharedData.weatherInfo.gettingDate = new DateTime();

            saveToLocalData(gson.toJson(SharedData.weatherInfo));

            System.out.print("dsdsdsds");


        } catch (IOException e) {
            e.printStackTrace();
            SharedData.weatherInfo = new WeatherInfo();
            SharedData.weatherInfo.gettingDate = new DateTime();
            saveToLocalData(gson.toJson(SharedData.weatherInfo));
        }
    }

    public void SaveUnits(Units units) {
        String filename = "units.txt";
        FileOutputStream outputStream;

        try {
            String data = gson.toJson(units);
            outputStream = _context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Units LoadUnits() {
        String filename = "units.txt";
        FileInputStream inputstream;
        StringBuilder builder = new StringBuilder();

        try {
            inputstream = _context.openFileInput(filename);
            int ch;
            while ((ch = inputstream.read()) != -1) {
                builder.append((char) ch);
            }
            String data = builder.toString();
            return gson.fromJson(data, Units.class);

        } catch (IOException e) {
            e.printStackTrace();
            return new Units();
        }
    }


    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        } else
            return false;
    }

    private boolean isOldData(DateTime dateTime) {
        Period p = new Period(dateTime, new DateTime());
        int hours = p.getHours();
        int minutes = p.getMinutes();
        return minutes > 30;
    }

}
