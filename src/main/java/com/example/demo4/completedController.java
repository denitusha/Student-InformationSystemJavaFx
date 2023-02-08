package com.example.demo4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Completed_course;
import model.DataSource;
import model.Student;

import java.io.IOException;

public class completedController {

    @FXML
    private TableView<Completed_course> completedCourseTable;

    @FXML
    private Button retake;
    private ObservableList<Completed_course> completedCourses = FXCollections.observableArrayList();
    @FXML
    public void initialize(){
        listCompletedCourses();

        retake.disableProperty().bind(completedCourseTable.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    private void listCompletedCourses() {
        Task<ObservableList<Completed_course>> task  = new GetCompletedCoursesTask();
        completedCourseTable.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }

    @FXML
    private void retakeCourse(){
        if(Session.getInstance().getRetakenCourses() >= 2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection failed");
            alert.setContentText("You have retaken the maximum of courses allowed");
            alert.showAndWait();
            return;
        }
        final Completed_course  completedCourse = (Completed_course) completedCourseTable.getSelectionModel().getSelectedItem();
        Student student = Session.getInstance().getCurrentUser();
        Task<Boolean> task1 = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return DataSource.getInstance().queryForCurrentCourse(completedCourse.getCode());

            }
        };
        new Thread(task1).start();

        task1.setOnSucceeded(e -> {
            if (task1.getValue() == true) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Course has been already selected");
                alert.setContentText("Select another course you want to retake");
                alert.showAndWait();
            }else {
                Task<Void> task2 = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        DataSource.getInstance().insertIntoCurrentCourses(completedCourse.getCode(), student);
                        return null;
                    }
                };
                task2.setOnSucceeded(e2 -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Selection Successful");
                    alert.setHeaderText("Course has been selected");
                    alert.setContentText("You are retaking this course again\n you only have to attend the examns");
                    alert.showAndWait();
                    Session.getInstance().setRetakenCourses(Session.getInstance().getRetakenCourses() + 1);
                });
                task2.setOnFailed(e2 -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Selection failed");
                    alert.setContentText("Please try again");
                    alert.showAndWait();
                });
                new Thread(task2).start();
            }
        });
    }
    @FXML
    private void changeScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("studentAfterLogin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) completedCourseTable.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class GetCompletedCoursesTask extends Task {
    Student student = Session.getInstance().getCurrentUser();
    @Override
    protected ObservableList<Completed_course> call() throws Exception {
        return FXCollections.observableArrayList(DataSource.getInstance().queryCompletedCourses(student.getId()));
    }
}