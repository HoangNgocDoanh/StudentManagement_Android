package com.example.project_studentmanagement_android.Model;

public class Classes {
    private String IdSubject;
    private String IdTeacher;
    private String IdClass;
    private String ClassName;
    private int NumberStudent;
    private int MaxStudent;
    private String Time;

    public Classes(){}

    public Classes(String idSubject, String idTeacher, String idClass, String className, int numberStudent, int maxStudent, String time) {
        IdSubject = idSubject;
        IdTeacher = idTeacher;
        IdClass = idClass;
        ClassName = className;
        NumberStudent = numberStudent;
        MaxStudent = maxStudent;
        Time = time;
    }

    public String getClassName() {
        return ClassName;
    }

    public int getNumberStudent() {
        return NumberStudent;
    }

    public String getTime() {
        return Time;
    }

    public String getIdSubject() {
        return IdSubject;
    }

    public String getIdTeacher() {
        return IdTeacher;
    }

    public String getIdClass() {
        return IdClass;
    }

    public int getMaxStudent() {
        return MaxStudent;
    }

    public void setIdSubject(String idSubject) {
        IdSubject = idSubject;
    }

    public void setIdTeacher(String idTeacher) {
        IdTeacher = idTeacher;
    }

    public void setIdClass(String idClass) {
        IdClass = idClass;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public void setNumberStudent(int numberStudent) {
        NumberStudent = numberStudent;
    }

    public void setMaxStudent(int maxStudent) {
        MaxStudent = maxStudent;
    }

    public void setTime(String time) {
        Time = time;
    }
}
