package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;
import com.example.myapplication.model.Attendance;
import com.example.myapplication.util.DateUtil;

import java.util.List;

public class StudentAttendanceAdapter extends RecyclerView.Adapter<StudentAttendanceAdapter.ViewHolder> {

    private Context context;
    private List<Attendance> attendanceList;
    private int layoutRes;

    public StudentAttendanceAdapter(Context context, List<Attendance> attendanceList, int layoutRes) {
        this.context = context;
        this.attendanceList = attendanceList;
        this.layoutRes = layoutRes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutRes,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Attendance attendance = attendanceList.get(position);

        holder.txtNameStudentAttendance.setText(attendance.getName());
        holder.txtTimeForAttendance.setText(DateUtil.toString(attendance.getAttendanceTime()));
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNameStudentAttendance;
        private TextView txtTimeForAttendance;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameStudentAttendance = itemView.findViewById(R.id.txtNameStudentAttendance);
            txtTimeForAttendance = itemView.findViewById(R.id.txtTimeForAttendance);
        }
    }

}
