/*
Bichoy Sedrak - CSIT551 Mobile Computing Summer 2022
Creating Course registration application
 */
// creating the student class with all fields.
package com.example.courseregistration_sedrakb1;

public class Student {
    String Name;
    String ID;
    String year;
    Integer priority;
    public Student(String name, String ID, String course, Integer priority) {
        this.Name = name;
        this.ID = ID;
        this.year = course;
        this.priority = priority;
    }
    public String getName() {
        return Name;
    }
    public String getID() {
        return ID;
    }
    public String getYear() {
        return year;
    }
    public Integer getPriority() {
        return priority;
    }
}