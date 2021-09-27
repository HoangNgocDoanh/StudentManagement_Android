
package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes;

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
import com.example.project_studentmanagement_android.Model.GlobalSubject;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Admin_homepage;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Study.Admin_StudyPage;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Subject.SubjectAdapter;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Subject.Subject_AddSubject;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Subject.Subject_DeleteSubject;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Subject.Subject_EditSubject;
import com.example.project_studentmanagement_android.controller.Classes_Controler;
import com.example.project_studentmanagement_android.controller.Subject_Controller;

import java.util.ArrayList;
import java.util.List;

public class Admin_ClassPage extends AppCompatActivity {
    TextView tvClassName;
    ListView lvClasses;
    ImageView imgGoBack;
    ClassesAdapter adapter;
    List<Classes> lstClass;
    Classes_Controler controller;
    String idSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__class_page);
        Anhxa();

        //set tên môn cho trang
        Intent intent = getIntent();
        tvClassName.setText(GlobalSubject.getNameSubject());
        idSub = GlobalSubject.getIdSubject();

        //load list view
        lstClass = controller.GetListClassesByIdSub(idSub);
        adapter = new ClassesAdapter(this, R.layout.activity_subject_view, lstClass);
        lvClasses.setAdapter(adapter);


        imgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Admin_homepage.class);
                startActivity(intent);
                finish();
            }
        });

        lvClasses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //set subject toàn cục
                GlobalClass.setIdClass(lstClass.get(position).getIdClass());
                GlobalClass.setNameClass(lstClass.get(position).getClassName());
                Intent StudyManage = new Intent(getApplicationContext(), Admin_StudyPage.class);
                startActivity(StudyManage);
            }
        });



    }

    public void Anhxa() {
        tvClassName = (TextView) findViewById(R.id.tvSubjectName);
        lvClasses = (ListView) findViewById(R.id.LvClasses);
        imgGoBack = (ImageView) findViewById(R.id.imgReturn);
        lstClass = new ArrayList<>();
        controller = new Classes_Controler(getApplicationContext());

    }

    //tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.admin_classpagemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //sự kiện menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.InsertClass:
                Intent AddSubjectIntent = new Intent(getApplicationContext(), Classes_AddClass.class);
                startActivity(AddSubjectIntent);
                break;
            case R.id.EditClass:
                Intent DeleteSubjectIntent = new Intent(getApplicationContext(), Classes_EditClass.class);
                startActivity(DeleteSubjectIntent);
                break;
            case R.id.DeleteClass:
                Intent EditSubjectIntent = new Intent(getApplicationContext(), Classes_DeleteClass.class);
                startActivity(EditSubjectIntent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}