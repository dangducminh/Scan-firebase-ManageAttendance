package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowInforStudent extends AppCompatActivity {
    private TextView txtShowNameStudent;
    private TextView txtshowIdStudent;
    private TextView txtShowBirhOfDateStudent;
    private TextView txtShowEmailStudent;
    private TextView txtShowSexStudent;
    private TextView txtShowClassStudent;
    private TextView txtShowAcademicYearStudent;
    private Button btnShowHistoricalAttendanceStudent;

    private String idStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_infor_student);

        initView();

    }

    private void initView() {

        txtShowNameStudent = findViewById(R.id.txtShowNameStudent);
        txtshowIdStudent = findViewById(R.id.txtshowIdStudent);
        txtShowBirhOfDateStudent = findViewById(R.id.txtShowBirhOfDateStudent);
        txtShowEmailStudent = findViewById(R.id.txtShowEmailStudent);
        txtShowSexStudent = findViewById(R.id.txtShowSexStudent);
        txtShowClassStudent = findViewById(R.id.txtShowClassStudent);
        txtShowAcademicYearStudent = findViewById(R.id.txtShowAcademicYearStudent);
        btnShowHistoricalAttendanceStudent = findViewById(R.id.btnShowHistoricalAttendanceStudent);


        Bundle bundle = getIntent().getExtras();
        idStudent = bundle.getString("idStudent");
        txtShowNameStudent.setText(bundle.getString("name"));
        txtshowIdStudent.setText(bundle.getString("idStudent"));
        txtShowBirhOfDateStudent.setText(bundle.getString("dateOfBirth"));
        txtShowEmailStudent.setText(bundle.getString("email"));
        txtShowSexStudent.setText(bundle.getString("sex"));
        txtShowClassStudent.setText(bundle.getString("classroom"));
        txtShowAcademicYearStudent.setText(bundle.getString("academicYear"));

        btnShowHistoricalAttendanceStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("minh",idStudent);
                Intent intent = new Intent(ShowInforStudent.this,ViewHistoryAttendanceStudent.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("ID_STUDENT",idStudent);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
    }
}