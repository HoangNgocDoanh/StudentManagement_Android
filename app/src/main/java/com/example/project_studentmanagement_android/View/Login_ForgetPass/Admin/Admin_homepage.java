package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project_studentmanagement_android.MainActivity;
import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.GlobalSubject;
import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.Admin_ClassPage;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.ClassesAdapter;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.StudentManager.StudentManager_AddStudent;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Subject.SubjectAdapter;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Subject.Subject_AddSubject;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Subject.Subject_DeleteSubject;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Subject.Subject_EditSubject;
import com.example.project_studentmanagement_android.controller.Classes_Controler;
import com.example.project_studentmanagement_android.controller.Database;
import com.example.project_studentmanagement_android.controller.Subject_Controller;

import java.util.ArrayList;
import java.util.List;

public class Admin_homepage extends AppCompatActivity {

    TextView math, web, moblie;
    ListView lstSubject;
    SubjectAdapter adapter;
    List<Subject> subjectsList;
    Subject_Controller controler;
    Database data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);
        Anhxa();

//        data = new Database(this);
//        data.InsertAllBaseData();


        subjectsList = controler.GetListSubjects();

        adapter = new SubjectAdapter(this, R.layout.activity_subject_view, subjectsList);
        lstSubject.setAdapter(adapter);

        lstSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //set subject toàn cục
                GlobalSubject.setIdSubject(subjectsList.get(position).getIdSubject());
                GlobalSubject.setNameSubject(subjectsList.get(position).getSubjectName());

                Intent ClassManage = new Intent(getApplicationContext(), Admin_ClassPage.class);
                startActivity(ClassManage);
            }
        });

    }


    public void Anhxa() {

        controler = new Subject_Controller(this);
        lstSubject = (ListView) findViewById(R.id.Lstsubject);
        subjectsList = new ArrayList<Subject>();
    }


    //tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.admin_homepagemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //sự kiện menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.AccountManagement:

                break;
            case R.id.InsertSubject:
                Intent AddSubjectIntent = new Intent(getApplicationContext(), Subject_AddSubject.class);
                startActivity(AddSubjectIntent);
                break;
            case R.id.DeleteSubject:
                Intent DeleteSubjectIntent = new Intent(getApplicationContext(), Subject_DeleteSubject.class);
                startActivity(DeleteSubjectIntent);
                break;
            case R.id.EditSubject:
                Intent EditSubjectIntent = new Intent(getApplicationContext(), Subject_EditSubject.class);
                startActivity(EditSubjectIntent);
                break;
            case R.id.StudentMangement:
                Intent StudentMagementIntent = new Intent(getApplicationContext(), StudentManager_AddStudent.class);
                startActivity(StudentMagementIntent);
                break;
            case R.id.LogOut:
                Intent Login = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Login);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}