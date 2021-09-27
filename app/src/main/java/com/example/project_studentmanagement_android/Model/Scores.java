package com.example.project_studentmanagement_android.Model;

public class Scores {
    String Id_class;
    String Id_student;
    Float middle;
    Float end;
    Float average;

    public Scores(String id_class, String id_student, Float middle, Float end, Float average) {
        Id_class = id_class;
        Id_student = id_student;
        this.middle = middle;
        this.end = end;
        this.average = average;
    }

    public Scores() {

    }

    public String getId_class() {
        return Id_class;
    }

    public String getId_student() {
        return Id_student;
    }

    public Float getMiddle() {
        return middle;
    }

    public Float getEnd() {
        return end;
    }

    public Float getAverage() {
        return average;
    }

    public void setId_class(String id_class) {
        Id_class = id_class;
    }

    public void setId_student(String id_student) {
        Id_student = id_student;
    }

    public void setMiddle(Float middle) {
        this.middle = middle;
    }

    public void setEnd(Float end) {
        this.end = end;
    }

    public void setAverage(Float average) {
        this.average = average;
    }
}
