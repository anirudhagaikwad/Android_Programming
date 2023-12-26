package com.anirudha.clockpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView hourImageView;
    private AnimatorSet hourAnimatorSet;
    private AnimatorSet minuteAnimatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hourImageView = findViewById(R.id.clockImageView);
        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start rotation animation for the hour stick
                hourAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(
                        MainActivity.this, R.animator.hour_rotation);
                hourAnimatorSet.setTarget(hourImageView);
                hourAnimatorSet.start();

                // Start rotation animation for the minute stick
                minuteAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(
                        MainActivity.this, R.animator.minute_rotation);
                minuteAnimatorSet.setTarget(hourImageView); // Use the same ImageView for both
                minuteAnimatorSet.start();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Stop rotation animation for the hour stick
                if (hourAnimatorSet != null) {
                    hourAnimatorSet.cancel();
                }

                // Stop rotation animation for the minute stick
                if (minuteAnimatorSet != null) {
                    minuteAnimatorSet.cancel();
                }

                // Check if the hour and minute sticks are in the same direction
                if (hourImageView.getRotation() == hourImageView.getRotation()) {
                    // Show congratulation message to the user
                    Toast.makeText(MainActivity.this, "Congratulations!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}