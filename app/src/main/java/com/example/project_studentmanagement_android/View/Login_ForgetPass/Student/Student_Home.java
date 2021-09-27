package com.example.project_studentmanagement_android.View.Login_ForgetPass.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_studentmanagement_android.Model.GlobalStudentName;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.controller.Singleton.ManagerUser;

public class Student_Home extends AppCompatActivity {
    TextView txtStudentName;
    String name;
    ImageView Personal;
    ImageView Calender;
    ImageView Count;
    ImageView Subject;
    String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__home);

        Anhxa();

        name = GlobalStudentName.getFlagName();
        txtStudentName.setText(name);

        //Ân vào Personnal
        Personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtra("username",user);
                startActivity(intent);

            }
        });

        //Ấn vào Calender
        Calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Schedule.class);
                startActivity(intent);
                finish();
            }
        });

        //Ấn vào Count
        Count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.project_studentmanagement_android.View.Login_ForgetPass.Student.Count.class);
                //ManagerUser.getInstance().SetGlobalFlagName(txtStudentName.getText().toString());
                startActivity(intent);
            }
        });

        //Ấn vào Subject
        Subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Student_Subject.class);
                startActivity(intent);

            }
        });

    }

    private void Anhxa() {
        txtStudentName = (TextView) findViewById(R.id.StudentName);
        Personal = (ImageView) findViewById(R.id.Personal);
        Calender = (ImageView) findViewById(R.id.Calender);
        Count = (ImageView) findViewById(R.id.Count);
        Subject = (ImageView) findViewById(R.id.subject);
        name = getIntent().getStringExtra("name");
        user = getIntent().getStringExtra("username");
    }
}