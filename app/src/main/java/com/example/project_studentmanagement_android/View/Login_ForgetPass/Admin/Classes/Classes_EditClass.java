package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes;

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
import com.example.project_studentmanagement_android.Model.GlobalSubject;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.controller.Classes_Controler;

import java.util.ArrayList;
import java.util.List;

public class Classes_EditClass extends AppCompatActivity {

    EditText edtIdSub, edtIdTeacher, edtClassname, edtNumberStudent, edtMaxStudent, edtSchedule;
    Spinner spinIdclass;
    ImageView imgGoBack;
    Button btnEditClass;

    Classes_Controler controller;
    List<String> lstClass;
    String IdClassChoosed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes__edit_class);
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
                edtClassname.setText(classes.getClassName().toString());
                edtIdTeacher.setText(classes.getIdTeacher().toString());
                edtMaxStudent.setText(String.valueOf(classes.getMaxStudent()));
                edtNumberStudent.setText(String.valueOf(classes.getNumberStudent()));
                edtSchedule.setText(classes.getTime());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnEditClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckInput())
                {
                    Classes classes = new Classes();
                    classes.setIdClass(spinIdclass.getSelectedItem().toString());
                    classes.setIdTeacher(edtIdTeacher.getText().toString());
                    classes.setIdSubject(edtIdSub.getText().toString());
                    classes.setClassName(edtClassname.getText().toString());
                    classes.setNumberStudent(Integer.parseInt(edtNumberStudent.getText().toString()));
                    classes.setMaxStudent(Integer.parseInt(edtMaxStudent.getText().toString()));
                    classes.setTime(edtSchedule.getText().toString());
                    controller.EditClass(classes);

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
        edtClassname = (EditText) findViewById(R.id.edtClassName);
        edtNumberStudent = (EditText) findViewById(R.id.edtNumberStudent);
        edtMaxStudent = (EditText) findViewById(R.id.edtMaxStudent);
        edtIdTeacher = (EditText) findViewById(R.id.edtIdTeacher);
        edtSchedule = (EditText) findViewById(R.id.edtTime);

        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        btnEditClass = (Button) findViewById(R.id.btnEditClass);

        controller = new Classes_Controler(getApplicationContext());
        lstClass = new ArrayList<String>();
    }

    public boolean CheckInput() {

        String className = edtClassname.getText().toString().trim();
        String maxstudent = edtMaxStudent.getText().toString().trim();
        int stdclass = Integer.parseInt(edtNumberStudent.getText().toString());
        String classTime = edtSchedule.getText().toString().trim();

        if (className.equals("") || maxstudent.equals("") || classTime.equals("")) {
            Toast.makeText(this, "Information Missing", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (Integer.parseInt(maxstudent)<stdclass) {
            Toast.makeText(this, "Max class can't smaller than number of student in class", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    //tạo spinner id class
    public void createSpinner() {
        lstClass = controller.GetListIDClassesByIdSub(GlobalSubject.getIdSubject());
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lstClass);
        spinIdclass.setAdapter(arrayAdapter);
    }
}