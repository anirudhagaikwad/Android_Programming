package com.anirudha.fragmentlifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.anirudha.fragmentlifecycle.fragment.FragmentLifeCycle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            // Add the initial fragment when the activity is created.
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainerView, new FragmentLifeCycle())
                    .commit();
        }
    }
}