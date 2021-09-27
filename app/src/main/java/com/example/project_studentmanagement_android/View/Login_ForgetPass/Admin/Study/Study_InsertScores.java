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

import java.util.ArrayList;
import java.util.List;

public class Study_InsertScores extends AppCompatActivity {

    Spinner spinidclass, spinidstd;
    EditText edtclassname, edtstdname, edtmiddlescore, edtfinalscore, edtaveragescore;
    ImageView imgGoBack;
    Button btnAddScore;

    Classes_Controler controllerClass;
    List<String> lstIdClass;
    Scores_Controller controllerScore;
    List<String> lstIdStudent;
    Profile_controller controllerProfile;
    Scores scoresInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study__insert_scores);
        Anhxa();

        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_StudyPage.class);
                startActivity(intent);
                finish();
            }
        });

        //tạo spinner id class
        createIdClassSpinner();
        createIdStudentSpinner();

        //set sự kiện click item spinner
        spinidclass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //lấy thông tin môn có id được chọn
                Classes classes = new Classes();
                classes = controllerClass.GetClassById(lstIdClass.get(position).toString());
                //gán thông tin ra màng hình;
                edtclassname.setText(classes.getClassName().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //set sự kiện click item spinner
        spinidstd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //lấy thông tin môn có id được chọn
                Student student = new Student();
                student = controllerProfile.GetStudentByIdStudent(lstIdStudent.get(position).toString());
                //gán thông tin ra màng hình;
                edtstdname.setText(student.getStudent_name().toString());


                //lấy thông tin điểm của sv
                scoresInfo = controllerScore.getScores(GlobalClass.getIdClass(),lstIdStudent.get(position).toString());
                //gán thông tin điểm
                edtmiddlescore.setText(scoresInfo.getMiddle().toString().trim());
                edtfinalscore.setText(scoresInfo.getEnd().toString().trim());
                edtaveragescore.setText(scoresInfo.getAverage().toString().trim());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edtaveragescore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float average = (Float.parseFloat(edtmiddlescore.getText().toString()) + Float.parseFloat(edtfinalscore.getText().toString()))/2;
                edtaveragescore.setText(String.valueOf(average));
            }
        });

        btnAddScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idclass = spinidclass.getSelectedItem().toString().trim();
                String idstudent = spinidstd.getSelectedItem().toString().trim();
                float middlescore = Float.parseFloat(edtmiddlescore.getText().toString().trim());
                float finalscore = Float.parseFloat(edtfinalscore.getText().toString().trim());
                float averagescore = Float.parseFloat(edtaveragescore.getText().toString().trim());
                Scores scores = new Scores(idclass,idstudent,middlescore,finalscore,averagescore);

                if (CheckInput()) {
                    if (controllerScore.UpdateScore(getApplicationContext(),scores)) {
                        Intent intent = new Intent(getApplicationContext(), Admin_StudyPage.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }


    private void Anhxa() {
        spinidclass = (Spinner) findViewById(R.id.spinAddScoresIdClass);
        spinidstd = (Spinner) findViewById(R.id.spinAddScoresIdStudent);
        edtclassname = (EditText) findViewById(R.id.edtAddScoreClassName);
        edtstdname = (EditText) findViewById(R.id.edtAddScoreStudentName);
        edtmiddlescore = (EditText) findViewById(R.id.edtAddScoreMiddleScores);
        edtfinalscore = (EditText) findViewById(R.id.edtAddScoreFinalScore);
        edtaveragescore = (EditText) findViewById(R.id.edtAddScoreAverageScore);
        btnAddScore = (Button) findViewById(R.id.btnAddScore);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);

        lstIdClass = new ArrayList<>();
        lstIdStudent = new ArrayList<>();
        controllerClass = new Classes_Controler(getApplicationContext());
        controllerScore = new Scores_Controller(getApplicationContext());
        controllerProfile = new Profile_controller(getApplicationContext());
        scoresInfo = new Scores();
    }

    //tạo spinner id class
    public void createIdClassSpinner() {
        List<String> lstNull2 = new ArrayList<>();
        lstNull2.add("Không có lớp mới");
        lstIdClass = controllerClass.GetListIDClassesByIdSub(GlobalSubject.getIdSubject());
        if (lstIdClass != null) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lstIdClass);
            spinidclass.setAdapter(arrayAdapter);
        } else {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lstNull2);
            spinidclass.setAdapter(arrayAdapter);
        }

    }

    //tạo spinner id student
    public void createIdStudentSpinner() {
        List<String> lstNull = new ArrayList<>();
        lstNull.add("không có sinh viên mới");
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
        String middlescore = edtmiddlescore.getText().toString().trim();
        String finalscore = edtfinalscore.getText().toString().trim();

        if (nameclass.equals("") || namestd.equals("")||middlescore.equals("")||finalscore.equals("")) {
            Toast.makeText(this, "Information Missing", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(Float.parseFloat(middlescore)<0||Float.parseFloat(finalscore)<0)
        {
            Toast.makeText(this, "Scores too low", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}