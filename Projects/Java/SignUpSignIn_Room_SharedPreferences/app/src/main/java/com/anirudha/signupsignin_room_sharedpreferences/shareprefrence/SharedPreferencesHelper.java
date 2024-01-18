package com.anirudha.signupsignin_room_sharedpreferences.shareprefrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.navigation.NavController;

import com.anirudha.signupsignin_room_sharedpreferences.R;

public class SharedPreferencesHelper {
    // SharedPreferences keys
    private static final String PREF_NAME = "user_data";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    // SharedPreferences instance
    private final SharedPreferences sharedPreferences;

    // NavController for navigation
    private final NavController navController;

    // Context for displaying Toast
    private final Context context;

    // Singleton instance
    private static SharedPreferencesHelper instance;

    // Private constructor to prevent direct instantiation
    private SharedPreferencesHelper(Context context, NavController navController) {
        // Initialize SharedPreferences
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Initialize NavController
        this.navController = navController;

        // Initialize Context
        this.context = context;
    }

    // Method to get the singleton instance
    public static synchronized SharedPreferencesHelper getInstance(Context context, NavController navController) {
        if (instance == null) {
            instance = new SharedPreferencesHelper(context, navController);
        }
        return instance;
    }

    // Save user credentials in SharedPreferences
    public void saveCredentials(String username, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    // Retrieve stored username from SharedPreferences
    public String getStoredUsername() {
        return sharedPreferences.getString(KEY_USERNAME, "");
    }

    // Retrieve stored password from SharedPreferences
    public String getStoredPassword() {
        return sharedPreferences.getString(KEY_PASSWORD, "");
    }

    // Check whether the user is already logged in
    public boolean isLoggedIn() {
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    // Logout the user
    public void logout() {
        // Clear SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        // Navigate to the login fragment
        navController.navigate(R.id.action_homeFragment_to_loginFragment);
        // Provide feedback to the user
        Toast.makeText(context, "Log-Out", Toast.LENGTH_LONG).show();
    } // end logout
}
