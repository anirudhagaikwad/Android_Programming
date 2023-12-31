package com.anirudha.rupeeconvertor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=findViewById(R.id.converterButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToRupees();
            }
        });
    }

    public void convertToRupees()
    {
        try {
            EditText textConvert = (EditText) findViewById(R.id.dollerTextInput);
            Double doller=Double.parseDouble(textConvert.getText().toString());
            //Doller rate into rupees-dated 31Dec2023
            Double rupees = doller * 83.25;
            String strDouble = String.format("%.2f",rupees);
            Toast.makeText(this, doller+": Doller Converted "+"to Rupees :"+strDouble, Toast.LENGTH_LONG).show();
            //String rupString=Double.toString(rupees);
        }
        catch(Exception e)
        {
            Log.i("Info","Exception Occured ::"+e);
        }
    }

}