package com.anirudha.signupin_retrofit_api.sign_in_up;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anirudha.signupin_retrofit_api.R;
import com.anirudha.signupin_retrofit_api.api.ApiClient;
import com.anirudha.signupin_retrofit_api.api.ApiInterface;
import com.anirudha.signupin_retrofit_api.model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {

    private EditText usernameEditText, mobileEditText, emailEditText, addressEditText, passwordEditText;
    private Button signUpButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        usernameEditText = view.findViewById(R.id.editTextUsername);
        mobileEditText = view.findViewById(R.id.editTextMobile);
        emailEditText = view.findViewById(R.id.editTextEmail);
        addressEditText = view.findViewById(R.id.editTextAddress);
        passwordEditText = view.findViewById(R.id.editTextPassword);
        signUpButton = view.findViewById(R.id.buttonSignUp);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        return view;
    }

    private void signUp() {
        String username = usernameEditText.getText().toString().trim();
        String mobile = mobileEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate input fields (optional)

        // Call the signup API
        Call<UserResponse> call = ApiClient.getClient().create(ApiInterface.class).signUp(username, mobile, email, address, password);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if (userResponse != null && !userResponse.isError()) {
                    // Signup successful, navigate to another screen or perform required actions
                } else {
                    // Signup failed, show error message
                    String errorMessage = userResponse != null ? userResponse.getMessage() : "Unknown error";
                    // Show error message to the user
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // Handle failure, show error message
            }
        });
    }
}
