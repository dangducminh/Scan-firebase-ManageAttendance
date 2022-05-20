package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.databaseRef.DatabaseManager;
import com.example.myapplication.model.Teacher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AddTeacherActivity extends AppCompatActivity {
    private TextView IdTeacher;
    private TextView txtSetEmailTeacher;
    private TextView txtSetPWTeacher;
    private Button btnAddTeacher;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        txtSetEmailTeacher = findViewById(R.id.txtSetEmailTeacher);
        txtSetPWTeacher = findViewById(R.id.txtSetPWTeacher);
        btnAddTeacher = findViewById(R.id.btnAddTeacher);

        mAuth = FirebaseAuth.getInstance();

        btnAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(txtSetEmailTeacher.getText().toString(),txtSetPWTeacher.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                Log.v("minh","start");
                                Teacher teacher = new Teacher(mAuth.getUid(),txtSetEmailTeacher.getText().toString(),txtSetPWTeacher.getText().toString());
                                saveTeacher(teacher);
                                sendToLogin();
                                finish();
                            }
                        });
            }
        });
    }

    private void saveTeacher(Teacher teacher) {
        DatabaseManager
                .getInstance()
                .getTableTeacherById(teacher.getIdTeacher())
                .setValue(teacher);
        Log.v("minh","end");
    }
    private void sendToLogin() {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }
}