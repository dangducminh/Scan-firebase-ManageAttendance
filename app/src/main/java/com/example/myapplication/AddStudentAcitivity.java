package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.databaseRef.DatabaseManager;
import com.example.myapplication.model.Student;
import com.example.myapplication.util.DateUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;


import java.text.ParseException;

public class AddStudentAcitivity extends AppCompatActivity {

    private EditText txtSetNameStudent;
    private EditText txtSetMailStudent;
    private EditText txtSetDateStudent;
    private EditText txtSetSexStudent;
    private EditText txtSetClassStudent;
    private EditText txtSetYearStudent;

    private EditText txtChangeKey;

    private Button btnSetStudent;

    FirebaseFirestore db = FirebaseFirestore.getInstance();



    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_acitivity);

        txtSetNameStudent = findViewById(R.id.txtSetNameStudent);
        txtSetMailStudent = findViewById(R.id.txtSetMailStudent);
        txtSetDateStudent = findViewById(R.id.txtSetDateStudent);
        txtSetSexStudent = findViewById(R.id.txtSetSexStudent);
        txtSetClassStudent = findViewById(R.id.txtSetClassStudent);
        txtSetYearStudent = findViewById(R.id.txtSetYearStudent);
        btnSetStudent = findViewById(R.id.btnSetStudent);

        txtChangeKey = findViewById(R.id.txtChangeKey);

        mAuth = FirebaseAuth.getInstance();


        btnSetStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveStudent();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void saveStudent() throws ParseException {

        Student student = new Student(txtChangeKey.getText().toString(),
                txtSetNameStudent.getText().toString(),
                DateUtil.toBirthOfDate(txtSetDateStudent.getText().toString()),
                txtSetMailStudent.getText().toString(),
                txtSetSexStudent.getText().toString(),
                txtSetClassStudent.getText().toString(),
                txtSetYearStudent.getText().toString());



        DatabaseManager
                .getInstance()
                .getTableStudentById(student.getIdStudent())
                .setValue(student);
    }


}