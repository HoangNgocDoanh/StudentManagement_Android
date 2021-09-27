package com.example.project_studentmanagement_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.GlobalStudentName;
import com.example.project_studentmanagement_android.Model.SetIdStudent;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Admin_homepage;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.ForgetPassword;

import com.example.project_studentmanagement_android.View.Login_ForgetPass.Student.Student_Home;
import com.example.project_studentmanagement_android.controller.Database;
import com.example.project_studentmanagement_android.controller.ForgetPass_controller;
import com.example.project_studentmanagement_android.controller.Login_controller;
import com.example.project_studentmanagement_android.controller.Singleton.ManagerUser;
import com.example.project_studentmanagement_android.controller.State.RoleState;
import com.example.project_studentmanagement_android.controller.State.SetRole;

public class MainActivity extends AppCompatActivity {

    EditText edtusername,edtpassword;
    ImageView imgViewLogin;
    TextView txtFPass, txtLogin;
    String Id_student;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        //tạo data base
//        Database data = new Database(this);
//        data.InsertAllBaseData();


        // Login Controller
        Login_controller login = new Login_controller(this);
        ForgetPass_controller forget = new ForgetPass_controller(this);

        //Login
        imgViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtusername.getText().toString();
                String pass = edtpassword.getText().toString();

                if (user.equals("") || pass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Fali", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean checkuserpass = login.login(user,pass);
                    if(checkuserpass == true)
                    {
                        SetRole setcolor = new SetRole();
                        setcolor.setState(new RoleState());
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        String usn = edtusername.getText().toString().trim();
                        role = login.GetIdUser(usn).getRole();

                        // đăng nhâp nếu là student
                        if (role.equals(setcolor.getState().getStudent())) {
                            String id = login.GetIdUser(usn).getId();
                            String name = forget.GetPhone(id).getStudent_name();
                            Id_student = forget.GetPhone(id).getId_student();
                            SetIdStudent.setFlagID(Id_student);
                            Intent intent = new Intent(getApplicationContext(), Student_Home.class);
                            intent.putExtra("username", user);
                            GlobalStudentName.setFlagName(name);
                            startActivity(intent);
                        }
                        // đăng nhập nếu là teacher
                        if (role.equals(setcolor.getState().getTeacher())) {
                            Intent intent = new Intent(getApplicationContext(), Admin_homepage.class);
                            startActivity(intent);

                        }
                        else if (role.equals(setcolor.getState().getTeacher()))
                        {
                            Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //forget pass
        txtFPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtusername.getText().toString();
                if (user.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Điền username ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // kiểm tra xem user có tồn tại trong CSDL không
                    Boolean checkuser = login.checkusername(user);
                    if (checkuser == true) {
                        Intent intent = new Intent(MainActivity.this, ForgetPassword.class);
                        intent.putExtra("username",user);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Kiểm tra lại tên đăng nhập", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    //Refer
    public void AnhXa(){
        edtusername     =       (EditText) findViewById(R.id.edtusername);
        edtpassword     =       (EditText) findViewById(R.id.edtpassword);
        imgViewLogin    =       (ImageView) findViewById(R.id.Login_btn);
        txtFPass        =       (TextView) findViewById(R.id.txtFPass);
        txtLogin        =       (TextView) findViewById(R.id.txtLogin);

    }
}