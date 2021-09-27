package com.example.project_studentmanagement_android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.controller.Facade_Pattern.FacadeDeletedata;
import com.example.project_studentmanagement_android.controller.Facade_Pattern.Facade_DeleteSubject;

import java.util.ArrayList;
import java.util.List;

public class Subject_Controller extends Database {

    public Subject_Controller(Context context) {
        super(context);
    }


    //get data
    public Cursor GetSubject() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from subject", null);
        return res;
    }

    //gán data vào list<Subject>
    public List<Subject> GetListSubjects() {
        String IdSubject;
        String SubjectName;
        int Credits;
        int NumberClass;
        int MaxClass;

        List<Subject> LstSubject = new ArrayList<Subject>();

        Cursor data = GetSubject();
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            IdSubject = data.getString(0);
            SubjectName = data.getString(1);
            Credits = data.getInt(2);
            NumberClass = data.getInt(3);
            MaxClass = data.getInt(4);


            LstSubject.add(new Subject(IdSubject, SubjectName, Credits, NumberClass, MaxClass));
        }
        data.close();
        return LstSubject;
    }


    //Thêm môn
    public boolean AddSubject(Subject subject) {
        String idSubject = subject.getIdSubject();
        String subjectName = subject.getSubjectName();
        int credits = subject.getCredits();
        int numberClass = subject.getNumberClass();
        int maxClass = subject.getMaxClass();

        ContentValues values = new ContentValues();
        values.put("id_subject",idSubject);
        values.put("subject_name",subjectName);
        values.put("credits",credits);
        values.put("number_class",numberClass);
        values.put("max_class",maxClass);

        SQLiteDatabase database = this.getWritableDatabase();
        if(database.insert("subject",null,values)>0)
        {
            database.close();
            return true;
        }
        return false;
//        Cursor cursor = database.rawQuery("INSERT INTO subject(id_subject, subject_name, credits, number_class, max_class) " +
//                "VALUES ( '" +idSubject+"','"+subjectName +"',"+credits+","+numberClass+","+maxClass+")", null);
//        if (cursor.getCount() > 0) {
//            cursor.close();
//            return true;
//        } else {
//            cursor.close();
//            return false;
//        }
    }

    //kiểm tra id subject có trùng ko
    public boolean CheckSubjectId(String subjectid)
    {
        List<Subject> lstsub = GetListSubjects();
        if(lstsub.size()>0) {
            for (int i = 0; i < lstsub.size(); i++) {
                if(subjectid.equals(lstsub.get(i).getIdSubject().toString().trim()))
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


    //lấy Id môn cho vào mảng
    public List<String> GetListIdSubjects() {
        String IdSubject;

        List<String> LstIdSubject = new ArrayList<String>();

        Cursor data = GetSubject();
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            IdSubject = data.getString(0);

            LstIdSubject.add(IdSubject);
        }
        data.close();
        return LstIdSubject;
    }


    //lấy thông tin môn theo id
    public Subject GetSubjectsById(String id) {

        Subject subject = new Subject();

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("SELECT * FROM subject WHERE id_subject = '" + id + "'", null);

        for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            subject.setIdSubject(res.getString(0));
            subject.setSubjectName(res.getString(1));
            subject.setCredits(res.getInt(2));
            subject.setNumberClass(res.getInt(3));
            subject.setMaxClass(res.getInt(4));
        }
        res.close();
        return subject;
    }


    //xóa môn
    public void DeleteSubject(Context context, String id, int numclass) {
        try {
            FacadeDeletedata deletedata = new Facade_DeleteSubject(context);

            if(deletedata.DeleteSubject(context,id,numclass))
            {
                Toast.makeText(context, "Delete Subject Success", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Delete Subject Fail", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception ex) {
            Toast.makeText(context, "Error Delete Unsuccess", Toast.LENGTH_LONG).show();
        }
    }

    //lấy id class đang dạy môn theo mã môn
    public String[] GetIdClassByIdSubject(String idSub) {
        String[] lstClass;
        List<String> lst = new ArrayList<String>();
        int i = 0;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM classes WHERE id_subject = '" + idSub + "'", null);
//        Cursor cursor = database.query("classes",null,"id_subject"+"= ?",new String[]{idSub},null,null,null);
        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                lst.add(cursor.getString(2));
            }
        }
        lstClass = new String[lst.size()];
        for (i = 0; i < lst.size(); i++) {
            lstClass[i] = lst.get(i);
        }
        cursor.close();

        return lstClass;
    }


    //sửa thông tin môn học
    public boolean EditSubject(Subject subject) {
        String idSubject = subject.getIdSubject().trim();
        String subjectName = subject.getSubjectName().trim();
        int credit = subject.getCredits();
        int maxClass = subject.getMaxClass();

        ContentValues cv = new ContentValues();
        cv.put("subject_name",subjectName);
        cv.put("credits",credit);
        cv.put("max_class",maxClass);

        SQLiteDatabase database = this.getWritableDatabase();
        database.update("subject",cv,"id_subject = ?",new String[]{idSubject});
        return true;
    }


    //lấy số lượng
}
