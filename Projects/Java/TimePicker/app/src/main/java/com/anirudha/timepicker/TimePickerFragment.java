package com.anirudha.timepicker;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    /**
     * Creates the time picker dialog with the current time from Calendar.
     *
     * @param savedInstanceState Saved instance state bundle.
     * @return TimePickerDialog The time picker dialog.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it.
        return new TimePickerDialog(requireContext(), this, hour, minute,
                android.text.format.DateFormat.is24HourFormat(requireContext()));
    }

    /**
     * Grabs the time and converts it to a string to pass
     * to the MainActivity in order to show it with processTimePickerResult().
     *
     * @param timePicker The time picker view
     * @param hourOfDay  The hour chosen
     * @param minute     The minute chosen
     */
    /* @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        MainActivity activity = (MainActivity) requireActivity();
        activity.processTimePickerResult(hourOfDay, minute);
    } */

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        String timeFormat;
        if (android.text.format.DateFormat.is24HourFormat(requireContext())) {
            timeFormat = String.format("%02d:%02d", hourOfDay, minute);
        } else {
            int amPm = hourOfDay < 12 ? Calendar.AM : Calendar.PM;
            timeFormat = String.format("%02d:%02d %s", (hourOfDay % 12 == 0) ? 12 : hourOfDay % 12, minute, amPm == Calendar.AM ? "AM" : "PM");
        }

        MainActivity activity = (MainActivity) requireActivity();
        activity.processTimePickerResult(hourOfDay, minute, timeFormat);
    }

}
