package com.example.project_studentmanagement_android.View.Login_ForgetPass.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_studentmanagement_android.Model.GlobalSchedule;
import com.example.project_studentmanagement_android.R;

public class Schedule extends AppCompatActivity {


    TextView Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Anhxa();

        Monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalSchedule.setSchedule("Thứ 2");
                Intent intent = new Intent(getApplicationContext(), Student_Schedule_Detail.class);
                startActivity(intent);
                finish();
            }
        });

        Tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalSchedule.setSchedule("Thứ 3");
                Intent intent = new Intent(getApplicationContext(), Student_Schedule_Detail.class);
                startActivity(intent);
                finish();
            }
        });

        Wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Schedule.this, "4", Toast.LENGTH_SHORT).show();
            }
        });

        Thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Schedule.this, "5", Toast.LENGTH_SHORT).show();
            }
        });

        Friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Schedule.this, "6", Toast.LENGTH_SHORT).show();
            }
        });

        Saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Schedule.this, "7", Toast.LENGTH_SHORT).show();
            }
        });

        Sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Schedule.this, "Monday", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Anhxa() {
        Monday = (TextView) findViewById(R.id.Monday);
        Tuesday = (TextView) findViewById(R.id.Tuesday);
        Wednesday = (TextView) findViewById(R.id.Wednesday);
        Thursday = (TextView) findViewById(R.id.Thursday);
        Friday = (TextView) findViewById(R.id.Friday);
        Saturday = (TextView) findViewById(R.id.Saturday);
        Sunday = (TextView) findViewById(R.id.Sunday);
    }
}