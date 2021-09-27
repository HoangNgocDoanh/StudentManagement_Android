package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.GlobalClass;
import com.example.project_studentmanagement_android.Model.Scores;
import com.example.project_studentmanagement_android.Model.SetIdStudent;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Admin_homepage;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.Admin_ClassPage;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.ClassesAdapter;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.Classes_AddClass;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.Classes_DeleteClass;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.Classes_EditClass;
import com.example.project_studentmanagement_android.controller.Classes_Controler;
import com.example.project_studentmanagement_android.controller.Scores_Controller;

import java.util.ArrayList;
import java.util.List;

public class Admin_StudyPage extends AppCompatActivity {

    TextView tvIdstudent;
    ListView LvStudents;
    ImageView imgGoBack;
    StudyAdapter adapter;
    List<Scores> lstScores;
    Scores_Controller controller;
    String idClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__study_page);
        Anhxa();

        tvIdstudent.setText(GlobalClass.getNameClass());

        lstScores = controller.GetListScoreByIdClass(GlobalClass.getIdClass());
        adapter = new StudyAdapter(getApplicationContext(), R.layout.activity_study_view, lstScores);
        LvStudents.setAdapter(adapter);

        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_ClassPage.class);
                startActivity(intent);
                finish();
            }
        });

        LvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SetIdStudent.setFlagID(lstScores.get(position).getId_student());
                Intent intent = new Intent(getApplicationContext(), Study_StudentInfomation.class);
                startActivity(intent);
            }
        });


    }

    public void Anhxa() {
        tvIdstudent = (TextView) findViewById(R.id.tvClassName);
        LvStudents = (ListView) findViewById(R.id.Lvstudents);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        lstScores = new ArrayList<>();
        controller = new Scores_Controller(getApplicationContext());
    }


    //tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.admin_studypagemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //sự kiện menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.AddStudentInClass:
                Intent AddStudentInClass = new Intent(getApplicationContext(), Study_AddStudentInClass.class);
                startActivity(AddStudentInClass);
                break;
            case R.id.DeleteStudentOutClass:
                Intent DeletestudentOutClass = new Intent(getApplicationContext(), Study_DeletestudentOutClass.class);
                startActivity(DeletestudentOutClass);
                break;
            case R.id.InsertScores:
                Intent InsertScores = new Intent(getApplicationContext(),Study_InsertScores.class);
                startActivity(InsertScores);
                break;
//            case R.id.EditScores:
////                Intent EditSubjectIntent = new Intent(getApplicationContext(), Classes_DeleteClass.class);
////                startActivity(EditSubjectIntent);
//                break;

        }
        return super.onOptionsItemSelected(item);
    }


}