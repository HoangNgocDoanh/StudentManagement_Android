package com.example.project_studentmanagement_android.View.Login_ForgetPass.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.project_studentmanagement_android.Model.GlobalStudentName;
import com.example.project_studentmanagement_android.Model.Scores;
import com.example.project_studentmanagement_android.Model.SetIdStudent;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.controller.Scores_Controller;
import com.example.project_studentmanagement_android.controller.Singleton.ManagerUser;

public class Count_Detail extends AppCompatActivity {
    TextView middle, end, Avg, StudentName;
    String name;
    String Id_class;
    Scores scores;
    String Id_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count__detail);

        Anhxa();
        GlobalStudentName managerUser = new GlobalStudentName();
        name = managerUser.getFlagName();
        Id_student = SetIdStudent.getFlagID();
        StudentName.setText(name);

        Scores_Controller scores_controller = new Scores_Controller(this);


        middle.setText(scores_controller.getScores(Id_class, Id_student).getMiddle().toString());
        end.setText(scores_controller.getScores(Id_class, Id_student).getEnd().toString());
        Avg.setText(scores_controller.getScores(Id_class, Id_student).getAverage().toString());




    }

    private void Anhxa() {
        middle = (TextView) findViewById(R.id.dbMid);
        end = (TextView) findViewById(R.id.dbend);
        Avg = (TextView) findViewById(R.id.dbAvg);
        StudentName = (TextView) findViewById(R.id.StudentName);
        Id_class = getIntent().getStringExtra("id_class");
    }
}