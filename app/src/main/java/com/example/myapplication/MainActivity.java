package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.databaseRef.DatabaseManager;
import com.example.myapplication.model.Attendance;
import com.example.myapplication.model.Student;
import com.example.myapplication.model.Teacher;
import com.example.myapplication.util.DateUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity {
    private Button scanBtn;
    private Button btnShowAttendanceHistory;
    private Button btnSendAttendanceResult;
    private Button btnShowInforStudent;
    private TextView txtShowNameTeacher;
    private TextView txtshowIdStudentFocus;

    private String myId;

    private Button addStudent;

    
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addStudent = findViewById(R.id.addStudent);

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(MainActivity.this, AddStudentAcitivity.class);
                startActivity(main);
            }
        });

        initView();
        mAuth = FirebaseAuth.getInstance();
        myId = FirebaseAuth.getInstance().getUid();
        showInforTeacher();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logOut();
                return true;
            default:
                return false;
        }
    }

    private void initView() {

        scanBtn = findViewById(R.id.btnScan);
        btnShowAttendanceHistory = findViewById(R.id.btnShowAttendanceHistory);
        btnSendAttendanceResult = findViewById(R.id.btnSendAttendanceResult);
        btnShowInforStudent = findViewById(R.id.btnShowInforStudent);
        txtShowNameTeacher = findViewById(R.id.txtShowNameTeacher);
        txtshowIdStudentFocus = findViewById(R.id.txtshowIdStudentFocus);

        btnShowAttendanceHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Chức năng sẽ sớm được cập nhập", Toast.LENGTH_LONG).show();
            }
        });

        btnSendAttendanceResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Chức năng sẽ sớm được cập nhập", Toast.LENGTH_LONG).show();
            }
        });

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });

        btnShowInforStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtshowIdStudentFocus.getText().toString().equals("____________")){
                    Toast.makeText(MainActivity.this,"Bạn cần Scan thẻ sinh viên để xem thông tin sinh viên đó",Toast.LENGTH_LONG).show();
                }else{
                    DatabaseManager
                            .getInstance()
                            .getTableStudentById(txtshowIdStudentFocus.getText().toString())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    Student student = new Student();

                                    student.setAcademicYear(snapshot.child("academicYear").getValue(String.class));
                                    student.setClassroom(snapshot.child("classroom").getValue(String.class));
                                    student.setDateOfBirth(snapshot.child("dateOfBirth").getValue(Date.class));
                                    student.setEmail(snapshot.child("email").getValue(String.class));
                                    student.setIdStudent(snapshot.child("idStudent").getValue(String.class));
                                    student.setName(snapshot.child("name").getValue(String.class));
                                    student.setSex(snapshot.child("sex").getValue(String.class));

                                    Intent viewProfile = new Intent(MainActivity.this, ShowInforStudent.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("name",student.getName());
                                    bundle.putString("idStudent",student.getIdStudent());
                                    bundle.putString("dateOfBirth",DateUtil.toStringBirthOfDate(student.getDateOfBirth()));
                                    bundle.putString("email",student.getEmail());
                                    bundle.putString("sex",student.getSex());
                                    bundle.putString("classroom",student.getClassroom());
                                    bundle.putString("academicYear",student.getAcademicYear());
                                    viewProfile.putExtras(bundle);
                                    startActivity(viewProfile);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                }
            }
        });

    }

    private void showInforTeacher() {
        DatabaseManager
                .getInstance()
                .getTableTeacherById(myId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Teacher teacher = new Teacher();
                        teacher.setEmail(snapshot.child("email").getValue(String.class));
                        teacher.setIdTeacher(snapshot.child("idTeacher").getValue(String.class));

                        txtShowNameTeacher.setText(teacher.getEmail());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void scanCode() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setCaptureActivity(CaptureAct.class);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Scanning code");
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() !=null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //8934994116282
                builder.setMessage(result.getContents());
                Log.i("minh",result.getContents());
                builder.setTitle("Mã sinh viên :");
                builder.setPositiveButton("Scan Lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        scanCode();
                    }
                }).setNegativeButton("Điểm danh", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            Log.i("minh","onclick1");
                            attendance(result.getContents());
                            txtshowIdStudentFocus.setText(result.getContents());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
                AlertDialog dialog =builder.create();
                dialog.show();
            }
            else {
                Toast.makeText(this,"No result",Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    private void attendance(String id) throws ParseException {
        getStudent(id);


    }

    private void updateAttendance(Student student,Attendance attendance) throws ParseException {
        //set attendance for student
        DatabaseManager
                .getInstance()
                .getTableStudentById(student.getIdStudent())
                .child("attendanceHistory")
                .child(DateUtil.toString(attendance.getAttendanceTime()))
                .setValue(attendance);

        //set attendance for teacher

        DatabaseManager
                .getInstance()
                .getTableTeacher()
                .child("attendanceList")
                .child(DateUtil.timePointToString(attendance.getAttendanceTime()))
                .child(attendance.getIdStudent())
                .setValue(attendance);

    }

    private void getStudent(String id){
        Log.i("minh","getStudent1");

        DatabaseManager
                .getInstance()
                .getTableStudentById(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Student student = new Student();

                        student.setAcademicYear(snapshot.child("academicYear").getValue(String.class));
                        student.setClassroom(snapshot.child("classroom").getValue(String.class));
                        student.setDateOfBirth(snapshot.child("dateOfBirth").getValue(Date.class));
                        student.setEmail(snapshot.child("email").getValue(String.class));
                        student.setIdStudent(snapshot.child("idStudent").getValue(String.class));
                        student.setName(snapshot.child("name").getValue(String.class));
                        student.setSex(snapshot.child("sex").getValue(String.class));

                        Log.i("minh",student.toString());
                        Log.i("minh",student.toString());
                        Attendance attendance = new Attendance();
                        try {
                            attendance.setIdStudent(student.getIdStudent());
                            attendance.setName(student.getName());
                            attendance.setAttendanceTime(DateUtil.toDatePointAttendance(DateUtil.toString(Calendar.getInstance().getTime())));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        try {
                            updateAttendance(student,attendance);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

//                        try {
//                            sendMail(attendance,student);
//                        } catch (MessagingException e) {
//                            e.printStackTrace();
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void logOut() {
        mAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(login);
    }

//    private void sendMail(Attendance attendance, Student student) throws MessagingException, UnsupportedEncodingException {
//
//        final String fromEmail = "testuniversity.edu.message@gmail.com";
//        // Mat khai email cua ban
//        final String password = "testuniversity";
//        // dia chi email nguoi nhan
//        final String subject = "Điểm danh thành công";
//        final String body = "Sinh viên: "+student.getName()+" - Mã sinh viên : "+student.getIdStudent()+" - đã điểm danh vào lúc : "+DateUtil.toString(attendance.getAttendanceTime());
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
//        props.put("mail.smtp.port", "587"); //TLS Port
//        props.put("mail.smtp.auth", "true"); //enable authentication
//        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
//        Authenticator auth = new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(fromEmail, password);
//            }
//        };
//        Session session = Session.getInstance(props, auth);
//        MimeMessage msg = new MimeMessage(session);
//        //set message headers
//        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//        msg.addHeader("format", "flowed");
//        msg.addHeader("Content-Transfer-Encoding", "8bit");
//        msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));
//        msg.setReplyTo(InternetAddress.parse(fromEmail, false));
//        msg.setSubject(subject, "UTF-8");
//        msg.setText(body, "UTF-8");
//        msg.setSentDate(new Date());
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(student.getEmail(), false));
//        Transport.send(msg);
//    }

}