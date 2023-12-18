package com.anirudha.imageswitch;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {
    private ImageView imageView;
    private Button button;
    private boolean isColorChanged = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button3);

        // Set the initial vector drawable
        imageView.setImageResource(R.drawable.img2);

        // Set a click listener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the color when the button is clicked
                toggleColor();
            }
        });
    }
    private void toggleColor() {
        // Define your two desired colors
        int color1 = getColorCompat(R.color.white);
        int color2 = getColorCompat(R.color.yello);

        // Apply color filter to the ImageView based on the current state
        if (isColorChanged) {
            imageView.setColorFilter(color1, PorterDuff.Mode.SRC_IN);
        } else {
            imageView.setColorFilter(color2, PorterDuff.Mode.SRC_IN);
        }
        // Toggle the state
        isColorChanged = !isColorChanged;
    }
    private int getColorCompat(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }
}