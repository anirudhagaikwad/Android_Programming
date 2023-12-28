package com.anirudha.rotateimage;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private Bitmap selectedBitmap;

    private final ActivityResultLauncher<String> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        try {
                            selectedBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result);
                            imageView.setImageBitmap(selectedBitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        Button rotateLeftButton = findViewById(R.id.rotateLeftButton);
        Button rotateRightButton = findViewById(R.id.rotateRightButton);
        Button rotateTopButton = findViewById(R.id.rotateTopButton);
        Button rotateBottomButton = findViewById(R.id.rotateBottomButton);

        rotateLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateImage(-90);
                Toast.makeText(MainActivity.this, "Rotate Left", Toast.LENGTH_SHORT).show();
            }
        });

        rotateRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateImage(90);
                Toast.makeText(MainActivity.this, "Rotate Right", Toast.LENGTH_SHORT).show();
            }
        });

        rotateTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateImage(-90); // Rotate counterclockwise for "Top"
                Toast.makeText(MainActivity.this, "Rotate Top", Toast.LENGTH_SHORT).show();
            }
        });

        rotateBottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateImage(90); // Rotate clockwise for "Bottom"
                Toast.makeText(MainActivity.this, "Rotate Bottom", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void rotateImage(float angle) {
        if (selectedBitmap != null) {
            Matrix matrix = new Matrix();
            matrix.postRotate(angle);
            Bitmap rotatedBitmap = Bitmap.createBitmap(selectedBitmap, 0, 0, selectedBitmap.getWidth(), selectedBitmap.getHeight(), matrix, true);
            imageView.setImageBitmap(rotatedBitmap);
        }
    }

    public void chooseImage(View view) {
        imagePickerLauncher.launch("image/*");
    }
}