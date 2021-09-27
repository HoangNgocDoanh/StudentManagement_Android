package com.example.project_studentmanagement_android.controller.State;

public class RoleState implements State {
    String student = "student";
    String teacher = "teacher";

    @Override
    public String getStudent() {
        return student;
    }

    @Override
    public String getTeacher() {
        return teacher;
    }
}
