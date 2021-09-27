package com.example.project_studentmanagement_android.Model;

public class GlobalSubject {
    static public String IdSubject;
    static public String NameSubject;

    public GlobalSubject(){}

    public static void setIdSubject(String idSubject) {
        IdSubject = idSubject;
    }

    public static String getIdSubject() {
        return IdSubject;
    }

    public static String getNameSubject() {
        return NameSubject;
    }

    public static void setNameSubject(String nameSubject) {
        NameSubject = nameSubject;
    }
}
