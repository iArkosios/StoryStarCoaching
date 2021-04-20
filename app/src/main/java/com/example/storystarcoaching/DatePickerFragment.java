package com.example.storystarcoaching;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public  class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "DatePickerFragment";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        // Do something with the date chosen by the user
        try
        {
            try
            {
                ScheduleAppointment.setDateChosen((day + "-" + (month + 1) + "-" + year));
            }
            catch(Exception e)
            {
                UpdateAppointment.setDateChosen((day + "-" + (month + 1) + "-" + year));
            }
            UpdateAppointment.setDateChosen((day + "-" + (month + 1) + "-" + year));
        }
        catch (Exception e)
        {
            Log.d("DatePickerFragment", "I am inside catch clause of date picker");
        }
    }
}
