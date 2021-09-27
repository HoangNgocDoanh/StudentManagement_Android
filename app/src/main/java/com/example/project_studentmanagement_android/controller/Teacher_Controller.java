package com.example.project_studentmanagement_android.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_studentmanagement_android.controller.Database;

import java.util.ArrayList;
import java.util.List;

public class Teacher_Controller extends Database {
    public Teacher_Controller(Context context) {
        super(context);
    }

    //get data
    public Cursor GetTeacher() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from teacher", null);
        return res;
    }

    //lấy id giáo viên cho vào list
    public List<String> GetListIdTeacher() {
        String IdTeacher;
        List<String> LstIdTeacher = new ArrayList<String>();

        Cursor data = GetTeacher();
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            IdTeacher = data.getString(1);

            LstIdTeacher.add(IdTeacher);
        }
        data.close();
        return LstIdTeacher;
    }
}



