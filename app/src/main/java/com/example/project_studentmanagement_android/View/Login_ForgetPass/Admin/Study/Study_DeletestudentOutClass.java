package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.GlobalClass;
import com.example.project_studentmanagement_android.Model.GlobalSubject;
import com.example.project_studentmanagement_android.Model.Scores;
import com.example.project_studentmanagement_android.Model.Student;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.controller.Classes_Controler;
import com.example.project_studentmanagement_android.controller.Profile_controller;
import com.example.project_studentmanagement_android.controller.Scores_Controller;
import com.example.project_studentmanagement_android.controller.Subject_Controller;
import com.example.project_studentmanagement_android.controller.Teacher_Controller;

import java.util.ArrayList;
import java.util.List;

public class Study_DeletestudentOutClass extends AppCompatActivity {


    Spinner spinidclass, spinidstd;
    EditText edtclassname, edtstdname;
    ImageView imgGoBack;
    Button btnLeft;

    Classes_Controler controllerClass;
    List<String> lstIdClass;
    Scores_Controller controllerScore;
    List<String> lstIdStudent;
    Profile_controller controllerProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study__deletestudent_out_class);

        Anhxa();

        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_StudyPage.class);
                startActivity(intent);
                finish();
            }
        });

        //t???o spinner id class
        createIdClassSpinner();
        createIdStudentSpinner();

        //set s??? ki???n click item spinner
        spinidclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //l???y th??ng tin m??n c?? id ???????c ch???n
                Classes classes = new Classes();
                classes = controllerClass.GetClassById(lstIdClass.get(position).toString());
                //g??n th??ng tin ra m??ng h??nh;
                edtclassname.setText(classes.getClassName().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //set s??? ki???n click item spinner
        spinidstd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //l???y th??ng tin m??n c?? id ???????c ch???n
                Student student = new Student();
                student = controllerProfile.GetStudentByIdStudent(lstIdStudent.get(position).toString());
                //g??n th??ng tin ra m??ng h??nh;
                edtstdname.setText(student.getStudent_name().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idclass = spinidclass.getSelectedItem().toString().trim();
                String idstudent = spinidstd.getSelectedItem().toString().trim();

                if (CheckInput()) {
                    if (controllerScore.DeleteScoresByIdStudent(getApplicationContext(), idstudent, idclass)) {
                        Intent intent = new Intent(getApplicationContext(), Admin_StudyPage.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    private void Anhxa() {
        spinidclass = (Spinner) findViewById(R.id.spinLeftIdClass);
        spinidstd = (Spinner) findViewById(R.id.spinLeftIdStudent);
        edtclassname = (EditText) findViewById(R.id.edtLeftClassName);
        edtstdname = (EditText) findViewById(R.id.edtLeftStudentName);
        btnLeft = (Button) findViewById(R.id.btnLeftClass);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);

        lstIdClass = new ArrayList<>();
        lstIdStudent = new ArrayList<>();
        controllerClass = new Classes_Controler(getApplicationContext());
        controllerScore = new Scores_Controller(getApplicationContext());
        controllerProfile = new Profile_controller(getApplicationContext());
    }

    //t???o spinner id class
    public void createIdClassSpinner() {
        List<String> lstNull2 = new ArrayList<>();
        lstNull2.add("Kh??ng c?? l???p m???i");
        lstIdClass = controllerClass.GetListIDClassesByIdSub(GlobalSubject.getIdSubject());
        if (lstIdClass != null) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lstIdClass);
            spinidclass.setAdapter(arrayAdapter);
        } else {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lstNull2);
            spinidclass.setAdapter(arrayAdapter);
        }

    }

    //t???o spinner id student
    public void createIdStudentSpinner() {

        List<String> lstNull = new ArrayList<>();
        lstNull.add("kh??ng c?? sinh vi??n m???i");
        lstIdStudent = controllerScore.GetListIdStudentInScore(GlobalClass.getIdClass());
        if (lstIdStudent != null) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lstIdStudent);
            spinidstd.setAdapter(arrayAdapter);
        } else {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lstNull);
            spinidstd.setAdapter(arrayAdapter);
        }
    }

    //Check input
    public boolean CheckInput() {
        String nameclass = edtclassname.getText().toString().trim();
        String namestd = edtstdname.getText().toString().trim();

        if (nameclass.equals("") || namestd.equals("")) {
            Toast.makeText(this, "Information Missing", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}