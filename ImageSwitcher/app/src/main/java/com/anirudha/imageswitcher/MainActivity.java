package com.anirudha.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int flag = 0;
    ImageView textVimage;

    public void imageSwitcher(View v) {

        try {

       textVimage = (ImageView) findViewById(R.id.imageView);

           if(flag==1) {
               textVimage.setBackgroundResource(R.drawable.ic_2);
               flag=0;
               Toast.makeText(this, "Image Changed", Toast.LENGTH_SHORT).show();
           }
           else if(flag==0)
           {
               textVimage.setBackgroundResource(R.drawable.ic_1);
               flag=1;
               Toast.makeText(this, "Image Changed", Toast.LENGTH_SHORT).show();
           }
        }
        catch(Exception e)
        {
            Log.i("Exception","Occured in ::"+e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
