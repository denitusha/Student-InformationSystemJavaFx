package com.example.demo4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Completed_course;
import model.DataSource;
import model.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentAfterLoginController {

    @FXML
    private Label welcomeText;
    @FXML
    private Button profileButton;
    @FXML
    private Button transcriptButton;
    @FXML
    private Button CourseSelectionButton;
    @FXML
    private Button ongoingButton;
    @FXML
    private Button completedButton;

    @FXML
    public void initialize(){

        Student student = Session.getInstance().getCurrentUser();
        welcomeText.setText("Welcome " + student.getName() + " " + student.getSurname());
    }

    @FXML
    private void changeToProfile() {
        try {
            changeScene("profile.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void changeToSelection() {
        try {
            changeScene("courseSelection.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void download() {
        FileWriting.writeToFile(DataSource.getInstance().queryCompletedCourses(Session.getInstance().getCurrentUser().getId()));
    }
    @FXML
    private void changeToCompleted() {
        try {
            changeScene("Completedcourses.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void changeToOngoing() {
        try {
            changeScene("OngoingCourses.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void changeScene(String sceneName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(sceneName));
        Scene scene = new Scene(root);
        Stage stage = (Stage) profileButton.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("");
        stage.show();
    }
}

class FileWriting {
    public static void writeToFile(List<Completed_course> courses) {
        File file = new File("completed_courses.txt");
        try {
            FileWriter writer = new FileWriter(file);
            for (Completed_course course : courses) {
                writer.write(course.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
