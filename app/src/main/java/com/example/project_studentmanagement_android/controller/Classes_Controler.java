package com.example.project_studentmanagement_android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.controller.Facade_Pattern.FacadeDeletedata;
import com.example.project_studentmanagement_android.controller.Facade_Pattern.Facade_DeleteClass;

import java.util.ArrayList;
import java.util.List;

public class Classes_Controler extends Database {
    public Classes_Controler(Context context) {
        super(context);
    }


    //get data
    public Cursor GetClasses() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from classes", null);
        return res;
    }

    //get data theo id subject
    public Cursor GetClassesByIdSub(String idSub) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from classes WHERE id_subject ='" + idSub + "'", null);
        return res;
    }

    //gán toàn bộ class data vào list<classes>
    public List<Classes> GetListClasses() {
        String IdSubject;
        String IdTeacher;
        String IdClass;
        String ClassName;
        int NumberStudent;
        int MaxStudent;
        String Time;
        List<Classes> LstClass = new ArrayList<Classes>();

        Cursor data = GetClasses();
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            IdSubject = data.getString(0);
            IdTeacher = data.getString(1);
            IdClass = data.getString(2);
            ClassName = data.getString(3);
            NumberStudent = data.getInt(4);
            MaxStudent = data.getInt(5);
            Time = data.getString(6);

            LstClass.add(new Classes(IdSubject, IdTeacher, IdClass, ClassName, NumberStudent, MaxStudent, Time));
        }
        data.moveToFirst();
        data.close();

        return LstClass;
    }


    //gán class data vào list<classes> theo id môn
    public List<Classes> GetListClassesByIdSub(String idSub) {
        String IdSubject;
        String IdTeacher;
        String IdClass;
        String ClassName;
        int NumberStudent;
        int MaxStudent;
        String Time;
        List<Classes> LstClass = new ArrayList<Classes>();

        Cursor data = GetClassesByIdSub(idSub);
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            IdSubject = data.getString(0);
            IdTeacher = data.getString(1);
            IdClass = data.getString(2);
            ClassName = data.getString(3);
            NumberStudent = data.getInt(4);
            MaxStudent = data.getInt(5);
            Time = data.getString(6);

            LstClass.add(new Classes(IdSubject, IdTeacher, IdClass, ClassName, NumberStudent, MaxStudent, Time));
        }
        data.moveToFirst();
        data.close();

        return LstClass;
    }


    //lấy Id class cho vào mảng theo id môn
    public List<String> GetListIDClassesByIdSub(String idSub) {
        List<String> lstIdClass = new ArrayList<String>();

        Cursor data = GetClassesByIdSub(idSub);
        if (data != null) {
            for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
                lstIdClass.add(data.getString(2));
            }
        }
        data.close();
        return lstIdClass;
    }

    //lấy thông tin lớp theo id lớp
    public Classes GetClassById(String idclass) {

        Classes classes = new Classes();

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("SELECT * FROM classes WHERE id_class = '" + idclass + "'", null);

        for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            classes.setIdSubject(res.getString(0));
            classes.setIdTeacher(res.getString(1));
            classes.setIdClass(res.getString(2));
            classes.setClassName(res.getString(3));
            classes.setNumberStudent(res.getInt(4));
            classes.setMaxStudent(res.getInt(5));
            classes.setTime(res.getString(6));
        }
        res.close();
        return classes;
    }


    //thêm lớp
    public boolean AddClass(Classes classes, Context context, String idSub) {
        String idSubject = classes.getIdSubject();
        String idTeacher = classes.getIdTeacher();
        String idClass = classes.getIdClass();
        String className = classes.getClassName();
        int numberStudent = 0;
        int maxstudent = classes.getMaxStudent();
        String classTime = classes.getTime();

        //thêm vào bảng class
        ContentValues valuesClass = new ContentValues();
        valuesClass.put("id_subject", idSubject);
        valuesClass.put("id_teacher", idTeacher);
        valuesClass.put("id_class", idClass);
        valuesClass.put("class_name", className);
        valuesClass.put("number_student", numberStudent);
        valuesClass.put("max_student", maxstudent);
        valuesClass.put("time", classTime);

        //thêm vào bảng teach
        ContentValues valuesTeach = new ContentValues();
        valuesTeach.put("id_subject", idSubject);
        valuesTeach.put("id_teacher", idTeacher);
        valuesTeach.put("id_class", idClass);

        SQLiteDatabase database = this.getWritableDatabase();

        //thêm vào class, teach và cập nhập subject

        if (database.insert("classes", null, valuesClass) > 0 && database.insert("teach", null, valuesTeach) > 0 && UpdateSubject(context, idSub)) {
            database.close();
            return true;
        }

        database.close();
        return false;
    }

    //Cập nhật thông tin số lớp trong môn học
    public boolean UpdateSubject(Context context, String idSub) {
        //lấy môn học
        Subject subject = new Subject();
        Subject_Controller controller = new Subject_Controller(context);
        subject = controller.GetSubjectsById(idSub);

        //số lượng lớp + 1
        if (subject.getNumberClass() < subject.getMaxClass()) {
            int numclass = subject.getNumberClass() + 1;

            ContentValues valueSubject = new ContentValues();
            valueSubject.put("number_class", numclass);

            SQLiteDatabase database = this.getWritableDatabase();
            if (database.update("subject", valueSubject, "id_subject = ?", new String[]{idSub}) > 0) {
                database.close();
                return true;
            }
        }
        return false;
    }

    //kiểm tra id class có trùng ko
    public boolean CheckclassId(String classid, String SubId) {
        List<String> lstclass = GetListIDClassesByIdSub(SubId);
        if (lstclass.size() > 0) {
            for (int i = 0; i < lstclass.size(); i++) {
                if (classid.equals(lstclass.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }


    //xóa lớp
    public boolean DeleteClassByID(Context context, String idClass, String idSub, int numStudent) {
        try {
            FacadeDeletedata deletedata = new Facade_DeleteClass(context);

            if (deletedata.DeleteClass(context, idClass, idSub, numStudent)) {
                return true;
            }

        } catch (Exception ex) {
            Toast.makeText(context, "Error Delete Unsuccess", Toast.LENGTH_LONG).show();
        }
        return false;
    }


    //sửa thông tin lớp
    public boolean EditClass(Classes classes) {
        String idClass = classes.getIdClass();
        String className = classes.getClassName();
        int maxstudent = classes.getMaxStudent();
        String classTime = classes.getTime();

        ContentValues cv = new ContentValues();
        cv.put("class_name", className);
        cv.put("max_student", maxstudent);
        cv.put("time", classTime);

        SQLiteDatabase database = this.getWritableDatabase();
        database.update("classes", cv, "id_class = ?", new String[]{idClass});
        return true;
    }


    public Cursor GetClasses2(String id_student) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from classes join scores on classes.id_class = scores.id_class where scores.id_student = ? ", new String[] {id_student});
        return res;
    }

    // lọc danh sách class
    public List<Classes> GetListClasses2(String id_student) {
        String IdSubject;
        String IdTeacher;
        String IdClass;
        String ClassName;
        Integer NumberStudent;
        Integer MaxStudent;
        String Time;
        List<Classes> LstClass = new ArrayList<Classes>();

        Cursor data = GetClasses2(id_student);
        data.moveToFirst();
        for(data.moveToFirst();!data.isAfterLast();data.moveToNext()) {
            IdSubject = data.getString(0);
            IdTeacher = data.getString(1);
            IdClass = data.getString(2);
            ClassName = data.getString(3);
            NumberStudent = data.getInt(4);
            MaxStudent = data.getInt(5);
            Time = data.getString(6);

            LstClass.add(new Classes(IdSubject, IdTeacher, IdClass, ClassName, NumberStudent, MaxStudent, Time));
        }

        return LstClass;
    }


    public Cursor GetClasses3(String id_student, String schedule) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("select * from classes join scores on classes.id_class = scores.id_class where id_student = " + id_student + " and time " + " like '%" + schedule + "%'", new String[] {id_student,null});
        return res;
    }


    public Cursor GetClasses4( String schedule) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "select * from classes join scores on classes.id_class = scores.id_class where  classes.time " + " like '%" + schedule + "%'";
        Cursor res = database.rawQuery(query,null);
        return res;
    }

    public List<Classes> GetListClasses3(String id_student, String schedule) {
        String IdSubject;
        String IdTeacher;
        String IdClass;
        String ClassName;
        Integer NumberStudent;
        Integer MaxStudent;
        String Time;
        List<Classes> LstClass = new ArrayList<Classes>();

        Cursor data = GetClasses3(id_student,schedule);
        data.moveToFirst();
        for(data.moveToFirst();!data.isAfterLast();data.moveToNext()) {
            IdSubject = data.getString(0);
            IdTeacher = data.getString(1);
            IdClass = data.getString(2);
            ClassName = data.getString(3);
            NumberStudent = data.getInt(4);
            MaxStudent = data.getInt(5);
            Time = data.getString(6);

            LstClass.add(new Classes(IdSubject, IdTeacher, IdClass, ClassName, NumberStudent, MaxStudent, Time));
        }

        return LstClass;
    }

    public List<Classes> GetListClasses4(String schedule) {
        String IdSubject;
        String IdTeacher;
        String IdClass;
        String ClassName;
        Integer NumberStudent;
        Integer MaxStudent;
        String Time;
        List<Classes> LstClass = new ArrayList<Classes>();

        Cursor data = GetClasses4(schedule);
        data.moveToFirst();
        for(data.moveToFirst();!data.isAfterLast();data.moveToNext()) {
            IdSubject = data.getString(0);
            IdTeacher = data.getString(1);
            IdClass = data.getString(2);
            ClassName = data.getString(3);
            NumberStudent = data.getInt(4);
            MaxStudent = data.getInt(5);
            Time = data.getString(6);

            LstClass.add(new Classes(IdSubject, IdTeacher, IdClass, ClassName, NumberStudent, MaxStudent, Time));
        }

        return LstClass;
    }

    public List<Classes> GetListClasses5(String id_student, String schedule) {
        String IdSubject;
        String IdTeacher;
        String IdClass;
        String ClassName;
        Integer NumberStudent;
        Integer MaxStudent;
        String Time;
        List<Classes> LstClass = new ArrayList<Classes>();

        for ( int i =0 ; i < GetListClasses2(id_student).size(); i ++)
        {
            if ( GetListClasses4(schedule).get(i).getIdClass() == GetListClasses2(id_student).get(i).getIdClass() ) {
                IdSubject = GetListClasses4(schedule).get(i).getIdSubject();
                IdTeacher = GetListClasses4(schedule).get(i).getIdTeacher();
                IdClass = GetListClasses4(schedule).get(i).getIdClass();
                ClassName = GetListClasses4(schedule).get(i).getClassName();
                NumberStudent = GetListClasses4(schedule).get(i).getNumberStudent();
                MaxStudent = GetListClasses4(schedule).get(i).getMaxStudent();
                Time = GetListClasses4(schedule).get(i).getTime();
                LstClass.add(new Classes(IdSubject, IdTeacher, IdClass, ClassName, NumberStudent, MaxStudent, Time));
            }
        }

        return LstClass;
    }
}
