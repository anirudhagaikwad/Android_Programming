package com.anirudha.weatherapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.Priority;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    private WeatherApi weatherApi;
    private FusedLocationProviderClient fusedLocationClient;
    private TextView textViewTemperature,textViewWindSpeed,textViewCity,textViewLong,textViewLat,textViewSunRise,textViewSunset,textViewCntry ;

    // https://openweathermap.org/api
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.d("WeatherActivity", "Inside onCreate");
        // Create a single OkHttpClient instance to be reused
        OkHttpClient okHttpClient = new OkHttpClient();
        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/") // replace with your weather API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)  // Pass the OkHttpClient instance
                .build();

        // Create WeatherApi instance
        weatherApi = retrofit.create(WeatherApi.class);
        // Initialize FusedLocationProviderClient
        fusedLocationClient = new FusedLocationProviderClient(this);
        // Check and request location permissions
        checkLocationPermission();
    }

    private void checkLocationPermission() {
        Log.d("WeatherActivity", "Inside checkLocationPermission");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission is already granted
            getLastLocation();
        }
    }

    private void getLastLocation() {
        Log.d("WeatherActivity", "Inside getLastLocation");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            Log.d("WeatherActivity","getLastLocation logitude : "+longitude);
                            Log.d("WeatherActivity","getLastLocation latitude : "+latitude);
                            // Fetch weather data using Retrofit
                            fetchWeatherData(latitude, longitude);
                        } else {
                            Log.d("WeatherActivity","call requestLocationUpdates in getLastLocation");

                            requestLocationUpdates();
                        }
                    });
        }
    }

    private void requestLocationUpdates() {
        Log.d("WeatherActivity", "Inside requestLocationUpdates");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            LocationRequest locationRequest = LocationRequest
                    .create()
                    .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                    .setInterval(5000); // Update location every 5 seconds

            LocationCallback locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    Log.d("WeatherActivity", "Location updates received");
                    if (locationResult != null) {
                        Location location = locationResult.getLastLocation();
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        Log.d("WeatherActivity : ","onLocationResult logitude : "+longitude);
                        Log.d("WeatherActivity : ","onLocationResult latitude : "+latitude);
                        // Fetch weather data using Retrofit
                        fetchWeatherData(latitude, longitude);

                        // Stop requesting location updates after obtaining the location
                        fusedLocationClient.removeLocationUpdates(this);
                    }
                }
            };
            Log.d("WeatherActivity","call requestLocationUpdates in requestLocationUpdates");
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        } else {
            // Handle the case where the location permission is not granted
            Toast.makeText(this, "Location permission not granted", Toast.LENGTH_SHORT).show();
        }
    }


    private void fetchWeatherData(double latitude, double longitude) {
        // Replace "your_api_key" with your actual OpenWeatherMap API key
        String apiKey = "31b360133621e08ee1f71bb416c00649";
        Log.d("WeatherActivity", "API Key: " + apiKey);
        Call<WeatherData> call = weatherApi.getWeatherData(latitude, longitude, apiKey);

        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("WeatherActivity", "onResponse Latitude: " + latitude + ", Longitude: " + longitude);
                    // Create a new WeatherData instance
                    WeatherData weatherData = response.body();  // Use the response body directly
                    Log.d("WeatherActivity", "onResponse Full Response: " + response);
                    Log.d("WeatherActivity", "onResponse  Response body : " + response.body());
                    Log.d("WeatherActivity", "onResponse Weather Data: " + weatherData);
                   // Update UI with weatherData
                    updateUI(weatherData);
                } else {
                    // Handle unsuccessful response
                    Log.e("WeatherActivity", "Failed to fetch weather data. Response code: " + response.code());
                    try {
                        Log.e("WeatherActivity", "Error body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(WeatherActivity.this, "Failed to fetch weather data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                // Handle failure
                Toast.makeText(WeatherActivity.this, "Failed to fetch weather data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(WeatherData weatherData) {
        textViewTemperature = findViewById(R.id.tempratureTextViewID);
        textViewWindSpeed = findViewById(R.id.windSpeedTextViewID);
        textViewCity = findViewById(R.id.cityTextViewID);
        textViewLong = findViewById(R.id.longitudeTextViewID);
        textViewLat=findViewById(R.id.latitudeTextViewID);
        textViewSunRise =findViewById(R.id.sunRiseTextViewID);
        textViewSunset=findViewById(R.id.sunSetTextViewID);
        textViewCntry=findViewById(R.id.countryTextViewID);
        // Update UI elements with weatherData
        if (weatherData != null) {
            // WeatherData class has appropriate methods to retrieve
            String temperatureText = "Temperature: " + kelvinToCelsiusString(weatherData.getMain().getTemp());
            String windSpeedText = "WindSpeed: " + weatherData.getWind().getSpeed();
            String cityText = "CityName: " + weatherData.getName();
            String countrText = "Country: " + weatherData.getSys().getCountry();
            String langText = "longitude : " + weatherData.getCoord().getLon();
            String latText = "latitude: " + weatherData.getCoord().getLat();
            String sunRiseText = "Sunrise : " + convertUnixTimeTo12hr(weatherData.getSys().getSunrise());
            String sunSetText = "Sunset: " + convertUnixTimeTo12hr(weatherData.getSys().getSunset());

            textViewTemperature.setText(temperatureText);
            textViewWindSpeed.setText(windSpeedText);
            textViewCity.setText(cityText);
            textViewLong.setText(langText);
            textViewLat.setText(latText);
            textViewSunRise.setText(sunRiseText);
            textViewSunset.setText(sunSetText);
            textViewCntry.setText(countrText);
        } else {
            // Handle the case where weatherData is null or not properly initialized
            Toast.makeText(this, "Weather data is unavailable", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, get last location
                getLastLocation();
            } else {
                // Permission denied, handle accordingly (e.g., show a message)
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }

        // Call the superclass implementation
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public static String convertUnixTimeTo12hr(long unixTime) {
        // Convert Unix time to milliseconds
        long unixTimeMillis = unixTime * 1000L;
        // Create a Date object from Unix time
        Date date = new Date(unixTimeMillis);
        // Create a SimpleDateFormat object to format the date
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        // Set the time zone to UTC to avoid local time zone conversion
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        // Format the date in 12-hour AM/PM format
        return sdf.format(date);
    }

    public static String kelvinToCelsiusString(double kelvin) {
        double celsius = kelvin - 273.15;
        return String.format("%.2f°C", celsius); // Format the temperature with two decimal places and append "°C"
    }
}