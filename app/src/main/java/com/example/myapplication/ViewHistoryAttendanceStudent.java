package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.myapplication.Adapter.StudentAttendanceAdapter;
import com.example.myapplication.databaseRef.DatabaseManager;
import com.example.myapplication.model.Attendance;
import com.example.myapplication.util.DateUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewHistoryAttendanceStudent extends AppCompatActivity {

    private ArrayList<Attendance> attendanceList = new ArrayList<>() ;
    private StudentAttendanceAdapter studentAttendanceAdapter;
    private RecyclerView rcvListAttendance;

    private String idStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history_attendance_student);

        Bundle bundle = getIntent().getExtras();
        idStudent = bundle.getString("ID_STUDENT");


        rcvListAttendance = findViewById(R.id.rcvListAttendance);

        studentAttendanceAdapter = new StudentAttendanceAdapter(this, attendanceList,R.layout.item_view_attendance);
        rcvListAttendance.setLayoutManager(new LinearLayoutManager(ViewHistoryAttendanceStudent.this,LinearLayoutManager.VERTICAL,false));
        rcvListAttendance.setAdapter(studentAttendanceAdapter);

        DatabaseManager
                .getInstance()
                .getTableStudentById(idStudent)
                .child("attendanceHistory")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        attendanceList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Attendance attendance = dataSnapshot.getValue(Attendance.class);
                            attendanceList.add(attendance);
                            Log.i("minh", attendance.toString());
                        }

                        studentAttendanceAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

}