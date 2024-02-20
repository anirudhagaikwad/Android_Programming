package com.anirudha.customeui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout layout = findViewById(R.id.contlay);
        layout.setBackground(Gradient.createGradientDrawable2(this));

        CardView cardView = findViewById(R.id.cardView);
        cardView.setBackground(Gradient.createGradientDrawable1(this));

        Button btn = findViewById(R.id.b1);
       // btn.setBackground(Gradient.createGradientDrawable1(this));
    }


}
