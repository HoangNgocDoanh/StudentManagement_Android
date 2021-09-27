package com.example.project_studentmanagement_android.View.Login_ForgetPass.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.project_studentmanagement_android.Model.GlobalStudentName;
import com.example.project_studentmanagement_android.Model.SetIdStudent;
import com.example.project_studentmanagement_android.Model.Subject;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Subject.SubjectAdapter;
import com.example.project_studentmanagement_android.controller.Classes_Controler;
import com.example.project_studentmanagement_android.controller.Database;
import com.example.project_studentmanagement_android.controller.Scores_Controller;
import com.example.project_studentmanagement_android.controller.Singleton.ManagerUser;
import com.example.project_studentmanagement_android.controller.Subject_Controller;

import java.util.ArrayList;
import java.util.List;

public class Student_Subject extends AppCompatActivity {

    TextView math, web, moblie, studentname;
    ListView lstSubject;
    List<Subject> subjectsList;
    Subject_Controller controler;
    Database data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__subject);
        Anhxa();


        studentname.setText(GlobalStudentName.getFlagName());
        Subject_Controller subject_controller = new Subject_Controller(this);
        subjectsList = subject_controller.GetListSubjects();

        StudentSubjectAdapter adapter = new StudentSubjectAdapter(this, R.layout.activity_student__subject_view, subjectsList);
        lstSubject.setAdapter(adapter);
    }

    public void Anhxa() {
        controler = new Subject_Controller(this);
        lstSubject = (ListView) findViewById(R.id.LstStudentSubject);
        subjectsList = new ArrayList<Subject>();
        studentname = (TextView) findViewById(R.id.txtStudentName);
    }
}