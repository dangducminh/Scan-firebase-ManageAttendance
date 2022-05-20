package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.databaseRef.DatabaseManager;
import com.example.myapplication.model.Teacher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private TextView emailRegisterTxt;
    private TextView passRegisterTxt;
    private TextView alreadyHaveAccount;
    private Button registerBtn;
    private TextView forgot_password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailRegisterTxt = findViewById(R.id.emailRegisterTxt);
        passRegisterTxt = findViewById(R.id.passRegisterTxt);
        registerBtn = findViewById(R.id.registerBtn);
        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);


        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(emailRegisterTxt.getText().toString(), passRegisterTxt.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                Log.v("minh","start");
                                Teacher teacher = new Teacher(mAuth.getUid(), emailRegisterTxt.getText().toString(), passRegisterTxt.getText().toString());
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