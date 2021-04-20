package com.example.storystarcoaching;

import android.app.AlertDialog;
import android.database.Cursor;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SubmitReferral extends AppCompatActivity {
    EditText fname, lname, email, phone;
    DBHelper DB;
    private static final String TAG = "SubmitReferral";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_referral);
        fname = (EditText) findViewById(R.id.editText_fn_referral);
        lname = (EditText) findViewById(R.id.editText_ln_referral);
        email = (EditText) findViewById(R.id.editText_email_referral);
        phone = (EditText) findViewById(R.id.editText_phone_referral);
        DB = new DBHelper(this);
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void submitReferral(View view) {
        Log.d(TAG, "I am inside submit referral");
        String fnameTXT = fname.getText().toString();
        String lnameTXT = lname.getText().toString();
        String emailTXT = email.getText().toString();
        String phoneTXT = phone.getText().toString();
        Boolean checkinsertdata = DB.insertUserReferral(fnameTXT, lnameTXT, emailTXT, phoneTXT);
        if (checkinsertdata == true)
        {
            Toast.makeText(this, "New Entry Inserted Successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConfirmationReferral.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "New Entry Not Inserted Failed", Toast.LENGTH_LONG).show();
        }
    }
}