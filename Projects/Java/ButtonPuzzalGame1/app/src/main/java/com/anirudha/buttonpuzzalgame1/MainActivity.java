package com.anirudha.buttonpuzzalgame1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button[] buttons;
    private Button resetButton;
    private int[] originalColors;
    private List<Integer> selectedButtonIndices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new Button[]{
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9)
        };

        // Initialize originalColors array
        originalColors = new int[buttons.length];
        for (int i = 0; i < buttons.length; i++) {
            originalColors[i] = getRandomColor();
        }

        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetColors();
            }
        });

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleButtonColor(index);
                }
            });
        }

        resetColors();
    }

    private void toggleButtonColor(int index) {
        if (selectedButtonIndices.contains(index)) {
            // The button is part of the selected combination
            int color = getRandomColor();
            for (int selectedIndex : selectedButtonIndices) {
                buttons[selectedIndex].setBackgroundColor(color);
            }
        } else {
            // The button is not part of the selected combination
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setBackgroundColor(originalColors[i]);
            }
        }
    }

    private void resetColors() {
        selectedButtonIndices = getRandomButtonIndices();
        for (int i = 0; i < buttons.length; i++) {
            if (selectedButtonIndices.contains(i)) {
                // Set a single color for buttons in the selected combination
                buttons[i].setBackgroundColor(originalColors[i]);
            } else {
                // Set different colors for other buttons
                buttons[i].setBackgroundColor(getRandomColor());
            }
        }
    }

    private List<Integer> getRandomButtonIndices() {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < buttons.length; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);
        return indices.subList(0, 3); // Select the first 3 shuffled indices
    }

    private int getRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        return Color.rgb(red, green, blue);
    }
}