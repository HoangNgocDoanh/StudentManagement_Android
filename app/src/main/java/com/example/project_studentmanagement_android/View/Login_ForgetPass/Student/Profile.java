package com.example.project_studentmanagement_android.View.Login_ForgetPass.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.controller.ForgetPass_controller;
import com.example.project_studentmanagement_android.controller.Login_controller;
import com.example.project_studentmanagement_android.controller.Profile_controller;

import java.security.PrivateKey;
import java.util.Calendar;

public class Profile extends AppCompatActivity {

    EditText Id;
    EditText Name;
    EditText Phone;
    EditText Address;
    Button Birthday;
    RadioButton Male;
    RadioButton Female;
    Button Edit;
    String Student_user;

    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initDatePicker();
        Anhxa();

        Login_controller login = new Login_controller(this);
        ForgetPass_controller forget = new ForgetPass_controller(this);
        Profile_controller profile_controller = new Profile_controller(this);

        // Xét giá trị cho biến
        String usn = Student_user;
        String id = login.GetIdUser(usn).getId();
        String name = forget.GetPhone(id).getStudent_name();
        String student_id = forget.GetPhone(id).getId_student();
        String birthday = forget.GetPhone(id).getStudent_birthday();
        String phone = forget.GetPhone(id).getStudent_phone();
        String address = forget.GetPhone(id).getStudent_address();
        String gender = forget.GetPhone(id).getStudent_gender();


        Name.setText(name);
        Id.setText(student_id);
        Birthday.setText(birthday);
        Address.setText(address);
        Phone.setText(phone);
        if ( Male.toString().equals(gender)) {
            Female.setChecked(true);
        }else
        {
            Male.setChecked(true);
        }
        Id.setEnabled(false);


        Edit.setOnClickListener(new View.OnClickListener() {
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
                Boolean update = profile_controller.updateProfileStudent(id, student_gender, student_address, student_birthday, student_name, student_phone);

                if (update == true)
                {
                    Toast.makeText(Profile.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Profile.this, "Cập nhật thông tin thất bại", Toast.LENGTH_SHORT).show();
                }
                // profile_controller.updateProfileStudent(id, student_gender, student_address, student_birthday, student_name, student_phone);
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
        Edit = (Button) findViewById(R.id.btnEdit);
        Male = (RadioButton) findViewById(R.id.rdbMale);
        Female = (RadioButton) findViewById(R.id.rdbFemal);

        Student_user = getIntent().getStringExtra("username");

    }


}