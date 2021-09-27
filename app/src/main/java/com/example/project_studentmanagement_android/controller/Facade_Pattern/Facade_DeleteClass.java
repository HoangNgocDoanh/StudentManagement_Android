package com.example.project_studentmanagement_android.controller.Facade_Pattern;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.controller.Database;
import com.example.project_studentmanagement_android.controller.Subject_Controller;

public class Facade_DeleteClass extends Database implements FacadeDeletedata {


    public Facade_DeleteClass(Context context) {
        super(context);
    }

    @Override
    public boolean DeleteSubject(Context context, String SubId, int numclass) {
        return false;
    }

    @Override
    public boolean DeleteClass(Context context, String ClassId, String Idsub, int NumStudent) {
        SQLiteDatabase database = this.getWritableDatabase();
        String[] lstValue = {ClassId};
        //xóa lớp và cập nhật số lớp trong môn học ,  xóa teach
        if (database.delete("classes", "id_class = ?", lstValue) > 0 && UpdateSubject(context, Idsub) && database.delete("teach", "id_class = ?", lstValue) > 0) {
            //kiểm tra lớp có ai học ko
            if (NumStudent > 0) {
                //xóa score
                if (database.delete("scores", "id_class = ?", lstValue) > 0) {
                    database.close();
                    return true;
                } else {
                    database.close();
                    return false;
                }
            } else {
                database.close();
                return true;
            }
        } else {
            database.close();
            return false;
        }
    }


    //Cập nhật thông tin số lớp trong môn học
    public boolean UpdateSubject(Context context, String idSub) {
        //lấy môn học
        Subject subject = new Subject();
        Subject_Controller controller = new Subject_Controller(context);
        subject = controller.GetSubjectsById(idSub);

        //số lượng lớp - 1
        if (subject.getNumberClass() > 0) {
            int numclass = subject.getNumberClass() - 1;

            ContentValues valueSubject = new ContentValues();
            valueSubject.put("number_class", numclass);

            SQLiteDatabase database = this.getWritableDatabase();
            if (database.update("subject", valueSubject, "id_subject = ?", new String[]{idSub}) > 0) {
                return true;
            }
        }
        return false;
    }
}
