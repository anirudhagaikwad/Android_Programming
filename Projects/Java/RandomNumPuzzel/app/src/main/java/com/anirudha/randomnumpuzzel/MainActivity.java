package com.anirudha.randomnumpuzzel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomnumber();
    }
    int random;
    public void randomnumber()
    {
        random = (int)(Math.random()*((20-1)+1))+1;

        /*Random rand=new Random();
        random=rand.nextInt(20)+1;*/
    }

    public void randomgenerate(View v)
    {
        try {
            EditText number = (EditText) findViewById(R.id.puzzelInputText);
            int guessVal = Integer.parseInt(number.getText().toString());
            String message;
            if (random < guessVal) {
                message = "Random-Number is Lower";
            } else if (random > guessVal) {
                message = "Random-Numebr is Higher";
            } else {
                message = "Correct-Guess!";
                randomnumber();
            }
            Toast.makeText(this, "hey!"+message, Toast.LENGTH_SHORT).show();

        }
        catch(Exception e)
        {
            Log.i("Exception","Occured in :"+e);
        }
    }

}