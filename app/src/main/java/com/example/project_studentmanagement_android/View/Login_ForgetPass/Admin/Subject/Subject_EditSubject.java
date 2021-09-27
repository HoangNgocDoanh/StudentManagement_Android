package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Subject;

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

import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Admin_homepage;
import com.example.project_studentmanagement_android.controller.Subject_Controller;

import java.util.ArrayList;
import java.util.List;

public class Subject_EditSubject extends AppCompatActivity {

    Spinner spinIdSub;
    EditText  edtNameSub, edtCredits, edtMaxClass, edtClassOnSub;
    ImageView imgGoBack;
    Button btnEditSub;
    Subject_Controller controller;
    List<String> lstSub;
    String IdSubjectChoosed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject__edit_subject);
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
                IdSubjectChoosed = subject.getIdSubject().trim();
                edtNameSub.setText(subject.getSubjectName().toString().trim());
                edtCredits.setText(String.valueOf(subject.getCredits()));
                edtMaxClass.setText(String.valueOf(subject.getMaxClass()));
                edtClassOnSub.setText(String.valueOf(subject.getNumberClass()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnEditSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idSubject = IdSubjectChoosed;
                String subjectName = edtNameSub.getText().toString().trim();
                int credits = Integer.parseInt(edtCredits.getText().toString().trim());
                int maxClass = Integer.parseInt(edtMaxClass.getText().toString().trim());
                int numclass = Integer.parseInt(edtClassOnSub.getText().toString().trim());

                Subject subject = new Subject(idSubject,subjectName,credits,numclass,maxClass);

                if(CheckInput())
                {
                    if(controller.EditSubject(subject))
                    {
                        Toast.makeText(Subject_EditSubject.this, "Edit Complete", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Admin_homepage.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Subject_EditSubject.this, "Error Edit", Toast.LENGTH_SHORT).show();
                    }
                }
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

    }

    public void Anhxa() {
        spinIdSub = (Spinner) findViewById(R.id.spinIdSubjectEdit);
        edtNameSub = (EditText) findViewById(R.id.edtNameSubject);
        edtCredits = (EditText) findViewById(R.id.edtCreditsSubject);
        edtMaxClass = (EditText) findViewById(R.id.edtMaxClassSubject);
        edtClassOnSub = (EditText) findViewById(R.id.edtClassOnSubject);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        btnEditSub = (Button) findViewById(R.id.btnEditSubject);
        lstSub= new ArrayList<String>();
        controller = new Subject_Controller(this);
    }


    //tạo spinner
    public void createSpinner()
    {
        lstSub = controller.GetListIdSubjects();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lstSub);
        spinIdSub.setAdapter(arrayAdapter);
    }


    //check input
    public boolean CheckInput()
    {
        String classonsub = edtClassOnSub.getText().toString();
        String subjectName_ = edtNameSub.getText().toString();
        String credits_ = edtCredits.getText().toString();
        String maxClass_ = edtMaxClass.getText().toString();

        if (classonsub.equals("") || subjectName_.equals("") || credits_.equals("") || maxClass_.equals("")) {
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
        }
        if(Integer.parseInt(maxClass_)<Integer.parseInt(classonsub)){
            Toast.makeText(this, "Max class can't smaller than Class on study", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }
}