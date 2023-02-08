package model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
    private SimpleIntegerProperty id;
    private SimpleStringProperty program;
    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private SimpleIntegerProperty semester;
    private SimpleStringProperty status;
    private SimpleStringProperty registrationDate;
    private SimpleFloatProperty cgpa;
    private SimpleStringProperty birthDate;
    private SimpleStringProperty birthPlace;
    private SimpleStringProperty fatherName;
    private SimpleStringProperty fatherSurname;
    private SimpleStringProperty motherName;
    private SimpleStringProperty motherSurname;
    private SimpleStringProperty email;

    public Student() {
        this.id = new SimpleIntegerProperty();
        this.program = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.surname = new SimpleStringProperty();
        this.semester = new SimpleIntegerProperty();
        this.status = new SimpleStringProperty();
        this.registrationDate = new SimpleStringProperty();
        this.cgpa = new SimpleFloatProperty();
        this.birthDate = new SimpleStringProperty();
        this.birthPlace = new SimpleStringProperty();
        this.fatherName = new SimpleStringProperty();
        this.fatherSurname = new SimpleStringProperty();
        this.motherName = new SimpleStringProperty();
        this.motherSurname = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getProgram() {
        return program.get();
    }
    public void setProgram(String program) {
        this.program.set(program);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public int getSemester() {
        return semester.get();
    }


    public void setSemester(int semester) {
        this.semester.set(semester);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public float getCgpa() {
        return cgpa.get();
    }
    public void setCgpa(float cgpa) {
        this.cgpa.set(cgpa);
    }

    public String getRegistrationDate() {
        return registrationDate.get();
    }

    public String getBirthDate() {
        return birthDate.get();
    }



    public void setBirthDate(String birthDate) {
        this.birthDate.set(birthDate);
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate.set(registrationDate);
    }

    public String getBirthPlace() {
        return birthPlace.get();
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace.set(birthPlace);
    }

    public String getFatherName() {
        return fatherName.get();
    }

    public void setFatherName(String fatherName) {
        this.fatherName.set(fatherName);
    }

    public String getFatherSurname() {
        return fatherSurname.get();
    }

    public void setFatherSurname(String fatherSurname) {
        this.fatherSurname.set(fatherSurname);
    }

    public String getMotherName() {
        return motherName.get();
    }

    public void setMotherName(String motherName) {
        this.motherName.set(motherName);
    }

    public String getMotherSurname() {
        return motherSurname.get();
    }


    public void setMotherSurname(String motherSurname) {
        this.motherSurname.set(motherSurname);
    }

    public String getEmail() {
        return email.get();
    }


    public void setEmail(String email) {
        this.email.set(email);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", program='" + program + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", semester=" + semester +
                ", status='" + status + '\'' +
                ", registrationDate=" + registrationDate +
                ", cgpa=" + cgpa +
                ", birthDate=" + birthDate +
                ", birthPlace='" + birthPlace + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", fatherSurname='" + fatherSurname + '\'' +
                ", motherName='" + motherName + '\'' +
                ", motherSurname='" + motherSurname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
