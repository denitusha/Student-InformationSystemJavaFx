package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Course {
    private SimpleStringProperty code;
    private SimpleStringProperty name;
    private SimpleStringProperty program;
    private SimpleStringProperty category;
    private SimpleIntegerProperty credits;
    private SimpleStringProperty professor;
    private SimpleStringProperty syllabus;
    private SimpleIntegerProperty semester;

    public Course() {
        this.code = new SimpleStringProperty();
        this.name = new SimpleStringProperty();;
        this.program = new SimpleStringProperty();;
        this.category = new SimpleStringProperty();;
        this.credits = new SimpleIntegerProperty();;
        this.professor = new SimpleStringProperty();;
        this.syllabus = new SimpleStringProperty();;
        this.semester = new SimpleIntegerProperty();;
    }



    public String getCode() {
        return code.get();
    }

    public void setCode(String code) {
        this.code.set(code);
    }

    public String getName() {
        return name.get();
    }



    public void setName(String name) {
        this.name.set(name);
    }

    public String getProgram() {
        return program.get();
    }


    public void setProgram(String program) {
        this.program.set(program);
    }

    public String getCategory() {
        return category.get();
    }



    public void setCategory(String category) {
        this.category.set(category);
    }

    public int getCredits() {
        return credits.get();
    }



    public void setCredits(int credits) {
        this.credits.set(credits);
    }

    public String getProfessor() {
        return professor.get();
    }



    public void setProfessor(String professor) {
        this.professor.set(professor);
    }

    public String getSyllabus() {
        return syllabus.get();
    }



    public void setSyllabus(String syllabus) {
        this.syllabus.set(syllabus);
    }

    public int getSemester() {
        return semester.get();
    }



    public void setSemester(int semester) {
        this.semester.set(semester);
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", program='" + program + '\'' +
                ", category='" + category + '\'' +
                ", credits=" + credits +
                ", professor='" + professor + '\'' +
                ", syllabus='" + syllabus + '\'' +
                '}';
    }
}
