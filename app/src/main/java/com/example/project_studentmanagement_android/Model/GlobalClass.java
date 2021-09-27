package com.example.project_studentmanagement_android.Model;

public class GlobalClass {

    static public String IdClass;
    static public String NameClass;


    public GlobalClass(){}

    public static void setIdClass(String idClass) {
        IdClass = idClass;
    }

    public static String getIdClass() {
        return IdClass;
    }

    public static String getNameClass() {
        return NameClass;
    }

    public static void setNameClass(String nameClass) {
        NameClass = nameClass;
    }
}
