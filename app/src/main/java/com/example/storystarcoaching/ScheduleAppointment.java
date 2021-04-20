package com.example.storystarcoaching;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ScheduleAppointment extends AppCompatActivity {
    static EditText dateChosen;
    static EditText timeChosen;
    EditText fname, lname, email, phone, appDate, appTime;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3;
    DBHelper DB;
    String appointmentType;
    private static final String TAG = "ScheduleAppointment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);
        dateChosen = (EditText)findViewById(R.id.editText_dateSelector);
        timeChosen = (EditText)findViewById(R.id.editText_TimeSelector);
        fname = (EditText)findViewById(R.id.editText_fnameClient);
        lname = (EditText)findViewById(R.id.editText_lnameClient);
        email = (EditText)findViewById(R.id.editText_emailClient);
        phone = (EditText)findViewById(R.id.editText_phoneClient);
        appDate = (EditText)findViewById(R.id.editText_dateSelector);
        appTime = (EditText)findViewById(R.id.editText_TimeSelector);
        rg = (RadioGroup) findViewById(R.id.radioGroup_newappType);
        DB = new DBHelper(this);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton_newfreeSession:
                        // do operations specific to this selection
                        rb1 = (RadioButton) findViewById(R.id.radioButton_newfreeSession);
                        appointmentType = rb1.getText().toString();
                        break;
                    case R.id.radioButton_newstorySession:
                        // do operations specific to this selection
                        rb2 = (RadioButton) findViewById(R.id.radioButton_newstorySession);
                        appointmentType = rb2.getText().toString();
                        break;
                    case R.id.radioButton_newdiscoverySession:
                        rb3 = (RadioButton) findViewById(R.id.radioButton_newdiscoverySession);
                        appointmentType = rb3.getText().toString();
                        break;
                }
            }
        });
    }
    public void returnHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void submitAppointment(View view)
    {
        Log.d(TAG, "I am inside submit appointment");
        Log.d(TAG, appointmentType);
        String fnameTXT = fname.getText().toString();
        String lnameTXT = lname.getText().toString();
        String emailTXT = email.getText().toString();
        String phoneTXT = phone.getText().toString();
        String appDateTXT = appDate.getText().toString();
        String appTimeTXT = appTime.getText().toString();
        Boolean checkinsertdata = DB.insertUserAppointment(fnameTXT, lnameTXT, emailTXT, phoneTXT, appDateTXT, appTimeTXT, appointmentType);
        if(checkinsertdata==true) {
            Toast.makeText(this, "New Entry Inserted Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConfirmationAppointment.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "New Entry Not Inserted Failed", Toast.LENGTH_SHORT).show();
        }
    }
    public void showTimePickerDialog(View view) {
            DialogFragment newFragment = new TimePickerFragment();
            newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
    public static void setDateChosen(String x){
        dateChosen.setText(x);
    }
    public static void setTimeChosen(String x){
        timeChosen.setText(x);
    }
}