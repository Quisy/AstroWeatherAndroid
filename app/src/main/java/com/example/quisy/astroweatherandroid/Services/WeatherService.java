package com.example.quisy.astroweatherandroid.Services;

import android.content.Context;

import com.example.quisy.astroweatherandroid.Models.Atmosphere;
import com.example.quisy.astroweatherandroid.Models.SharedData;
import com.example.quisy.astroweatherandroid.Models.WeatherInfo;
import com.example.quisy.astroweatherandroid.Models.Wind;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

/**
 * Created by Mariusz on 2016-07-02.
 */
public class WeatherService {

    Gson gson = new Gson();
    private Context _context;

    public WeatherService(Context context)
    {
        _context = context;
    }

    public void downloadWeatherInfo(String woeid)
    {
        getFromApi(woeid);
    }

    public void getWeatherInfo(String woeid) {
        //getFromApi(woeid, context);
        getFromLocalData();
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

    private void getFromLocalData() {
        String filename = "weather.txt";
        FileInputStream inputstream;
        StringBuilder builder = new StringBuilder();

        try {
            inputstream = _context.openFileInput(filename);
            int ch;
            while((ch = inputstream.read()) != -1){
                builder.append((char)ch);
            }
            String data = builder.toString();
            SharedData.weatherInfo = gson.fromJson(data, WeatherInfo.class);

        } catch (IOException e) {
            e.printStackTrace();
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
            //jobject = jobject.getAsJsonObject("channel");
            JsonElement channelElement = jobject.get("channel");
            //JsonElement windElement = jobject.get("wind");
            //JsonElement atmosphereElement = jobject.get("atmosphere");

            //SharedData.WeatherInfo data = gson.fromJson(channelElement, SharedData.WeatherInfo.class);

            SharedData.weatherInfo = gson.fromJson(channelElement, WeatherInfo.class);

            saveToLocalData(gson.toJson(SharedData.weatherInfo));

            System.out.print("dsdsdsds");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
