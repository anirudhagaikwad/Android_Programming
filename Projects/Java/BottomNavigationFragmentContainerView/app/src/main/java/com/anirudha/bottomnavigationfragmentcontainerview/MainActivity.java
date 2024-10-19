package com.anirudha.bottomnavigationfragmentcontainerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.MenuItem;

import com.anirudha.bottomnavigationfragmentcontainerview.fragments.camera.CameraFragment;
import com.anirudha.bottomnavigationfragmentcontainerview.fragments.meesage.MessageFragment;
import com.anirudha.bottomnavigationfragmentcontainerview.fragments.setting.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentContainerView fragmentContainerView;
    private AppBarConfiguration appBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.nav_view);
        fragmentContainerView = findViewById(R.id.nav_host_fragment_activity_main);
        // Set up initial fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, new CameraFragment())
                    .commit();
        }

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.menu_camera) {
                    selectedFragment = new CameraFragment();
                } else if (item.getItemId() == R.id.menu_message) {
                    selectedFragment = new MessageFragment();
                } else if (item.getItemId() == R.id.menu_setting) {
                    selectedFragment = new SettingFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment_activity_main, selectedFragment)
                            .commit();
                }
                return true;
            }
        });
    }}
        
        
        // Set up ActionBar with Navigation Component
   /*     NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.menu_camera, R.id.menu_message, R.id.menu_setting)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    // Add this method to handle Up button navigation in the ActionBar
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

  } */
