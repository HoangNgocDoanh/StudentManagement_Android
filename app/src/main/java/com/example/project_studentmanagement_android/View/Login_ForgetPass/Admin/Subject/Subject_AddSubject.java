package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Subject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Admin_homepage;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.ChangePass;
import com.example.project_studentmanagement_android.controller.Subject_Controller;

public class Subject_AddSubject extends AppCompatActivity {

    EditText edtIdSub, edtNameSub, edtCredits, edtMaxClass;
    ImageView imgGoBack;
    Button btnAddSub;
    Subject_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject__add_subject);

        Anhxa();
        controller = new Subject_Controller(this);

        btnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idSubject = edtIdSub.getText().toString().trim();
                String subjectName = edtNameSub.getText().toString().trim();
                int credits = Integer.parseInt(edtCredits.getText().toString().trim());
                int numberClass = 0;
                int maxClass = Integer.parseInt(edtMaxClass.getText().toString().trim());

                //kiem tra input
                if (CheckInput()) {
                    //them mon
                    Subject subject = new Subject(idSubject, subjectName, credits, numberClass, maxClass);
                    if (controller.AddSubject(subject)) {
                        Toast.makeText(Subject_AddSubject.this, "Add Subject Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Admin_homepage.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Subject_AddSubject.this, "Error Add Unsuccess", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //check input
    public boolean CheckInput() {
        String idSubject_ = edtIdSub.getText().toString();
        String subjectName_ = edtNameSub.getText().toString();
        String credits_ = edtCredits.getText().toString();
        String maxClass_ = edtMaxClass.getText().toString();

        if (idSubject_.equals("") || subjectName_.equals("") || credits_.equals("") || maxClass_.equals("")) {
            Toast.makeText(this, "Information Missing", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(credits_) >= 10) {
            Toast.makeText(this, "Credits too high", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(maxClass_) >= 10) {
            Toast.makeText(this, "Number of class too high", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    public void Anhxa() {
        edtIdSub = (EditText) findViewById(R.id.edtIdSubject);
        edtNameSub = (EditText) findViewById(R.id.edtNameSubject);
        edtCredits = (EditText) findViewById(R.id.edtCreditsSubject);
        edtMaxClass = (EditText) findViewById(R.id.edtMaxClassSubject);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        btnAddSub = (Button) findViewById(R.id.btnAddSubject);
    }
}