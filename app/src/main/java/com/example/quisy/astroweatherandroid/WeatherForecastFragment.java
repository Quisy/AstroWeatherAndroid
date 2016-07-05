package com.example.quisy.astroweatherandroid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.quisy.astroweatherandroid.Models.Forecast;
import com.example.quisy.astroweatherandroid.Models.SharedData;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class WeatherForecastFragment extends Fragment {

    //LinearLayout layout;
    TableLayout tableLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_weather_forecast, container, false);

        //layout = (LinearLayout) rootView.findViewById(R.id.forecastLayout);
        tableLayout = (TableLayout) rootView.findViewById(R.id.tableLayout);

        List<Forecast> forecasts = getForecast();

        for (Forecast forecast : forecasts) {
            LinearLayout layout = new LinearLayout(this.getActivity().getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(16,16,16,16);

            TextView tvDate = new TextView(this.getActivity().getApplicationContext());
            TextView tvLowTemp = new TextView(this.getActivity().getApplicationContext());
            TextView tvHighTemp = new TextView(this.getActivity().getApplicationContext());
            TextView tvText = new TextView(this.getActivity().getApplicationContext());
            TextView tvEmpty = new TextView(this.getActivity().getApplicationContext());

            tvDate.setTextColor(Color.BLACK);
            tvLowTemp.setTextColor(Color.BLACK);
            tvHighTemp.setTextColor(Color.BLACK);
            tvText.setTextColor(Color.BLACK);

            tvDate.setText(forecast.getDate());
            tvText.setText(forecast.getText());
            tvLowTemp.setText("Lowest temperature: " + String.valueOf(forecast.getLow()) + " " + SharedData.units.getTempUnitShort());
            tvHighTemp.setText("Highest temperature: " + String.valueOf(forecast.getHigh()) + " " + SharedData.units.getTempUnitShort());

            layout.addView(tvDate);
            layout.addView(tvText);
            layout.addView(tvLowTemp);
            layout.addView(tvHighTemp);
            layout.addView(tvEmpty);

            ImageView img = new ImageView(this.getActivity().getApplicationContext());
            img.setMinimumHeight(100);
            img.setMinimumWidth(100);

            InputStream is = null;
            try {
                is = (InputStream) new URL("http://l.yimg.com/a/i/us/we/52/" + String.valueOf(forecast.getCode()) + ".gif").getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Drawable d = Drawable.createFromStream(is, "src name");
            img.setImageDrawable(d);

            TableRow row = new TableRow(this.getActivity().getApplicationContext());
            row.addView(layout);
            TableRow row2 = new TableRow(this.getActivity().getApplicationContext());
            row.addView(img);

            tableLayout.addView(row);
        }


        return rootView;
    }

    private List<Forecast> getForecast() {
        return SharedData.weatherInfo.item.getForecast();
    }

}
