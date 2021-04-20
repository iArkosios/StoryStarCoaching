package com.example.storystarcoaching;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper DB;
    private String delete_apt_ID = "";
    private String update_apt_ID = "";
    public static final String EXTRA_MESSAGE = "com.example.android.storystarcoaching.extra.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DBHelper(this);
    }

    public void launchScheduleApp(View view) {
        Intent intent = new Intent(this, ScheduleAppointment.class);
        startActivity(intent);
    }

    public void launchSubmitReferral(View view) {
        Intent intent = new Intent(this, SubmitReferral.class);
        startActivity(intent);
    }

    public void launchSubmitTestimonial(View view) {
        Intent intent = new Intent(this, SubmitTestimonial.class);
        startActivity(intent);
    }

    public void launchViewAppointment(View view) {
        Cursor res = DB.getdata();
        if(res.getCount()==0){
            Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("ID: "+res.getString(0)+"\n");
            buffer.append("First Name: "+res.getString(1)+"\n");
            buffer.append("Last Name: "+res.getString(2)+"\n");
            buffer.append("Email: "+res.getString(3)+"\n");
            buffer.append("Phone: "+res.getString(4)+"\n");
            buffer.append("Appointment Date: "+res.getString(5)+"\n");
            buffer.append("Appointment Time: "+res.getString(6)+"\n");
            buffer.append("Appointment Type: "+res.getString(7)+"\n");
            buffer.append("\n\n");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("User Appointment(s)");
        builder.setMessage(buffer.toString());
        builder.show();
    }

    public void launchCancelAppointment(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("To Cancel, Please Enter The Appointment ID");
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete_apt_ID = input.getText().toString();
                Boolean checkudeletedata = DB.deletedata(delete_apt_ID);
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getBaseContext(), "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void launchUpdateAppointment(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("To Update, Please Enter The Appointment ID");
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                update_apt_ID = input.getText().toString();
                Intent intent = new Intent(MainActivity.this, UpdateAppointment.class);
                intent.putExtra(EXTRA_MESSAGE, update_apt_ID);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}