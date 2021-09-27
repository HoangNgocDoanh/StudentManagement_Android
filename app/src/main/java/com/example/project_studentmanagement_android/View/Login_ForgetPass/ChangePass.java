package com.example.project_studentmanagement_android.View.Login_ForgetPass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.project_studentmanagement_android.MainActivity;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.controller.Database;
import com.example.project_studentmanagement_android.controller.Login_controller;

public class ChangePass extends AppCompatActivity {
    EditText edtnewpass;
    EditText edtConfirmPass;
    Button btnConfirm;
    String user;
    Database database;
    ImageButton imgreturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        Login_controller login = new Login_controller(this);
        Anhxa();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpass = edtnewpass.getText().toString();
                String cfpass = edtConfirmPass.getText().toString();
                if (newpass.equals("") || cfpass.equals("") && newpass != cfpass) {
                    Toast.makeText(ChangePass.this, "Kiểm tra lại mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean updatepass = login.updatepass(user, newpass);
                    if (updatepass == true) {
                        Toast.makeText(ChangePass.this, "Mật khẩu mới đã được lưu", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(ChangePass.this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        imgreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VerifyCode.class);
                startActivity(intent);
            }
        });

    }

    private void Anhxa() {
        edtConfirmPass = (EditText) findViewById(R.id.txtConfirmPass);
        edtnewpass = (EditText) findViewById(R.id.txtNewPass);
        btnConfirm = (Button) findViewById(R.id.btnChangePass);
        imgreturn = (ImageButton) findViewById(R.id.ImgGoBack);

        user = getIntent().getStringExtra("username");

    }
}