package com.anirudha.signupsignin_sqldialect.registrationfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.anirudha.signupsignin_sqldialect.R;
import com.anirudha.signupsignin_sqldialect.dao.User;
import com.anirudha.signupsignin_sqldialect.dao.UserDAOImpl;

public class RegistrationFragment extends Fragment {
    private UserDAOImpl userDAO;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button registerButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDAO = new UserDAOImpl(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        usernameEditText = view.findViewById(R.id.uNameTextRegistration);
        passwordEditText = view.findViewById(R.id.passwordTextRegistration);
        registerButton = view.findViewById(R.id.submitBtnRegistration);

        registerButton.setOnClickListener(v -> register());

        return view;
    }

    private void register() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        try {
            // Check if the username already exists
            if (userDAO.getUserByUsername(username) != null) {
                Toast.makeText(requireContext(), "Username already exists. Choose a different one.", Toast.LENGTH_SHORT).show();
            } else {
                // Perform registration logic
                User newUser = new User(0, username, password);
                userDAO.saveUser(newUser);

                // Registration successful, navigate to login
                NavHostFragment.findNavController(this).navigate(R.id.action_registationFragment_to_loginFragment);
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., database errors)
            Toast.makeText(requireContext(), "Error during registration: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
