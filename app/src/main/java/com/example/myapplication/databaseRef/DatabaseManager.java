package com.example.myapplication.databaseRef;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseManager {
    private DatabaseReference databaseReference;
    private static final String TABLE_STUDENT = "STUDENT";
    private static final String TABLE_TEACHER = "TEACHER";

    public static DatabaseManager getInstance(){
        return new DatabaseManager();
    }

    public DatabaseManager() {
        databaseReference = FirebaseDatabase.getInstance("https://my-application-6d3f6-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
//                                                        https://my-application-6d3f6-default-rtdb.asia-southeast1.firebasedatabase.app/
    }

    public DatabaseReference getTableStudent(){
        return databaseReference
                .child(TABLE_STUDENT);
    }


    public DatabaseReference getTableTeacher(){
        return databaseReference
                .child(TABLE_TEACHER);
    }


    public DatabaseReference getTableStudentById(String id){
        return getTableStudent()
                .child(id);
    }

    public DatabaseReference getTableTeacherById(String id){
        return  getTableTeacher()
                .child(id);
    }
}