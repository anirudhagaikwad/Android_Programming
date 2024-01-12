package com.anirudha.datetimepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

/**
 * A simple {@link DialogFragment} subclass for the date picker.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    /**
     * Creates the date picker dialog with the current date from Calendar.
     *
     * @param savedInstanceState Saved instance state bundle
     * @return DatePickerDialog The date picker dialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker.
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it.
        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    /**
     * Grabs the date and passes it to processDatePickerResult().
     *
     * @param datePicker The date picker view
     * @param year       The year chosen
     * @param month      The month chosen
     * @param day        The day chosen
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        // Set the activity to the Main Activity.
        MainActivity activity = (MainActivity) requireActivity();
        // Invoke Main Activity's processDatePickerResult() method.
        activity.processDatePickerResult(year, month, day);
    }
}
