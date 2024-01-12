package com.anirudha.datetimepicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView textDate;
    /**
     * Creates the view.
     * @param savedInstanceState Saved instance state bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textDate=findViewById(R.id.textView);
    }

    /**
     * Handles the button click to create a new date picker fragment and
     * show it.
     *
     * @param view View that was clicked
     */
    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.datepicker));
    }

    /**
     * Process the date picker result into strings that can be displayed in
     * a Toast.
     *
     * @param year Chosen year
     * @param month Chosen month
     * @param day Chosen day
     */
    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (day_string +
                "/" + month_string +
                "/" + year_string);
        textDate.setText(dateMessage);
        Toast.makeText(this, getString(R.string.date) + dateMessage, Toast.LENGTH_SHORT).show();
    }
}