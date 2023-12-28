package com.anirudha.activitylifecycle_intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThiredActivity extends AppCompatActivity {

    private static final String TAG = "ThiredActivity";

    private TextView timerTextView;
    private int seconds = 0;
    private Handler handler;

    Button btn4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thired);
        btn4=findViewById(R.id.button4);
        timerTextView = findViewById(R.id.timerTextView);
        Log.d(TAG, "onCreate");

        handler = new Handler(Looper.getMainLooper());
        startTimer();
        implicitIntent();
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

    private void implicitIntent(){
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage();
            }
        });
    }

    private void openWebPage() {
        // Define the URL to open
        String webpageUrl = "https://www.rust-lang.org";
        // Create an implicit intent with ACTION_VIEW action and the webpage URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webpageUrl));
        // Start the activity without checking for resolution
        startActivity(intent);
    }

}
