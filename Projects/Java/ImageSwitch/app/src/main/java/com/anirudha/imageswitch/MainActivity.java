package com.anirudha.imageswitch;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button1);
        // Set an initial image
        setImageResource(R.drawable.img1);
        // Set a click listener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the image when the button is clicked
                switchImage();
            }
        });
        //to start new activity
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                // Create an Intent to start SecondActivity
               startActivity(new Intent(getApplicationContext(), MainActivity2.class));
            }
        });
    }
    private void switchImage() {
        // implement logic for switching images here
       @DrawableRes int currentImage = (int) imageView.getTag();
        int newImage = (currentImage == R.drawable.img1) ? R.drawable.img2 : R.drawable.img1;
        setImageResource(newImage);
    }

    private void setImageResource(@DrawableRes int resourceId) {
        // Set the image resource and update the tag
        imageView.setImageResource(resourceId);
        imageView.setTag(resourceId);
    }
}