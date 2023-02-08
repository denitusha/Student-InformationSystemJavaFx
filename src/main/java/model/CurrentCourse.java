package model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CurrentCourse {
    private SimpleIntegerProperty id_student;
    private SimpleStringProperty code;
    private SimpleIntegerProperty semester;
    private SimpleFloatProperty midterm;
    private SimpleFloatProperty  finalExam;
    private SimpleFloatProperty midtermPercentage;

    private SimpleFloatProperty finalPercentage;

    public CurrentCourse(){
        this.id_student = new SimpleIntegerProperty();
        this.code = new SimpleStringProperty();
        this.semester = new SimpleIntegerProperty();
        this.midterm = new SimpleFloatProperty();
        this.finalExam = new SimpleFloatProperty();
        this.midtermPercentage = new SimpleFloatProperty();
        this.finalPercentage = new SimpleFloatProperty();
    }

    public int getId_student() {
        return id_student.get();
    }

    public void setId_student(int id_student) {
        this.id_student.set(id_student);
    }

    public String getCode() {
        return code.get();
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public int getSemester() {
        return semester.get();
    }


    public void setSemester(int semester) {
        this.semester.set(semester);
    }

    public float getMidterm() {
        return midterm.get();
    }



    public void setMidterm(float midterm) {
        this.midterm.set(midterm);
    }

    public float getFinalExam() {
        return finalExam.get();
    }



    public void setFinalExam(float finalExam) {
        this.finalExam.set(finalExam);
    }

    public float getMidtermPercentage() {
        return midtermPercentage.get();
    }



    public void setMidtermPercentage(float midtermPercentage) {
        this.midtermPercentage.set(midtermPercentage);
    }

    public float getFinalPercentage() {
        return finalPercentage.get();
    }


    public void setFinalPercentage(float finalPercentage) {
        this.finalPercentage.set(finalPercentage);
    }

    @Override
    public String toString() {
        return "CurrentCourse{" +
                "id_student=" + id_student +
                ", code='" + code + '\'' +
                ", semester=" + semester +
                ", midterm=" + midterm +
                ", finalExam=" + finalExam +
                ", midtermPercentage=" + midtermPercentage +
                ", finalPercentage=" + finalPercentage +
                '}';
    }
}
