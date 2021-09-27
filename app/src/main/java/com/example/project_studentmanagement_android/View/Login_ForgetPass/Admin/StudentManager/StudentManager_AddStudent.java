package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.StudentManager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.Student;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Student.Profile;
import com.example.project_studentmanagement_android.controller.Profile_controller;

import java.util.Calendar;

public class StudentManager_AddStudent extends AppCompatActivity {

    EditText Id;
    EditText Name;
    EditText Phone;
    EditText Address;
    Button Birthday;
    RadioButton Male;
    RadioButton Female;
    Button Add;
    String Student_user;

    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manager__add_student);

        initDatePicker();
        Anhxa();

        Profile_controller profile_controller = new Profile_controller(this);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String student_name = Name.getText().toString().trim();
                String student_address = Address.getText().toString().trim();
                String student_gender;
                if (Male.isChecked())
                {
                    student_gender = "Nam";
                }
                else
                {
                    student_gender = "Nữ";
                }
                String student_phone = Phone.getText().toString().trim();
                String student_birthday = Birthday.getText().toString().trim();
                String id_user = "";
                String id_student = Id.getText().toString().trim();
                Student student = new Student(id_user, id_student, student_gender, student_address, student_birthday, student_name, student_phone);

                Boolean checkIdStudent = profile_controller.CheckSubjectId(id_student);
                if (checkIdStudent == true) {
                    Boolean update = profile_controller.AddStudent(student);

                    if (update == true) {
                        Toast.makeText(StudentManager_AddStudent.this, "Thêm sinh viên thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(StudentManager_AddStudent.this, "Thêm sinh viên thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(StudentManager_AddStudent.this, "Đã tồn tại sinh viên", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                Birthday.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year)
    {
        return  day + "/" + month + "/" + year;
    }


    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    private void Anhxa() {
        Id = (EditText) findViewById(R.id.id_student);
        Name = (EditText) findViewById(R.id.name);
        Phone = (EditText) findViewById(R.id.Phone);
        Address = (EditText) findViewById(R.id.address);
        Birthday = (Button) findViewById(R.id.btnBirthday);
        Add = (Button) findViewById(R.id.btnEdit);
        Male = (RadioButton) findViewById(R.id.rdbMale);
        Female = (RadioButton) findViewById(R.id.rdbFemal);

        Student_user = getIntent().getStringExtra("username");

    }

}