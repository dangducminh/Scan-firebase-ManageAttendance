package com.example.myapplication.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Teacher {
    private String IdTeacher;
    private String email;
    private String password;
    private Map<Date, List<Attendance>> attendanceList;

    public Teacher(String idTeacher, String email,String password) {
        IdTeacher = idTeacher;
        this.email = email;
        this.password = password;
    }

    public Teacher() {
    }

    public String getIdTeacher() {
        return IdTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        IdTeacher = idTeacher;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<Date, List<Attendance>> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(Map<Date, List<Attendance>> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
