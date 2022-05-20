package com.example.myapplication.model;

import java.util.Date;

public class Attendance {
    private String IdStudent;
    private String name;
    private Date attendanceTime;

    public Attendance(String idStudent, String name, Date attendanceTime) {
        IdStudent = idStudent;
        this.name = name;
        this.attendanceTime = attendanceTime;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "IdStudent='" + IdStudent + '\'' +
                ", name='" + name + '\'' +
                ", attendanceTime=" + attendanceTime +
                '}';
    }

    public Attendance() {
    }

    public String getIdStudent() {
        return IdStudent;
    }

    public void setIdStudent(String idStudent) {
        IdStudent = idStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Date attendanceTime) {
        this.attendanceTime = attendanceTime;
    }
}
