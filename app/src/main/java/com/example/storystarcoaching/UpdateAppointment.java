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

public class UpdateAppointment extends AppCompatActivity {
    static EditText dateChosen;
    static EditText timeChosen;
    String updateAptID;
    EditText fname, lname, email, phone, appDate, appTime;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3;
    DBHelper DB;
    String appointmentType;
    private static final String TAG = "UpdateAppointment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_appointment);
        dateChosen = (EditText)findViewById(R.id.editText_newdateSelector);
        timeChosen = (EditText)findViewById(R.id.editText_newTimeSelector);
        fname = (EditText)findViewById(R.id.editText_newfnameClient);
        lname = (EditText)findViewById(R.id.editText_newlnameClient);
        email = (EditText)findViewById(R.id.editText_newemailClient);
        phone = (EditText)findViewById(R.id.editText_newphoneClient);
        appDate = (EditText)findViewById(R.id.editText_newdateSelector);
        appTime = (EditText)findViewById(R.id.editText_newTimeSelector);
        rg = (RadioGroup) findViewById(R.id.radioGroup_newappType);
        DB = new DBHelper(this);
        Intent intent = getIntent();
        updateAptID = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
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

    public void returnHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void updateAppointment(View view) {
        //Log.d(TAG, updateAptID);
        String fNameTXT = fname.getText().toString();
        String lNameTXT = lname.getText().toString();
        String emailTXT = email.getText().toString();
        String phoneTXT = phone.getText().toString();
        String appDateTXT = appDate.getText().toString();
        String appTimeTXT = appTime.getText().toString();
        String AppTypeTXT = appointmentType;
        Boolean checkupdatedata = DB.updateUserAppointment(updateAptID, fNameTXT, lNameTXT, emailTXT, phoneTXT, appDateTXT, appTimeTXT, AppTypeTXT);
        if(checkupdatedata==true) {
            Toast.makeText(UpdateAppointment.this, "Entry Updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConfirmationAppointmentUpdated.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(UpdateAppointment.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
        }
    }
}