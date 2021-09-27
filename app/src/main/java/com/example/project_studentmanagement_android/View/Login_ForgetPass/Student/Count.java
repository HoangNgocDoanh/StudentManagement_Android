package com.example.project_studentmanagement_android.View.Login_ForgetPass.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.GlobalStudentName;
import com.example.project_studentmanagement_android.Model.SetIdStudent;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.controller.Classes_Controler;
import com.example.project_studentmanagement_android.controller.Scores_Controller;

import java.util.ArrayList;
import java.util.List;

public class Count extends AppCompatActivity {
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
        setContentView(R.layout.activity_count);

        Anhxa();

        Classes_Controler classes_controler = new Classes_Controler(this);
        Scores_Controller scores_controller = new Scores_Controller(this);
        GlobalStudentName managerUser = new GlobalStudentName();
        name = managerUser.getFlagName();
        StudentName.setText(name);

        String id_student = SetIdStudent.getFlagID();
        classesList = classes_controler.GetListClasses2(id_student);

        StudentAdapter adapter = new StudentAdapter(this, R.layout.activity_student_view, classesList);
        lstClasses.setAdapter(adapter);
        lstClasses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Count_Detail.class);
                intent.putExtra("id_class",classesList.get(position).getIdClass());
                startActivity(intent);
            }
        });
    }
    private void Anhxa() {
        StudentName = (TextView) findViewById(R.id.txtAdminHomeTitle);
        lstClasses = (ListView) findViewById(R.id.LstClasses);
        controler = new Classes_Controler(this);
        classesList = new ArrayList<Classes>();
    }
}