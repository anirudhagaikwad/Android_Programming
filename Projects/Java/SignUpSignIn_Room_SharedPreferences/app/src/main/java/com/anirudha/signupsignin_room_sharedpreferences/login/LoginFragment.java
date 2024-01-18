package com.anirudha.signupsignin_room_sharedpreferences.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.anirudha.signupsignin_room_sharedpreferences.R;
import com.anirudha.signupsignin_room_sharedpreferences.dao.UserDaoImpl;
import com.anirudha.signupsignin_room_sharedpreferences.dao.User;
import com.anirudha.signupsignin_room_sharedpreferences.databaseclass.AppDatabase;
import com.anirudha.signupsignin_room_sharedpreferences.shareprefrence.SharedPreferencesHelper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginFragment extends Fragment {
    private UserDaoImpl userDao;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView registrationTextView;
    private Executor executor = Executors.newSingleThreadExecutor();

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize UserDaoImpl using the AppDatabase singleton
        userDao = new UserDaoImpl(AppDatabase.getInstance(requireContext()));
        // Create/get the singleton instance of SharedPreferencesHelper
       sharedPreferencesHelper = SharedPreferencesHelper.getInstance(requireContext(), NavHostFragment.findNavController(this));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Check if the user is not logged in
        if (sharedPreferencesHelper.isLoggedIn()) {
            // Navigate to the login fragment
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_homeFragment);
            // Provide feedback to the user
            Toast.makeText(requireContext(), "log in by sharePref", Toast.LENGTH_LONG).show();
        }

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Initialize UI components
        usernameEditText = view.findViewById(R.id.uNameTextLogin);
        passwordEditText = view.findViewById(R.id.passwordTextLogin);
        loginButton = view.findViewById(R.id.submitBtnLogin);
        registrationTextView = view.findViewById(R.id.registrationTextViewLogin);

        // Set click listener for the login button
        loginButton.setOnClickListener(v -> login());
        registrationTextView.setOnClickListener(v -> startRegistrationFragment());
        return view;
    }

    private void login() {
        // Get user input
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Perform login logic using Executor
        executor.execute(() -> {
            User user = userDao.getUserByUsername(username);

            // Main thread
            requireActivity().runOnUiThread(() -> {
                if (user != null && user.getPasswd().equals(password)) {
                    Toast.makeText(requireContext(), "Correct username & password", Toast.LENGTH_SHORT).show();
                    // Save credentials to SharedPreferences
                    sharedPreferencesHelper.saveCredentials(username, password);
                    startHomeFragment(user);
                } else {
                    Toast.makeText(requireContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void startHomeFragment(User currentUser) {
        // Navigate to the login fragment
        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_homeFragment);
        // Provide feedback to the user
        Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_LONG).show();
        // Pop the back stack
       // NavHostFragment.findNavController(this).popBackStack(R.id.navigation_login, false);
      }

    private void startRegistrationFragment() {
        // Navigate to the login fragment
        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registrationFragment);
        // Provide feedback to the user
        Toast.makeText(requireContext(), "Switch to registration", Toast.LENGTH_LONG).show();
    }
}