package com.anirudha.activitylifecycle_intent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class FirstActivity extends AppCompatActivity {
    ImageButton imageButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        imageButton1 = findViewById(R.id.imageButton1);
        startSecondActivity();
    }
    private final ActivityResultLauncher<Intent> secondActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        String messageFromSecondActivity = data.getStringExtra("FROM_SECOND_ACTIVITY");
                        if (messageFromSecondActivity != null) {
                            Log.i("Msg from Activity2: ", messageFromSecondActivity);
                        } else {
                            Log.e("Error", "messageFromSecondActivity is null");
                        }
                    } else {
                        Log.e("Error", "Data in result is null");
                    }
                } else {
                    Log.e("ActivityResult", "Result code is not OK");
                }
            }
    );

        private void startSecondActivity() {
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataToPass = "Hello from FirstActivity";
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("FROM_FIRST_ACTIVITY", dataToPass);
                secondActivityLauncher.launch(intent);
            }
        });
    }
}
