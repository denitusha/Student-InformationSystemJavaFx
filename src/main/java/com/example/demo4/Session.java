package com.example.demo4;

import model.CurrentCourse;
import model.Student;

import java.util.List;

public class Session {
    private static Session instance;

    public List<CurrentCourse> getCurrentCourses() {
        return currentCourses;
    }

    private int retakenCourses = 0;
    private int compulsory = 0;
    private int nonTechnicalElective = 0;
    private int TechnicalElective = 0;

    public int getCompulsory() {
        return compulsory;
    }

    public void setCompulsory(int compulsory) {
        this.compulsory = compulsory;
    }

    public int getNonTechnicalElective() {
        return nonTechnicalElective;
    }

    public void setNonTechnicalElective(int nonTechnicalElective) {
        this.nonTechnicalElective = nonTechnicalElective;
    }

    public int getTechnicalElective() {
        return TechnicalElective;
    }

    public void setTechnicalElective(int technicalElective) {
        TechnicalElective = technicalElective;
    }

    public int getRetakenCourses() {
        return retakenCourses;
    }

    public void setRetakenCourses(int retakenCourses) {
        this.retakenCourses = retakenCourses;
    }

    public void setCurrentCourses(List<CurrentCourse> currentCourses) {
        this.currentCourses = currentCourses;
    }

    private List<CurrentCourse> currentCourses;
    private Student currentUser;

    private Session() {};

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Student getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Student currentUser) {
        this.currentUser = currentUser;
    }
}
