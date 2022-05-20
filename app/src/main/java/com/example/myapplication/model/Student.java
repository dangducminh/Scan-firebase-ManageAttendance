package com.example.myapplication.model;

import java.util.Date;
import java.util.List;

public class Student {
    private String idStudent;
    private String name;
    private Date dateOfBirth;
    private String email;
    private String sex;
    //lop cua sinh vien
    private String classroom;
    //nien khoa cua sinh vien
    private String academicYear;
    //lich su diem danh
    private List<Attendance> attendanceHistory;

    public Student(String idStudent, String name, Date dateOfBirth, String email, String sex, String classroom, String academicYear) {
        this.idStudent = idStudent;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.sex = sex;
        this.classroom = classroom;
        this.academicYear = academicYear;
    }

    public Student() {
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public List<Attendance> getAttendanceHistory() {
        return attendanceHistory;
    }

    public void setAttendanceHistory(List<Attendance> attendanceHistory) {
        this.attendanceHistory = attendanceHistory;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent='" + idStudent + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", classroom='" + classroom + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", attendanceHistory=" + attendanceHistory +
                '}';
    }
}
