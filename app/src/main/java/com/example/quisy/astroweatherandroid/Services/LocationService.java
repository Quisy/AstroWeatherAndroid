package com.example.quisy.astroweatherandroid.Services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.quisy.astroweatherandroid.Models.Location;
import com.example.quisy.astroweatherandroid.Models.SharedData;
import com.example.quisy.astroweatherandroid.Models.WeatherInfo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mariusz on 2016-06-27.
 */
public class LocationService {

    Gson gson = new Gson();
    private Context _context;
    String allLocationsFilename = "locations.txt";
    String currentLocationFilename = "location.txt";

    public LocationService(Context context) {
        _context = context;
    }


    public Location Add(String city) {
        try {

            if(!isConnected())
            {
                Toast.makeText(_context, "No internet connection! Cannot get data!", Toast.LENGTH_LONG).show();
                return null;
            }

            String urll = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20geo.places(1)%20where%20text=%27" + city + ",%20pl%27&format=json";
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
            jobject = jobject.getAsJsonObject("place");

            String woeid = jobject.get("woeid").toString();
            woeid = woeid.substring(1).substring(0, woeid.length() - 2);
            String name = jobject.get("name").toString();
            name = name.substring(1).substring(0, name.length() - 2);

            JsonElement adminJElement = jobject.get("admin1");

            jobject = jobject.getAsJsonObject("centroid");

            String latitude = jobject.get("latitude").toString();
            latitude = latitude.substring(1).substring(0, latitude.length() - 2);
            String longitude = jobject.get("longitude").toString();
            longitude = longitude.substring(1).substring(0, longitude.length() - 2);

            Location location = new Location();
            location.setName(name);
            location.setWoeid(woeid);
            location.setLatitude(latitude);
            location.setLongitude(longitude);

            SharedData.currentLocation = location;
            List<Location> locations = getLocations();


            if (!locations.contains(location) && !adminJElement.toString().equals("null")) {
                locations.add(location);

                saveToLocalData(gson.toJson(locations), allLocationsFilename);
                saveToLocalData(gson.toJson(location), currentLocationFilename);

                return location;
            }

            return null;

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }

    private void saveToLocalData(String data, String filename) {

        FileOutputStream outputStream;

        try {
            outputStream = _context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Location> getLocations() {
        FileInputStream inputstream;
        StringBuilder builder = new StringBuilder();

        try {
            inputstream = _context.openFileInput(allLocationsFilename);
            int ch;
            while ((ch = inputstream.read()) != -1) {
                builder.append((char) ch);
            }
            String data = builder.toString();

            Type listType = new TypeToken<ArrayList<Location>>() {
            }.getType();
            List<Location> locations = gson.fromJson(data, listType);
            return locations;

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Location>();
        }

    }

    public Location loadCurrentLocation() {
        FileInputStream inputstream;
        StringBuilder builder = new StringBuilder();

        try {
            inputstream = _context.openFileInput(currentLocationFilename);
            int ch;
            while ((ch = inputstream.read()) != -1) {
                builder.append((char) ch);
            }
            String data = builder.toString();

            return gson.fromJson(data, Location.class);

        } catch (IOException e) {
            e.printStackTrace();
            return new Location();
        }
    }

    public void changeCurrentLocation(Location location) {
        saveToLocalData(gson.toJson(location), currentLocationFilename);
        SharedData.currentLocation = location;
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
}
