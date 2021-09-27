package com.example.project_studentmanagement_android.Model;

public class Student {

    String id_user;
    String id_student;
    String student_gender;
    String student_address;
    String student_birthday;
    String student_name;
    String student_phone;

    public Student(String id_user, String id_student, String student_gender, String student_address, String student_birthday, String student_name, String student_phone) {
        this.id_user = id_user;
        this.id_student = id_student;
        this.student_gender = student_gender;
        this.student_address = student_address;
        this.student_birthday = student_birthday;
        this.student_name = student_name;
        this.student_phone = student_phone;
    }

    public Student() {

    }

    public String getId_user() {
        return id_user;
    }

    public String getId_student() {
        return id_student;
    }

    public String getStudent_gender() {
        return student_gender;
    }

    public String getStudent_address() {
        return student_address;
    }

    public String getStudent_birthday() {
        return student_birthday;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getStudent_phone() {
        return student_phone;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }

    public void setStudent_gender(String student_gender) {
        this.student_gender = student_gender;
    }

    public void setStudent_address(String student_address) {
        this.student_address = student_address;
    }

    public void setStudent_birthday(String student_birthday) {
        this.student_birthday = student_birthday;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setStudent_phone(String student_phone) {
        this.student_phone = student_phone;
    }
}
