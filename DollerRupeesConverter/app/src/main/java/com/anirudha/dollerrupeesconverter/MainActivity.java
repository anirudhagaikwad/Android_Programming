package com.anirudha.dollerrupeesconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public void convetToRupees(View v)
    {
        try {



            EditText textConvert = (EditText) findViewById(R.id.dollerTextInput);
            Double doller=Double.parseDouble(textConvert.getText().toString());
            //Doller rate into rupees-dated 21Oct2019
            Double rupees = doller * 71.01;
            String strDouble = String.format("%.2f",rupees);
            Toast.makeText(this, doller+": Doller Converted "+"to Rupees :"+strDouble, Toast.LENGTH_SHORT).show();

            //String rupString=Double.toString(rupees);


        }
        catch(Exception e)
        {
            Log.i("Info","Exception Occured ::"+e);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
