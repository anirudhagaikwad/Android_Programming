package com.anirudha.signupsignin_room_sharedpreferences.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.anirudha.signupsignin_room_sharedpreferences.R;
import com.anirudha.signupsignin_room_sharedpreferences.dao.User;
import com.anirudha.signupsignin_room_sharedpreferences.dao.UserDaoImpl;
import com.anirudha.signupsignin_room_sharedpreferences.databaseclass.AppDatabase;
import com.anirudha.signupsignin_room_sharedpreferences.shareprefrence.SharedPreferencesHelper;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private Executor executor = Executors.newSingleThreadExecutor();
    private SharedPreferencesHelper sharedPreferencesHelper;
    private TextView userInfoTextView;
    private TextView all_userInfoTextView;
    private Button btnSignOut;
    private UserDaoImpl userDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize UserDaoImpl using the AppDatabase singleton
        userDao = new UserDaoImpl(AppDatabase.getInstance(requireContext()));
        // Create/get the singleton instance of SharedPreferencesHelper
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance(requireContext(), NavHostFragment.findNavController(this));

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Check if the user is not logged in
        if (!sharedPreferencesHelper.isLoggedIn()) {
            // Navigate to the login fragment
            NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_loginFragment);
            // Provide feedback to the user
            Toast.makeText(requireContext(), "Please log in", Toast.LENGTH_LONG).show();
        }
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        userInfoTextView=view.findViewById(R.id.homeUserInfoTextView);
        all_userInfoTextView=view.findViewById(R.id.homeAllUserInfoTextView);
        btnSignOut=view.findViewById(R.id.signOut);
        btnSignOut.setOnClickListener(v -> signOut());
        // Fetch and display current user information
        displayCurrentUser();
        // Fetch and display all users information
        displayAllUsers();
        return view;
    }

    private void signOut() {
        // Clear user credentials from SharedPreferences
        sharedPreferencesHelper.logout();
        // Navigate back to the login fragment
       // NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_loginFragment);
        // Provide feedback to the user
        Toast.makeText(requireContext(), "Sign-out successful", Toast.LENGTH_LONG).show();
    }

    private void displayCurrentUser() {
        // Get current user information
        executor.execute(() -> {
            User currentUser = userDao.getUserByUsername(sharedPreferencesHelper.getStoredUsername());
            requireActivity().runOnUiThread(() -> {
                if (currentUser != null) {
                    // Display current user information in userInfoTextView
                    String userInfo = "Username: " + currentUser.getFirstName() + "\nPassword: " + currentUser.getPasswd();
                    userInfoTextView.setText(userInfo);
                }
            });
        });
    }

    private void displayAllUsers() {
        // Fetch all users from the database
        executor.execute(() -> {
            List<User> allUsers = userDao.getAllUsers();
            requireActivity().runOnUiThread(() -> {
                // Display all users information in all_userInfoTextView
                StringBuilder allUsersInfo = new StringBuilder("All Users:\n");
                for (User user : allUsers) {
                    allUsersInfo.append("Username: ").append(user.getFirstName()).append("\nPassword: ").append(user.getPasswd()).append("\n\n");
                }
                all_userInfoTextView.setText(allUsersInfo.toString());
            });
        });
    }
}
