package model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Completed_course {
    private SimpleIntegerProperty id_student;
    private SimpleStringProperty code;
    private SimpleIntegerProperty semester;
    private SimpleFloatProperty grade;

    public Completed_course() {
        this.id_student = new SimpleIntegerProperty();
        this.code = new SimpleStringProperty();
        this.semester = new SimpleIntegerProperty();
        this.grade = new SimpleFloatProperty();
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

    public float getGrade() {
        return grade.get();
    }



    public void setGrade(float grade) {
        this.grade.set(grade);
    }

    @Override
    public String toString() {
        return "Completed_course{" +
                "id_student=" + id_student +
                ", code='" + code + '\'' +
                ", semester=" + semester +
                ", grade=" + grade +
                '}';
    }
}
