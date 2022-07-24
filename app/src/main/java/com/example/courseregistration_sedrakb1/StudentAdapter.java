/*
Bichoy Sedrak - CSIT551 Mobile Computing Summer 2022
Creating Course registration application
 */


// creating the student Adapter for the recycler view.
package com.example.courseregistration_sedrakb1;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    MainActivity mainActivity;
    List<Student> studentList = new ArrayList<>();
    public StudentAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    @NonNull
    @Override public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()) .inflate(R.layout.student_item, parent, false);
        return new MyViewHolder(itemView);
    }
    @SuppressLint("SetTextI18n")

    // to display the student info after insertion.
    @Override public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Student student = studentList.get(position);
        holder.name.setText(student.Name + " (" + student.ID + ")");
        holder.Year.setText(student.year);
        holder.edit.setOnClickListener(new View.OnClickListener() {

            // setting up the edit button.
            @Override public void onClick(View v) {

                mainActivity.edit(student, position);
            }
        });
        // setting up the delete icon
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                mainActivity.Remove(student, position);
            }
        });
    }
    // to get the count for the number of entered students.
    @Override public int getItemCount() {
        return studentList.size();
    }


    private static final String TAG = "StudentAdapter";
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Student> studentList) {
        Log.i(TAG, "setData: "+studentList.size());
        this.studentList.clear();
        this.studentList .addAll(studentList);
        notifyDataSetChanged();
    }

    // setting up MyViewHolder Class.
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, Year;
        ImageView remove, edit;
        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.nameandId);
            Year = view.findViewById(R.id.year);
            remove = view.findViewById(R.id.remove);
            edit = view.findViewById(R.id.edit);
        }
    }
}


//End Bichoy Sedrak