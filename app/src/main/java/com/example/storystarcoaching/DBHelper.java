package com.example.storystarcoaching;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "SubmitReferral";
    public DBHelper(Context context) {
        super(context, "DBStoryStarCoaching.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserReferral(ID INTEGER PRIMARY KEY AUTOINCREMENT, fname TEXT, lname TEXT, email TEXT, phone TEXT)");
        DB.execSQL("create Table UserAppointment(ID INTEGER PRIMARY KEY AUTOINCREMENT, fnameClient TEXT, lname TEXT, email TEXT, phone TEXT, appDate TEXT, appTime TEXT, appointmentType TEXT)");
        DB.execSQL("create Table UserTestimonial(ID INTEGER PRIMARY KEY AUTOINCREMENT, userRating INTEGER, testimonial TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserReferral");
        DB.execSQL("drop Table if exists UserAppointment");
        DB.execSQL("drop Table if exists userTestimonial");
        onCreate(DB);
    }
    public Boolean insertUserReferral(String fname, String lname, String email, String phone)
    {
        Log.d("submitReferral", "I am inside DB Helper insert user referral");
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname", fname);
        contentValues.put("lname", lname);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        long result=DB.insert("UserReferral", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean insertUserAppointment(String fname, String lname, String email, String phone, String appDate, String appTime, String appointmentType)
    {
        Log.d("scheduleAppointment", "I am inside DB Helper insert user Appointment");
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fnameClient", fname);
        contentValues.put("lname", lname);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("appDate", appDate);
        contentValues.put("appTime", appTime);
        contentValues.put("appointmentType", appointmentType);
        long result=DB.insert("UserAppointment", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean insertUserTestimonial(Integer userRating, String testimonial)
    {
        Log.d("submitTestimonial", "I am inside DB Helper insert user testimonial");
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userRating", userRating);
        contentValues.put("testimonial", testimonial);
        long result=DB.insert("UserTestimonial", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateUserAppointment(String someID, String fname, String lname, String email, String phone, String appDate, String appTime, String appointmentType) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fnameClient", fname);
        contentValues.put("lname", lname);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        contentValues.put("appDate", appDate);
        contentValues.put("appTime", appTime);
        contentValues.put("appointmentType", appointmentType);
        Cursor cursor = DB.rawQuery("Select * from UserAppointment where ID = ?", new String[]{someID});
        if (cursor.getCount() > 0) {
            long result = DB.update("UserAppointment", contentValues, "ID=?", new String[]{someID});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}

    public Boolean checkAptExists(String someID)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserAppointment where ID = ?", new String[]{someID});
        if (cursor.getCount() > 0)
        {
            //appointment ID exists
            return true;
        }
        else
            {
            return false;
        }
    }

    public Boolean deletedata (String someID)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserAppointment where ID = ?", new String[]{someID});
        if (cursor.getCount() > 0) {
            long result = DB.delete("UserAppointment", "ID=?", new String[]{someID});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserAppointment", null);
        return cursor;
    }
}
