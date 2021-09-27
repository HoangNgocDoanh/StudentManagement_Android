package com.example.project_studentmanagement_android.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_studentmanagement_android.Model.Student;

public class ForgetPass_controller extends Database {

    public ForgetPass_controller(Context context) {
        super(context);
    }

    public Student GetPhone (String Id)
    {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query("student",null,"id_user" + "= ? ", new String[]{String.valueOf(Id)},null,null,null);
        if ( cursor != null)
            cursor.moveToFirst();
        Student student = new Student(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
        return student;
    }

}
