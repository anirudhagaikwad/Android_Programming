package com.anirudha.timepicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * Handles the button click to create a new time picker fragment and
     * show it.
     *
     * @param view View that was clicked
     */
    public void showTimePicker(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.timepicker));
    }

    /**
     * Process the time picker result into strings that can be displayed in
     * a Toast.
     *
     * @param hourOfDay Chosen hour
     * @param minute Chosen minute
     * @param timeFormat Formatted time with AM/PM
     */
  /*  public void processTimePickerResult(int hourOfDay, int minute) {
        // Convert time elements into strings.
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        // Assign the concatenated strings to timeMessage.
        String timeMessage = (hour_string + ":" + minute_string);

        Toast.makeText(this,getString(R.string.time_toast) + timeMessage, Toast.LENGTH_SHORT).show();
    } */

    public void processTimePickerResult(int hourOfDay, int minute, String timeFormat) {
        // Display the time in a Toast.
        String timeMessage = getString(R.string.time_toast) + " " + timeFormat;
        Toast.makeText(this, timeMessage, Toast.LENGTH_SHORT).show();
    }
}
