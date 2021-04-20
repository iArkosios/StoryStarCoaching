package com.example.storystarcoaching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class SubmitTestimonial extends AppCompatActivity {
    EditText testimonial;
    RatingBar mBar;
    Integer userRating;
    DBHelper DB;
    private static final String TAG = "submitTestimonial";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_testimonial);
        testimonial = (EditText) findViewById(R.id.editText_testimonial_comment);
        mBar = (RatingBar) findViewById(R.id.ratingBar_testimonial);
        DB = new DBHelper(this);
    }

    public void returnHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void submitTestimonial(View view)
    {
        Log.d(TAG, "I am inside submit testimonial");
        //get user rating
        userRating = Math.round(mBar.getRating());
        String testimonialTXT = testimonial.getText().toString();
        Boolean checkinsertdata = DB.insertUserTestimonial(userRating, testimonialTXT);
        if(checkinsertdata==true) {
            Toast.makeText(this, "New Entry Inserted Successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConfirmationTestimonial.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "New Entry Not Inserted Failed", Toast.LENGTH_LONG).show();
        }
    }
}