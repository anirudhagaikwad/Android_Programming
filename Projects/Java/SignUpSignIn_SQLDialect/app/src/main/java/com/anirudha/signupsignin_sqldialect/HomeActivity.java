package com.anirudha.signupsignin_sqldialect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.anirudha.signupsignin_sqldialect.dao.User;
import com.anirudha.signupsignin_sqldialect.dao.UserDAOImpl;
import com.anirudha.signupsignin_sqldialect.loginfragmnet.LoginFragment;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
private TextView userInfoTextView;
private Button signout;
private String currentUsername;
    private UserDAOImpl userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userInfoTextView = findViewById(R.id.homeTextView);
        userDAO = new UserDAOImpl(this);
        signout=findViewById(R.id.signOut);
        //fetchAllUsersData();
        // Retrieve the username passed from the LoginActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("currentUsername")) {
            currentUsername = intent.getStringExtra("currentUsername");
        }
        displayCurrentUserInformation();
        signout.setOnClickListener(v -> navigateToLoginFragment());
    }
    private void fetchAllUsersData(){
        // Fetch all users from the database
        List<User> userList = userDAO.getAllUsers();

        // Display user information in the TextView
        StringBuilder userInfo = new StringBuilder("User Information:\n");

        for (User user : userList) {
            userInfo.append("Username: ").append(user.getUsername()).append("\n");
            userInfo.append("Username: ").append(user.getPassword()).append("\n");
            // Avoid displaying the password directly for security reasons
            // userInfo.append("Password: ").append(user.getPassword()).append("\n");
            userInfo.append("\n");
        }

        userInfoTextView.setText(userInfo.toString());
    }
    private void navigateToLoginFragment() {
        // Get the NavController for the NavHostFragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        // Navigate back to the LoginFragment
        navController.navigate(R.id.navigation_login);
        // Finish the HomeActivity to prevent going back to it
        finish();
    }

    private void displayCurrentUserInformation() {
        // Fetch the information of the current user based on the username
        User currentUser = userDAO.getUserByUsername(currentUsername);

        if (currentUser != null) {
            // Display user information in the TextView
            String userInfo = "User Information:\n" +
                    "Username: " + currentUser.getUsername() + "\n" +
                    "Password: " + currentUser.getPassword();

            userInfoTextView.setText(userInfo);
        }
    }

}