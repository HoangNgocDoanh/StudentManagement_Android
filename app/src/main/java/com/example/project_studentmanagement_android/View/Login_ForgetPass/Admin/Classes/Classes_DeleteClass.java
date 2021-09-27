package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.GlobalSubject;
import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Admin_homepage;
import com.example.project_studentmanagement_android.controller.Classes_Controler;
import com.example.project_studentmanagement_android.controller.Subject_Controller;

import java.util.ArrayList;
import java.util.List;

public class Classes_DeleteClass extends AppCompatActivity {

    EditText edtIdSub, edtIdTeacher, edtClassName, edtNumberStudent, edtMaxStudent, edtSchedule;
    Spinner spinIdclass;
    ImageView imgGoBack;
    Button btnDeleteClass;

    Classes_Controler controller;
    List<String> lstClass;
    String IdClassChoosed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes__delete_class);
        Anhxa();

        //tạo spinner id class
        createSpinner();

        //set sự kiện click item spinner
        spinIdclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //lấy thông tin môn có id được chọn
                Classes classes = new Classes();
                classes = controller.GetClassById(lstClass.get(position).toString());

                //gán thông tin ra màng hình
                edtIdSub.setText(classes.getIdSubject().toString());
                edtClassName.setText(classes.getClassName().toString());
                edtIdTeacher.setText(classes.getIdTeacher().toString());
                edtMaxStudent.setText(String.valueOf(classes.getMaxStudent()));
                edtNumberStudent.setText(String.valueOf(classes.getNumberStudent()));
                edtSchedule.setText(classes.getTime());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnDeleteClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                String idclass = spinIdclass.getSelectedItem().toString().trim();
                String idSub = edtIdSub.getText().toString().trim();
                int NumStudent = Integer.parseInt(edtNumberStudent.getText().toString());
                if (controller.DeleteClassByID(context,idclass,idSub,NumStudent)) {
                    Intent intent = new Intent(getApplicationContext(), Admin_ClassPage.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_ClassPage.class);
                startActivity(intent);
                finish();
            }
        });

    }


    public void Anhxa() {
        spinIdclass = (Spinner) findViewById(R.id.spinnerIdClass);
        edtIdSub = (EditText) findViewById(R.id.edtIdSubject);
        edtClassName = (EditText) findViewById(R.id.edtClassName);
        edtNumberStudent = (EditText) findViewById(R.id.edtNumberStudent);
        edtMaxStudent = (EditText) findViewById(R.id.edtMaxStudent);
        edtIdTeacher = (EditText) findViewById(R.id.edtIdTeacher);
        edtSchedule = (EditText) findViewById(R.id.edtTime);

        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        btnDeleteClass = (Button) findViewById(R.id.btnDeleteCLASS);

        controller = new Classes_Controler(getApplicationContext());
        lstClass = new ArrayList<String>();
    }

    //tạo spinner id class
    public void createSpinner() {
        lstClass = controller.GetListIDClassesByIdSub(GlobalSubject.getIdSubject());
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lstClass);
        spinIdclass.setAdapter(arrayAdapter);
    }


}