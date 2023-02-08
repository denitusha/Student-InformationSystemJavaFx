package com.example.demo4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Completed_course;
import model.DataSource;
import model.Student;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileController{
    @FXML
    private Label program;
    @FXML
    private Label student_id;
    @FXML
    private Label name;
    @FXML
    private Label surname;
    @FXML
    private Label semester;
    @FXML
    private Label status;
    @FXML
    private Label registration;
    @FXML
    private Label cgpa;
    @FXML
    private Label birthDate;
    @FXML
    private Label birthPlace;
    @FXML
    private Label fatherName;
    @FXML
    private Label fatherSurname;
    @FXML
    private Label motherName;
    @FXML
    private Label motherSurname;
    @FXML
    private Label email;

    private List<Completed_course> courses;



    public void initialize(){
        Student student = Session.getInstance().getCurrentUser();
        courses = DataSource.getInstance().queryCompletedCourses(student.getId());
        Map<String, Completed_course> courseMap = new HashMap<>();
        for (Completed_course completedCourse : courses) {
            String code = completedCourse.getCode();
            if (!courseMap.containsKey(code) || courseMap.get(code).getSemester() < completedCourse.getSemester()) {
                courseMap.put(code, completedCourse);
            }
        }
        float GPA = 0;
        int totalCredits = 0;
        for (Completed_course completedCourse : courseMap.values()) {

            float grade = completedCourse.getGrade();
            float credits = DataSource.getInstance().queryCredits(completedCourse);
            totalCredits += credits;
            float courseGPA;

            if (grade > 90) {
                courseGPA = 4.0f;
            } else if (grade > 85) {
                courseGPA = 3.5f;
            } else if (grade > 80) {
                courseGPA = 3.0f;
            } else if (grade > 75) {
                courseGPA = 2.5f;
            } else if (grade > 70) {
                courseGPA = 2.0f;
            } else if (grade > 65) {
                courseGPA = 1.5f;
            } else if (grade > 60) {
                courseGPA = 1.0f;
            } else {
                courseGPA = 0.0f;
            }

            GPA += credits * courseGPA;
        }
        GPA = GPA/totalCredits;
        Session.getInstance().getCurrentUser().setCgpa(GPA);
        student.setCgpa(GPA);
        program.setText(student.getProgram());
        student_id.setText(String.valueOf(student.getId()));
        name.setText(student.getName());
        surname.setText(student.getSurname());
        semester.setText(String.valueOf(student.getSemester()));
        status.setText(student.getStatus());
        registration.setText(student.getRegistrationDate().toString());
        cgpa.setText(String.valueOf(student.getCgpa()));
        birthDate.setText(student.getBirthDate().toString());
        birthPlace.setText(student.getBirthPlace());
        fatherName.setText(student.getFatherName());
        fatherSurname.setText(student.getFatherSurname());
        motherName.setText(student.getMotherName());
        motherSurname.setText(student.getMotherSurname());
        email.setText(student.getEmail());

    }

    @FXML
    private void changeScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("studentAfterLogin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) name.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
