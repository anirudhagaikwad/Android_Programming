package com.anirudha.keyboarddialphone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Retrieves the phone number from the EditText and makes a call.
     *
     * @param view The view containing the phone number.
     */
    public void makeCall(View view) {
        EditText editText = findViewById(R.id.editText_main);
        if (editText != null) {
            String phoneNumber = editText.getText().toString().trim();

            if (!phoneNumber.isEmpty()) {
                // Check for runtime permission before initiating the call
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                            == PackageManager.PERMISSION_GRANTED) {
                        dialNumber(phoneNumber);
                    } else {
                        // Request the permission
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                REQUEST_CALL_PHONE);
                    }
                } else {
                    // For devices below Android 6.0, no runtime permission is needed
                    dialNumber(phoneNumber);
                }
            } else {
                Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Dials the specified phone number using ACTION_CALL.
     *
     * @param phoneNumber The phone number to dial.
     */
    private void dialNumber(String phoneNumber) {
        // Log the phone number for dialing.
        Log.d(TAG, "Dialing number: " + phoneNumber);

        // Specify the intent.
        Intent intent = new Intent(Intent.ACTION_CALL);
        // Set the data for the intent as the phone number.
        intent.setData(Uri.parse("tel:" + phoneNumber));

        // Check if there is an app that can handle the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No app available to make a call", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, make the call
                EditText editText = findViewById(R.id.editText_main);
                if (editText != null) {
                    String phoneNumber = editText.getText().toString().trim();
                    dialNumber(phoneNumber);
                }
            } else {
                Toast.makeText(this, "Permission denied. Cannot make a call.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
