package com.example.project_studentmanagement_android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "user";
    public static final String COL_1 = "id";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";
    public static final String COL_4 = "role";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase database = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (Id NCHAR(10) PRIMARY KEY , username NCHAR(50), password NCHAR(50), role NCHAR(50) )");
        db.execSQL(" CREATE TABLE teacher(id_user NCHAR(20), id_teacher NCHAR(20) PRIMARY KEY, teacher_mail NCHAR(100), teacher_name NVARCHAR(50), teacher_phone NCHAR(10), FOREIGN KEY (id_user) REFERENCES user(Id) )");
        db.execSQL(" CREATE TABLE student(id_user NCHAR(20), id_student NCHAR(20) PRIMARY KEY, student_gender NVARCHAR(5),student_address NVARCHAR(100),student_birthday NCHAR(50),student_name NVARCHAR(50), student_phone NCHAR(10), FOREIGN KEY (id_user) REFERENCES user(Id) )");
        db.execSQL(" CREATE TABLE subject(id_subject NCHAR(20) PRIMARY KEY, subject_name NVARCHAR(100),credits INTEGER, number_class INTEGER,max_class INTEGER)");
        db.execSQL(" CREATE TABLE classes(id_subject NCHAR(20),id_teacher NCHAR(20), id_class NCHAR(20) PRIMARY KEY, class_name NVARCHAR(100),number_student INTEGER,max_student INTEGER,time NVARCHAR(100), FOREIGN KEY (id_subject) REFERENCES subject(id_subject), FOREIGN KEY (id_teacher) REFERENCES teacher(id_teacher) )");
        db.execSQL("CREATE TABLE teach(id_subject NCHAR(20), id_teacher NCHAR(20), id_class NCHAR(20), FOREIGN KEY (id_subject) REFERENCES subject(id_subject), FOREIGN KEY (id_teacher) REFERENCES teacher(id_teacher),FOREIGN KEY (id_class) REFERENCES classes(id_class))");
        db.execSQL("CREATE TABLE scores(id_class NCHAR(20), id_student NCHAR(20), middle_scores FLOAT, final_scores FLOAT, average_scores FLOAT , FOREIGN KEY (id_class) REFERENCES classes(id_class), FOREIGN KEY (id_student) REFERENCES student(id_student))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(" DROP TABLE IF EXISTS " + "user");
//        db.execSQL(" DROP TABLE IF EXISTS " + "teacher");
//        db.execSQL(" DROP TABLE IF EXISTS " + "student");
//
//        db.execSQL(" DROP TABLE IF EXISTS " + "subject");
//        db.execSQL(" DROP TABLE IF EXISTS " + "classes");

        db.execSQL(" DROP TABLE IF EXISTS " + "student");
//        db.execSQL(" DROP TABLE IF EXISTS " + "scores");
//        db.execSQL(" DROP TABLE IF EXISTS " + "study");
//        db.execSQL(" DROP TABLE IF EXISTS " + "scores");

        db.execSQL(" CREATE TABLE student(id_user NCHAR(20), id_student NCHAR(20) PRIMARY KEY, student_gender NVARCHAR(5),student_address NVARCHAR(100),student_birthday NCHAR(50),student_name NVARCHAR(50), student_phone NCHAR(10), FOREIGN KEY (id_user) REFERENCES user(Id) )");
//        db.execSQL("CREATE TABLE scores(id_class NCHAR(20), id_student NCHAR(20), middle_scores FLOAT, final_scores FLOAT, average_scores FLOAT , FOREIGN KEY (id_class) REFERENCES classes(id_class), FOREIGN KEY (id_student) REFERENCES student(id_student))");


    }

    //insert user
    public boolean insertUser(String Id, String username, String pass, String role) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, Id);
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, pass);
        contentValues.put(COL_4, role);
        long result = database.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //insert all base data
    public void InsertAllBaseData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO user(Id, username, password, role) VALUES ('tch1','van','123456','teacher')");
        db.execSQL("INSERT INTO user(Id, username, password, role) VALUES ('tch2','admin','123456','teacher')");
        db.execSQL("INSERT INTO user(Id, username, password, role) VALUES ('st1','quoc','123456','student')");
        db.execSQL("INSERT INTO user(Id, username, password, role) VALUES ('st2','quan','123456','student')");
        db.execSQL("INSERT INTO teacher(id_user, id_teacher, teacher_mail, teacher_name, teacher_phone) VALUES ('tch1','teacher01','thivan@gmail.com', 'Nguy???n Tr???n Thi V??n','0377188568')");
        db.execSQL("INSERT INTO teacher(id_user, id_teacher, teacher_mail, teacher_name, teacher_phone) VALUES ('tch2','teacher02','admin@gmail.com', 'Cao Qu???c Nh??n','0377188568')");
        db.execSQL("INSERT INTO student(id_user, id_student, student_gender, student_address, student_birthday, student_name, student_phone) VALUES ('st1','student01','Nam','01 H??a B??nh, TPHCM','16/05/2000','Tr???n V?? Qu???c', '0973872820')");
        db.execSQL("INSERT INTO student(id_user, id_student, student_gender, student_address, student_birthday, student_name, student_phone) VALUES ('st2','student02','Nam','03/2 Th???ng Nh???t, TPHCM','14/06/2000','V?? Ph???m Ho??ng Qu??n', '0377188568')");
////
////
        db.execSQL("INSERT INTO subject(id_subject, subject_name, credits, number_class, max_class) VALUES ('Subject01','subject 01','3','1','3')");
        db.execSQL("INSERT INTO subject(id_subject, subject_name, credits, number_class, max_class) VALUES ('Subject02','subject 02','3','1','3')");
//
//
//        1 l???p ??c d???y b???i 1 gi??o vi??n v?? 1 gi??o vi??n ??c d???y nhi???u l???p
        db.execSQL("INSERT INTO classes(id_subject, id_teacher, id_class, class_name, number_student, max_student, time) VALUES ('Subject01','teacher02','Subject01Class01','Class 01', '1','10','Th??? 2, ti???t 1-3')");
        db.execSQL("INSERT INTO classes(id_subject, id_teacher, id_class, class_name, number_student, max_student, time) VALUES ('Subject02','teacher02','Subject02Class01','Class 01', '0','10','Th??? 3, ti???t 1-3')");
        db.execSQL("INSERT INTO classes(id_subject, id_teacher, id_class, class_name, number_student, max_student, time) VALUES ('Subject02','teacher02','Subject02Class02','Class 02', '0','10','Th??? 3, ti???t 5-7')");

//
//        //1 m??n ??c d???y b???i nhi???u gi??o vi??n ho???c 1 gi??o vi??n d???y nhi???u m??n
        db.execSQL("INSERT INTO teach(id_subject, id_teacher, id_class) VALUES ('Subject01','teacher02','Subject01Class01')");
        db.execSQL("INSERT INTO teach(id_subject, id_teacher, id_class) VALUES ('Subject02','teacher02','Subject02Class01')");

        db.execSQL("INSERT INTO scores(id_class, id_student, middle_scores, final_scores, average_scores) VALUES ('Subject01Class01','student01','8','10','9')");
        db.execSQL("INSERT INTO scores(id_class, id_student, middle_scores, final_scores, average_scores) VALUES ('Subject02Class01','student01','8','10','9')");
        db.execSQL("INSERT INTO scores(id_class, id_student, middle_scores, final_scores, average_scores) VALUES ('Subject02Class02','student01','8','10','9')");







    }


}
