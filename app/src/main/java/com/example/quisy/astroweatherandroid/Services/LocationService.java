package com.example.quisy.astroweatherandroid.Services;

import com.example.quisy.astroweatherandroid.Models.Location;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Mariusz on 2016-06-27.
 */
public class LocationService {

    Gson gson = new Gson();

    public void Add(String city)
    {
        try{
            String urll = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20geo.places(1)%20where%20text=%27lodz,%20pl%27&format=json";
            String addCityUrl = "https://query.yahooapis.com/v1/public/yql?q=select * from geo.places(1) where text='" + URLEncoder.encode(city, "UTF-8") + ", pl'&format=json";
            URL url = new URL(urll);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            //connection.setRequestProperty("User-Agent", "");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            //connection.setRequestMethod("GET");
            //connection.setDoInput(true);
            //connection.connect();
            int status = connection.getResponseCode();
            //InputStream grr = connection.getInputStream();

            //InputStream inputStream = new BufferedInputStream(connection.getErrorStream());
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

            jobject = jobject.getAsJsonObject("centroid");

            String latitude = jobject.get("latitude").toString();
            String longitude = jobject.get("longitude").toString();

            Location location = new Location();
            location.setName(city);
//            location.setLatitude(Double.parseDouble(latitude));
//            location.setLongitude(Double.parseDouble(longitude));
//
//            String locationJson = gson.toJson(location);
//            System.out.println(locationJson);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
