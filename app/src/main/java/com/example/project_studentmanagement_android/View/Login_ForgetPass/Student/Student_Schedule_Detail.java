package com.example.project_studentmanagement_android.View.Login_ForgetPass.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.GlobalSchedule;
import com.example.project_studentmanagement_android.Model.GlobalStudentName;
import com.example.project_studentmanagement_android.Model.SetIdStudent;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.controller.Classes_Controler;
import com.example.project_studentmanagement_android.controller.Scores_Controller;

import java.util.ArrayList;
import java.util.List;

public class Student_Schedule_Detail extends AppCompatActivity {

    TextView txtStudentName;
    String name;
    TextView math,web,moblie;
    ListView lstClasses;
    List<Classes> classesList;

    Classes_Controler controler;
    String id_class;
    TextView StudentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__schedule__detail);

        Anhxa();

        Classes_Controler classes_controler = new Classes_Controler(this);
        Scores_Controller scores_controller = new Scores_Controller(this);

        String id_student = SetIdStudent.getFlagID();
        classesList = classes_controler.GetListClasses4(GlobalSchedule.getSchedule());


        ScheduleAdapter adapter = new ScheduleAdapter(this, R.layout.activity_schedule__view, classesList);
        lstClasses.setAdapter(adapter);
    }

    private void Anhxa() {
        lstClasses = (ListView) findViewById(R.id.mondaylist);
        controler = new Classes_Controler(this);
        classesList = new ArrayList<Classes>();
    }
}