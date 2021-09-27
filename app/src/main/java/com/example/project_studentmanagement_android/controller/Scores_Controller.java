package com.example.project_studentmanagement_android.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.Scores;
import com.example.project_studentmanagement_android.Model.Subject;

import java.util.ArrayList;
import java.util.List;

public class Scores_Controller extends Database {
    public Scores_Controller(Context context) {
        super(context);
    }

    //lấy điểm sinh viên theo mã sv và mã lớp cụ thể
    public Scores getScores(String id_class, String id_student) {
        SQLiteDatabase database = this.getReadableDatabase();
        if (id_class != null && id_student != null) {
            Cursor cursor = database.rawQuery("SELECT * FROM scores WHERE id_class = ? and id_student = ?", new String[]{id_class, id_student});
            if (cursor != null)
                cursor.moveToFirst();
            Scores scores = new Scores(cursor.getString(0), cursor.getString(1), cursor.getFloat(2), cursor.getFloat(3), cursor.getFloat(4));
            cursor.close();
            return scores;
        }
        return null;
    }


    //get data theo id subject
    public Cursor GetScoreByIdClass(String idClass) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("SELECT * FROM scores WHERE id_class ='" + idClass + "'", null);
        return res;
    }

    //lấy danh sách scores theo mã lớp
    public List<Scores> GetListScoreByIdClass(String idClass) {

        String IdClass;
        String IdStudent;
        Float MidleScore;
        Float FinalScore;
        Float AverageScore;
        List<Scores> LstScore = new ArrayList<Scores>();

        Cursor data = GetScoreByIdClass(idClass);
        data.moveToFirst();
        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            IdClass = data.getString(0);
            IdStudent = data.getString(1);
            MidleScore = data.getFloat(2);
            FinalScore = data.getFloat(3);
            AverageScore = data.getFloat(4);
            LstScore.add(new Scores(IdClass, IdStudent, MidleScore, FinalScore, AverageScore));
        }
        data.moveToFirst();
        data.close();
        return LstScore;
    }

    //lấy danh sách mã sinh viên không có trong lớp
    public List<String> GetListIdStudentNotInScore(String idClass) {
        List<String> lstIdStd = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("SELECT student.id_student FROM student WHERE student.id_student NOT IN (SELECT scores.id_student FROM scores WHERE scores.id_class ='" + idClass + "')", null);
        res.moveToFirst();
        if (res != null) {
            for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
                lstIdStd.add(res.getString(0));
            }
            res.moveToFirst();
            res.close();
            return lstIdStd;
        }
        return lstIdStd;
    }

    //Lấy danh sách mã sv có trong lớp
    public List<String> GetListIdStudentInScore(String idClass) {
        List<String> lstIdStd = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor res = database.rawQuery("SELECT scores.id_student FROM scores WHERE scores.id_class ='" + idClass + "'", null);
        res.moveToFirst();
        if (res != null) {
            for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
                lstIdStd.add(res.getString(0));
            }
            res.moveToFirst();
            res.close();
            return lstIdStd;
        }
        return lstIdStd;
    }

    //thêm sinh viên vào lớp
    public boolean AddScores(Context context, Scores scores) {
        String idClass = scores.getId_class();
        String idStudent = scores.getId_student();
        float middlscores = scores.getMiddle();
        float finalscores = scores.getEnd();
        float averagescore = scores.getAverage();

        //thêm vào bảng scores
        ContentValues valuesScores = new ContentValues();
        valuesScores.put("id_class", idClass);
        valuesScores.put("id_student", idStudent);
        valuesScores.put("middle_scores", middlscores);
        valuesScores.put("final_scores", finalscores);
        valuesScores.put("average_scores", averagescore);

        SQLiteDatabase database = this.getWritableDatabase();

        //thêm vào scores và cập nhập classes
        if (database.insert("scores", null, valuesScores) > 0 && UpdateNumberStdInClassAfterInsert(context, idClass)) {
            database.close();
            return true;
        }
        database.close();
        return false;
    }

    //Cập nhật thông tin số sv trong lớp học sau khi thêm sinh viên vào lớp
    public boolean UpdateNumberStdInClassAfterInsert(Context context, String idClass) {
        //lấy môn học
        Classes classes = new Classes();
        Classes_Controler controller = new Classes_Controler(context);
        classes = controller.GetClassById(idClass);

        //số lượng lớp + 1
        if (classes.getNumberStudent() < classes.getMaxStudent()) {
            int numstudent = classes.getNumberStudent() + 1;

            ContentValues valueSubject = new ContentValues();
            valueSubject.put("number_student", numstudent);

            SQLiteDatabase database = this.getWritableDatabase();
            if (database.update("classes", valueSubject, "id_class = ?", new String[]{idClass}) > 0) {
                return true;
            }
        }
        return false;
    }



    //xóa sinh viên khỏi lớp
    public boolean DeleteScoresByIdStudent(Context context,String Idstd, String idClass)
    {
        String[]lstValue = {idClass,Idstd};
        SQLiteDatabase database = this.getWritableDatabase();
        if (database.delete("scores", "id_class = ? AND id_student =?", lstValue) > 0 && UpdateNumberStdInClassAfterDelete(context,idClass))
        {
            database.close();
            return true;
        }
        database.close();
        return false;
    }

    //Cập nhật thông tin số sv trong lớp học sau khi xóa sinh viên vào lớp
    public boolean UpdateNumberStdInClassAfterDelete(Context context, String idClass) {
        //lấy môn học
        Classes classes = new Classes();
        Classes_Controler controller = new Classes_Controler(context);
        classes = controller.GetClassById(idClass);

        //số lượng lớp + 1
        if (classes.getNumberStudent() > 0) {
            int numstudent = classes.getNumberStudent() - 1;

            ContentValues valueSubject = new ContentValues();
            valueSubject.put("number_student", numstudent);

            SQLiteDatabase database = this.getWritableDatabase();
            if (database.update("classes", valueSubject, "id_class = ?", new String[]{idClass}) > 0) {
                return true;
            }
        }
        return false;
    }


    //Cập nhật lại điểm trong bảng scores
    public boolean UpdateScore(Context context, Scores scores) {
        String idClass = scores.getId_class();
        String idStudent = scores.getId_student();
        float middlscores = scores.getMiddle();
        float finalscores = scores.getEnd();
        float averagescore = scores.getAverage();

        String[] IdclassAndStudent = {idClass,idStudent};

        //thêm vào bảng scores
        ContentValues valuesScores = new ContentValues();
        valuesScores.put("middle_scores", middlscores);
        valuesScores.put("final_scores", finalscores);
        valuesScores.put("average_scores", averagescore);

        SQLiteDatabase database = this.getWritableDatabase();

        //update vào scores
        if (database.update("scores", valuesScores,"id_class = ? AND id_student = ?",IdclassAndStudent) > 0 ) {
            database.close();
            return true;
        }
        database.close();
        return false;
    }


}
