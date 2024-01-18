package com.anirudha.signupsignin_room_sharedpreferences.registration;

/*
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.anirudha.signupsignin_room_sharedpreferences.R;
import com.anirudha.signupsignin_room_sharedpreferences.dao.User;
import com.anirudha.signupsignin_room_sharedpreferences.dao.UserDaoImpl;
import com.anirudha.signupsignin_room_sharedpreferences.databaseclass.AppDatabase;

public class RegistrationFragment extends Fragment {
    private UserDaoImpl userDao;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private NavController navController;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize UserDaoImpl using the AppDatabase singleton
        userDao = new UserDaoImpl(AppDatabase.getInstance(requireContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        // Initialize UI components
        usernameEditText = view.findViewById(R.id.uNameTextRegistration);
        passwordEditText = view.findViewById(R.id.passwordTextRegistration);
        registerButton = view.findViewById(R.id.submitBtnRegistration);
        // Store NavController during onCreateView
        navController = NavHostFragment.findNavController(this);
        // Set click listener for the register button
        registerButton.setOnClickListener(v -> registerUser());
        return view;
    }

    private void registerUser() {
        // Get user input
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        // Check if the username is already taken
        if (userDao.getUserByUsername(username) != null) {
            Toast.makeText(requireContext(), "Username already taken. Choose another one.", Toast.LENGTH_SHORT).show();
            return;
        }
        // Create a new User object
        User newUser = new User(0, username, password);
        // Save the user to the database
        userDao.insertUser(newUser);
        // Navigate to the login fragment using the stored NavController
        navController.navigate(R.id.action_registrationFragment_to_loginFragment);
       // Navigate to the login fragment
       // NavHostFragment.findNavController(this).navigate(R.id.action_registrationFragment_to_loginFragment);
        // Provide feedback to the user
        Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show();
    }
}
*/

/*
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.anirudha.signupsignin_room_sharedpreferences.R;
import com.anirudha.signupsignin_room_sharedpreferences.dao.User;
import com.anirudha.signupsignin_room_sharedpreferences.dao.UserDaoImpl;
import com.anirudha.signupsignin_room_sharedpreferences.databaseclass.AppDatabase;

public class RegistrationFragment extends Fragment {
    private UserDaoImpl userDao;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private NavController navController;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize UserDaoImpl using the AppDatabase singleton
        userDao = new UserDaoImpl(AppDatabase.getInstance(requireContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        // Initialize UI components
        usernameEditText = view.findViewById(R.id.uNameTextRegistration);
        passwordEditText = view.findViewById(R.id.passwordTextRegistration);
        registerButton = view.findViewById(R.id.submitBtnRegistration);
        // Store NavController during onCreateView
        navController = NavHostFragment.findNavController(this);
        // Set click listener for the register button
        registerButton.setOnClickListener(v -> registerUser());
        return view;
    }

    private void registerUser() {
        // Get user input
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the username is already taken in the background
        new CheckUsernameAsyncTask().execute(username, password);
    }

    private class CheckUsernameAsyncTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String username = params[0];
            String password = params[1];

            // Check if the username is already taken
            return userDao.getUserByUsername(username) != null;
        }

        @Override
        protected void onPostExecute(Boolean isUsernameTaken) {
            // Main thread
            if (isUsernameTaken) {
                Toast.makeText(requireContext(), "Username already taken. Choose another one.", Toast.LENGTH_SHORT).show();
            } else {
                // Username is available, proceed with registration in the background
                new RegisterUserAsyncTask().execute(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        }
    }

    private class RegisterUserAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String username = params[0];
            String password = params[1];

            // Create a new User object
            User newUser = new User(0, username, password);

            // Save the user to the database
            userDao.insertUser(newUser);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Main thread
            // Navigate to the login fragment using the stored NavController
            navController.navigate(R.id.action_registrationFragment_to_loginFragment);
            // Provide feedback to the user
            Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show();
        }
    }
}

*/

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.anirudha.signupsignin_room_sharedpreferences.R;
import com.anirudha.signupsignin_room_sharedpreferences.dao.User;
import com.anirudha.signupsignin_room_sharedpreferences.dao.UserDaoImpl;
import com.anirudha.signupsignin_room_sharedpreferences.databaseclass.AppDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RegistrationFragment extends Fragment {
    private UserDaoImpl userDao;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private NavController navController;
    private Executor executor = Executors.newSingleThreadExecutor();

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize UserDaoImpl using the AppDatabase singleton
        userDao = new UserDaoImpl(AppDatabase.getInstance(requireContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        // Initialize UI components
        usernameEditText = view.findViewById(R.id.uNameTextRegistration);
        passwordEditText = view.findViewById(R.id.passwordTextRegistration);
        registerButton = view.findViewById(R.id.submitBtnRegistration);
        // Store NavController during onCreateView
        navController = NavHostFragment.findNavController(this);
        // Set click listener for the register button
        registerButton.setOnClickListener(v -> registerUser());
        return view;
    }

    private void registerUser() {
        // Get user input
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if the username is already taken in the background
        executor.execute(() -> {
            boolean isUsernameTaken = userDao.getUserByUsername(username) != null;

            // Main thread
            requireActivity().runOnUiThread(() -> {
                if (isUsernameTaken) {
                    Toast.makeText(requireContext(), "Username already taken. Choose another one.", Toast.LENGTH_SHORT).show();
                } else {
                    // Username is available, proceed with registration in the background
                    executor.execute(() -> {
                        // Create a new User object
                        User newUser = new User(0, username, password);

                        // Save the user to the database
                        userDao.insertUser(newUser);

                        // Main thread
                        requireActivity().runOnUiThread(() -> {
                            // Navigate to the login fragment using the stored NavController
                            navController.navigate(R.id.action_registrationFragment_to_loginFragment);
                            // Provide feedback to the user
                            Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                        });
                    });
                }
            });
        });
    }
}
