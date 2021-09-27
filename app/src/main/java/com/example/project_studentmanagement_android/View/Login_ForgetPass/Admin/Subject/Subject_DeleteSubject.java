package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Subject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.IpSecManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Admin_homepage;
import com.example.project_studentmanagement_android.controller.Subject_Controller;

import java.util.ArrayList;
import java.util.List;

public class Subject_DeleteSubject extends AppCompatActivity {

    Spinner spinIdSub;
    EditText  edtNameSub, edtCredits, edtMaxClass, edtClassOnSub;
    ImageView imgGoBack;
    Button btnDeleteSub;
    Subject_Controller controller;
    List<String> lstSub;
    String IdSubjectChoosed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject__delete_subject);
        Anhxa();

        //tạo spinner id môn
        createSpinner();

        //set sự kiện click item spinner
        spinIdSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //lấy thông tin môn có id được chọn
                Subject subject =new Subject();
                subject =  controller.GetSubjectsById(lstSub.get(position).toString());

                //gán thông tin ra màng hình
                IdSubjectChoosed = subject.getIdSubject();
                edtNameSub.setText(subject.getSubjectName().toString().trim());
                edtCredits.setText(String.valueOf(subject.getCredits()));
                edtMaxClass.setText(String.valueOf(subject.getMaxClass()));
                edtClassOnSub.setText(String.valueOf(subject.getNumberClass()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_homepage.class);
                startActivity(intent);
                finish();
            }
        });


        btnDeleteSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.DeleteSubject(getApplicationContext(),IdSubjectChoosed,Integer.parseInt(edtClassOnSub.getText().toString()));
                edtNameSub.setText("");
                edtCredits.setText("");
                edtMaxClass.setText("");
                edtClassOnSub.setText("");
                createSpinner();
            }
        });



    }


    public void Anhxa()
    {
        spinIdSub = (Spinner) findViewById(R.id.spinner_IdSubject);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        btnDeleteSub = (Button) findViewById(R.id.btnDeleteSubject);
        controller = new Subject_Controller(this);
        lstSub= new ArrayList<String>();

        edtNameSub = (EditText) findViewById(R.id.edtNameSubject);
        edtCredits = (EditText) findViewById(R.id.edtCreditsSubject);
        edtMaxClass = (EditText) findViewById(R.id.edtMaxClassSubject);
        edtClassOnSub = (EditText) findViewById(R.id.edtClassOnSubject);
    }

    //tạo spinner
    public void createSpinner()
    {
        lstSub = controller.GetListIdSubjects();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lstSub);
        spinIdSub.setAdapter(arrayAdapter);
    }

}