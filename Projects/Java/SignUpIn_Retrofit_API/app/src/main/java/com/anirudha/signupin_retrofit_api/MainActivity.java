package com.anirudha.signupin_retrofit_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anirudha.signupin_retrofit_api.api.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
