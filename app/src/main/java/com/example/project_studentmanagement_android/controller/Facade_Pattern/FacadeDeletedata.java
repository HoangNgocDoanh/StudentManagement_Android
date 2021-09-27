package com.example.project_studentmanagement_android.controller.Facade_Pattern;

import android.content.Context;

import com.example.project_studentmanagement_android.controller.Database;

public interface FacadeDeletedata {
    public boolean DeleteSubject(Context context, String SubId, int numclass);

    public  boolean DeleteClass(Context context, String ClassId, String Idsub, int NumStudent);
}
