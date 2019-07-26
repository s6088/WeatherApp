package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {


    private TextView tempText;
    final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tempText = findViewById(R.id.tempText);
        RequestParams requestParams = new RequestParams();
        requestParams.put("q", "Dhaka, BD");
        requestParams.put("appid", "a1c6e6aa953d73ed8ab3bf9b06bacfd9");
        apiCall(requestParams);
    }


    private void apiCall(RequestParams requestParams) {

            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

            asyncHttpClient.get(WEATHER_URL, requestParams, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    Weather weather = Weather.fromJson(response);
                    tempText.setText(weather .getCity() + " : " +weather.getTemperature() + "");
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }
            });

        }


}



