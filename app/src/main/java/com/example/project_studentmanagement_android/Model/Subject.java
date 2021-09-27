package com.example.project_studentmanagement_android.Model;

public class Subject {

    private String IdSubject;
    private String SubjectName;
    private int Credits;
    private int NumberClass;
    private int MaxClass;

    public Subject(){

    }


    public Subject(String idSubject, String subjectName, int credits, int numberClass, int maxClass) {
        IdSubject = idSubject;
        SubjectName = subjectName;
        Credits = credits;
        NumberClass = numberClass;
        MaxClass = maxClass;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public String getIdSubject() {
        return IdSubject;
    }

    public int getCredits() {
        return Credits;
    }

    public int getNumberClass() {
        return NumberClass;
    }

    public int getMaxClass() {
        return MaxClass;
    }

    public void setIdSubject(String idSubject) {
        IdSubject = idSubject;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public void setCredits(int credits) {
        Credits = credits;
    }

    public void setNumberClass(int numberClass) {
        NumberClass = numberClass;
    }

    public void setMaxClass(int maxClass) {
        MaxClass = maxClass;
    }
}
