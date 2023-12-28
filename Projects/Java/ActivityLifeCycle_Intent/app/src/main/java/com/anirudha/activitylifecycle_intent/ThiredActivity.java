package com.anirudha.activitylifecycle_intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThiredActivity extends AppCompatActivity {

    private static final String TAG = "ThiredActivity";

    private TextView timerTextView;
    private int seconds = 0;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thired);

        timerTextView = findViewById(R.id.timerTextView);
        Log.d(TAG, "onCreate");

        handler = new Handler(Looper.getMainLooper());
        startTimer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        stopTimer();
    }

    private void startTimer() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                seconds++;
                updateTimer();
                handler.postDelayed(this, 1000);
            }
        }, 1000); // Initial delay to start the timer immediately
    }

    private void stopTimer() {
        handler.removeCallbacksAndMessages(null);
    }

    private void updateTimer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int minutes = seconds / 60;
                int remainingSeconds = seconds % 60;
                String time = String.format("%02d:%02d", minutes, remainingSeconds);
                timerTextView.setText(time);
            }
        });
    }
}
