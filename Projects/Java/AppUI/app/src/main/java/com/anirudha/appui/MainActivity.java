package com.anirudha.appui;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView[] imageViews = new ImageView[8];
    private int currentImageIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SwipeRefreshLayout swipe = findViewById(R.id.swipeid);
        for (int i = 0; i < 8; i++) {
            int imageViewId = getResources().getIdentifier("imageView" + (i + 1), "id", getPackageName());
            imageViews[i] = findViewById(imageViewId);
        }
        //switchImage(); // Set the initial image
        setImageResource(R.drawable.img1);
        swipe.setColorSchemeResources(android.R.color.holo_red_dark);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your refresh code goes here
                Log.i("Swipe is working : ","Swipe");
                // After completing the refresh, setRefreshing to false
                switchImage();
                swipe.setRefreshing(false);
            }
        });


        // If you want to show the refresh indicator initially, you can use the post method
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
                // Your initial refresh code goes here
                Log.i("Swipe is working initially : ","Swipe");
                // After completing the initial refresh, setRefreshing to false
                swipe.setRefreshing(false);
            }
        });

    }

    private void switchImage() {
        // implement logic for switching images here
        @DrawableRes int currentImage = (int) imageViews[currentImageIndex].getTag();
        int newImage = (currentImage == R.drawable.img1) ? R.drawable.img2 : R.drawable.img1;
        setImageResource(newImage);
        currentImageIndex = (currentImageIndex + 1) % 8;
    }

    private void setImageResource(@DrawableRes int resourceId) {
        for (ImageView imageView : imageViews) {
            // Set the image resource and update the tag for all ImageViews
            imageView.setImageResource(resourceId);
            imageView.setTag(resourceId);
        }
    }
}
