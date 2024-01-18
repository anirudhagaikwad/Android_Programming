package com.anirudha.signupsignin_sqldialect.loginfragmnet;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anirudha.signupsignin_sqldialect.HomeActivity;
import com.anirudha.signupsignin_sqldialect.R;
import com.anirudha.signupsignin_sqldialect.dao.User;
import com.anirudha.signupsignin_sqldialect.dao.UserDAOImpl;

public class LoginFragment extends Fragment {
    private LoginViewModel loginViewModel;
    private UserDAOImpl userDAO;
    private TextView userNameTextView;
    private TextView passwordTextView;
    private Button loginButton;
    private TextView registerHereTextView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        userDAO = new UserDAOImpl(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        userNameTextView = view.findViewById(R.id.uNameTextLogin);
        passwordTextView = view.findViewById(R.id.passwordTextLogin);
        loginButton = view.findViewById(R.id.submitBtnLogin);
        registerHereTextView=view.findViewById(R.id.registrationTextViewLogin);
        loginButton.setOnClickListener(v -> login());
        registerHereTextView.setOnClickListener(v -> registerHere());
        return view;
    }
    private void login(){
            String username = userNameTextView.getText().toString();
            String password = passwordTextView.getText().toString();

            try {
                // Perform login logic
                User user = userDAO.getUserByUsername(username);

                if (user != null && user.getPassword().equals(password)) {
                    // Successful login, navigate to the next fragment or perform other actions
                    // For example, you can navigate to another fragment:
                    // NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_homeFragment);
                    Toast.makeText(requireContext(), "Correct username or password", Toast.LENGTH_SHORT).show();
                    startHomeActivity(user);
                } else {
                    // Display an error message or handle unsuccessful login
                    Toast.makeText(requireContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // Handle exceptions (e.g., database errors)
                Toast.makeText(requireContext(), "Error during login: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

    private void startHomeActivity(User currentUser) {
        // Create an Intent to start the activity
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        // If you need to pass data to the activity, use putExtra
        // Pass the current user information as extras
        intent.putExtra("currentUsername", currentUser.getUsername());
        intent.putExtra("currentPassword", currentUser.getPassword());
        // Start the activity
        startActivity(intent);
        NavHostFragment.findNavController(this).popBackStack();
    }
    private void registerHere() {
        // Handle text click to navigate to RegistrationFragment
        registerHereTextView.setOnClickListener(v -> {
            // Navigate to RegistrationFragment
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registrationFragment);
        });
    }

}