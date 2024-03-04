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

public class SignInFragment extends Fragment {

    private EditText mobileEditText, passwordEditText;
    private Button signInButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        mobileEditText = view.findViewById(R.id.editTextMobileSignIn);
        passwordEditText = view.findViewById(R.id.editTextPasswordSignIn);
        signInButton = view.findViewById(R.id.buttonSignIn);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        return view;
    }

    private void signIn() {
        String mobile = mobileEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Call the login API
        Call<UserResponse> call = ApiClient.getClient().create(ApiInterface.class).login(mobile, password);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if (userResponse != null && !userResponse.isError()) {
                    // Login successful, navigate to another screen or perform required actions
                } else {
                    // Login failed, show error message
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
