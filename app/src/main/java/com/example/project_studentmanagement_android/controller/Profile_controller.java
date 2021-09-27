package com.example.project_studentmanagement_android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_studentmanagement_android.Model.Scores;
import com.example.project_studentmanagement_android.Model.Student;
import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.Model.User;

import java.util.ArrayList;
import java.util.List;

public class Profile_controller extends Database {

    public Profile_controller(Context context) {
        super(context);
    }


    public boolean updateProfileStudent(String id, String gender, String address, String birthday, String name, String phone) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("student_gender", gender);
        values.put("student_address", address);
        values.put("student_birthday", birthday);
        values.put("student_name", name);
        values.put("student_phone", phone);
        int row = database.update("student", values, "id_user = ?", new String[]{id});
        if (row <= 0)
            return false;
        else
            return true;
    }


    //lấy thông tin sinh viên theo mã sinh viên
    public Student GetStudentByIdStudent(String idStd) {
        Student student = new Student();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from student WHERE id_student ='" + idStd + "'", null);
        if(res!=null) {
            res.moveToFirst();
            student.setId_user(res.getString(0));
            student.setId_student(res.getString(1));
            student.setStudent_gender(res.getString(2));
            student.setStudent_address(res.getString(3));
            student.setStudent_birthday(res.getString(4));
            student.setStudent_name(res.getString(5));
            student.setStudent_phone(res.getString(6));
            res.moveToFirst();
            res.close();
            return student;
        }
        res.moveToFirst();
        res.close();
        return student;
    }

    public boolean AddStudent(Student student) {
       String id_user = student.getId_user();
       String id_student = student.getId_student();
       String student_gender = student.getStudent_gender();
       String student_address = student.getStudent_address();
       String student_birthday = student.getStudent_birthday();
       String student_name = student.getStudent_name();
       String student_phone = student.getStudent_phone();

        ContentValues values = new ContentValues();
        values.put("id_user",id_user);
        values.put("id_student",id_student);
        values.put("student_gender",student_gender);
        values.put("student_address",student_address);
        values.put("student_birthday",student_birthday);
        values.put("student_name",student_name);
        values.put("student_phone",student_phone);

        SQLiteDatabase database = this.getWritableDatabase();
        if(database.insert("student",null,values) >0 )
        {
            database.close();
            return true;
        }
        return false;
    }

    public Cursor GetStudent() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from student", null);
        return res;
    }

    public List<Student> GetListStudent() {
        String id_user;
        String id_student;
        String student_gender;
        String student_address;
        String student_birthday;
        String student_name;
        String student_phone;

        List<Student> LstStd = new ArrayList<Student>();

        Cursor data = GetStudent();
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            id_user = data.getString(0);
            id_student = data.getString(1);
            student_gender = data.getString(2);
            student_birthday = data.getString(4);
            student_address = data.getString(3);
            student_name = data.getString(5);
            student_phone = data.getString(6);

            LstStd.add(new Student(id_user, id_student, student_gender, student_address, student_birthday, student_name, student_phone));
        }
        data.close();
        return LstStd;
    }

    //kiểm tra id subject có trùng ko
    public boolean CheckSubjectId(String subjectid)
    {
        List<Student> lstStd = GetListStudent();
        if(lstStd.size()>0) {
            for (int i = 0; i < lstStd.size(); i++) {
                if(subjectid.equals(lstStd.get(i).getId_student().toString().trim()))
                {
                    return false;
                }
            }
        }
        else
        {
            //không có lớp nào trong data
            return false;
        }
        return true;
    }

}
