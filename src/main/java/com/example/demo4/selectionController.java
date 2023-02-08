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
import model.Course;
import model.DataSource;
import model.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class selectionController {

    @FXML
    private TableView<Course> courseTable;
    @FXML
    private Button select;

    private List<Course> courses;

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    private ObservableList<Course> coursesList = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        listCourses();
        select.disableProperty().bind(courseTable.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    private void listCourses() {
        Task<ObservableList<Course>> task = new GetCoursesTask();

        courseTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }
    @FXML
    private void insertCourse() {
        final Course course = (Course) courseTable.getSelectionModel().getSelectedItem();
        int compulsory = Session.getInstance().getCompulsory();
        int nonTechnical = Session.getInstance().getNonTechnicalElective();
        int technical = Session.getInstance().getTechnicalElective();
        if(course.getCategory() == "Compulsory" && compulsory >=3){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection failed");
            alert.setContentText("You have already selected 3 compulsory courses");
            alert.showAndWait();
            return;
        }
        if(course.getCategory() == "Non-Technical-Elective" && nonTechnical >=1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection failed");
            alert.setContentText("You have already selected 1 Non-Technical-Elective course");
            alert.showAndWait();
            return;
        }
        if(course.getCategory() == "Technical-Elective" && technical >=1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection failed");
            alert.setContentText("You have already selected 1 Technical-Elective course");
            alert.showAndWait();
            return;
        }
        Student student = Session.getInstance().getCurrentUser();
        Task<Boolean> task1 = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return DataSource.getInstance().queryForCurrentCourse(course.getCode());

            }
        };
        new Thread(task1).start();

        task1.setOnSucceeded(e -> {
            if (task1.getValue() == true) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Course has been already selected");
                alert.setContentText("Select another course you want to take");
                alert.showAndWait();
            }else {
                Task<Void> task2 = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        DataSource.getInstance().insertIntoCurrentCourses(course.getCode(), student);
                        return null;
                    }
                };
                task2.setOnSucceeded(e2 -> {
                    if(course.getCategory() == "Non-Technical-Elective"){
                        Session.getInstance().setNonTechnicalElective(nonTechnical+1);
                    }
                    if(course.getCategory() == "Technical-Elective"){
                        Session.getInstance().setNonTechnicalElective(technical+1);
                    }
                    if(course.getCategory() == "Compulsory"){
                        Session.getInstance().setNonTechnicalElective(compulsory+1);
                    }
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Selection Successful");
                    alert.setHeaderText("Course has been selected");
                    alert.showAndWait();
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
            Stage stage = (Stage) courseTable.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class GetCoursesTask extends Task {
    Student student = Session.getInstance().getCurrentUser();

    @Override
    protected ObservableList<Course> call() throws Exception {
        List<Course> courseList = new ArrayList<>();
        courseList = DataSource.getInstance().queryCourses(student.getProgram());

        return FXCollections.observableArrayList(courseList);
    }
}
