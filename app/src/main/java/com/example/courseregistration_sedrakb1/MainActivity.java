/*
Bichoy Sedrak - CSIT551 Mobile Computing Summer 2022
Creating Course registration application
 */
package com.example.courseregistration_sedrakb1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// this is the main class that contains all the functions.
public class MainActivity extends AppCompatActivity {

    // setting up the year array.
    String[] year = {"Graduate", "4th Year", "3rd Year", "2nd Year", "1st Year"};
    RecyclerView ListofStudents;
    StudentAdapter Adapter;
    List<Student> studentList = new ArrayList<>();
    FloatingActionButton add;
    DBHelper DB;

    // On create that links all views
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListofStudents = findViewById(R.id.student_list);
        add = findViewById(R.id.add);
        Adapter = new StudentAdapter(this);
        DB = new DBHelper(this);

        // the list view (Recycler view)
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ListofStudents.setLayoutManager(mLayoutManager);
        ListofStudents.setItemAnimator(new DefaultItemAnimator());
        ListofStudents.setAdapter(Adapter);


        // the add button to add a new student
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(true, null, 0);
            }
        });
    }


    // to delete a student from the list
    public void Remove(Student student, int position) {
        studentList.remove(position);
        Adapter.setData(studentList);
    }

    // to edit a student in the list
    public void edit(Student student, int position) {
        showDialog(false, student, position);
    }

    // to show up the dialog to enter the student information
    public void showDialog(final boolean isInsert, Student students, final int position) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.add_or_edit);
        if (isInsert) dialog.setTitle("Add Student");
        else dialog.setTitle("Edit Student");
        final TextInputLayout name = dialog.findViewById(R.id.name);
        final TextInputLayout Id = dialog.findViewById(R.id.studentID);
        final Spinner course = dialog.findViewById(R.id.year);
        Button submit = dialog.findViewById(R.id.add);
        ArrayAdapter spinner = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, year);
        spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        course.setAdapter(spinner);
        if (!isInsert) {
            name.getEditText().setText(students.Name);
            Id.getEditText().setText(students.ID);
            course.setSelection(students.priority);
        }

        //setting up the submit button that confirms the student entry.
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Boolean checkinsertdata = DB.insertstudentdata(name, Id, course);
                if (checkinsertdata == true) {
                    Toast.makeText(MainActivity.this, "New User Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please Insert information!", Toast.LENGTH_LONG).show();
                }
                // validate the entries and show the dialog with to enter student info.
                if (InputValidation(name, Id)) {
                    Student student = new Student(name.getEditText().getText().toString(), Id.getEditText().getText().toString(), course.getSelectedItem().toString(), course.getSelectedItemPosition());
                    if (isInsert) studentList.add(student);
                    else {
                        studentList.get(position).Name = student.Name;
                        studentList.get(position).ID = student.ID;
                        studentList.get(position).year = student.year;
                        studentList.get(position).priority = student.priority;
                    }
                    Collections.sort(studentList, new Comparator<Student>() {
                        public int compare(Student v1, Student v2) {
                            return Long.compare(v1.getPriority(), v2.getPriority());
                        }
                    });
                    Adapter.setData(studentList);
                    dialog.dismiss();
                }
            }

        });
        dialog.show();
    }

    // A method to make sure entries are not empty.
    private boolean InputValidation(TextInputLayout name, TextInputLayout Id) {
        if (name.getEditText().getText().toString().isEmpty()) {
            name.setError("Required");
            return false;
        }
        if (Id.getEditText().getText().toString().isEmpty()) {
            Id.setError("Required");
            return false;
        }
        return true;
    }
}


//End Bichoy Sedrak