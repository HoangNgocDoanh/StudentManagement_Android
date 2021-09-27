package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.project_studentmanagement_android.Model.SetIdStudent;
import com.example.project_studentmanagement_android.Model.Student;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.Admin_ClassPage;
import com.example.project_studentmanagement_android.controller.Profile_controller;
import com.example.project_studentmanagement_android.controller.Scores_Controller;

public class Study_StudentInfomation extends AppCompatActivity {

    EditText idstd, namestd, phonestd, addressstd, brithdaystd, genderstd;
    ImageView imgGoBack;
    Profile_controller controller;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study__student_infomation);
        Anhxa();

        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        student = controller.GetStudentByIdStudent(SetIdStudent.getFlagID());
        idstd.setText(student.getId_student());
        namestd.setText(student.getStudent_name());
        phonestd.setText(student.getStudent_phone());
        addressstd.setText(student.getStudent_address());
        brithdaystd.setText(student.getStudent_birthday());
        idstd.setText(student.getId_student());
        genderstd.setText(student.getStudent_gender());

    }

    private void Anhxa() {
        idstd = (EditText) findViewById(R.id.edtIdStudnt);
        namestd = (EditText) findViewById(R.id.edtStudentName);
        phonestd = (EditText) findViewById(R.id.edtStudentPhone);
        addressstd = (EditText) findViewById(R.id.edtStudentaddress);
        brithdaystd = (EditText) findViewById(R.id.edtStudentBD);
        genderstd = (EditText) findViewById(R.id.edtStudentGender);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);

        controller = new Profile_controller(getApplicationContext());
        student = new Student();


    }
}