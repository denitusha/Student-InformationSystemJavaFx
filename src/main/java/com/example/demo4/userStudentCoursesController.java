package com.example.demo4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.CurrentCourse;
import model.DataSource;

import java.io.IOException;

public class userStudentCoursesController {


    @FXML
    private TableView<CurrentCourse> currentCourseTable;
    @FXML
    private Button edit;
    @FXML
    private TextField midterm;

    @FXML
    private TextField finalExam;

    @FXML
    private TextField midtermPercentage;

    @FXML
    private TextField finalPercentage;

    @FXML
    private Button ok;

    @FXML
    private Button finalise;



    private ObservableList<CurrentCourse> courses = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        listCurrentCourses();
        midterm.setVisible(false);
        finalExam.setVisible(false);
        midtermPercentage.setVisible(false);
        finalPercentage.setVisible(false);
        ok.setVisible(false);
        edit.disableProperty().bind(currentCourseTable.getSelectionModel().selectedItemProperty().isNull());
        finalise.disableProperty().bind(currentCourseTable.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    public void listCurrentCourses() {
        Task<ObservableList<CurrentCourse>> task  = new GetCurrentCoursesTask();
        currentCourseTable.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }

    @FXML
    public void updateCourse(){
        final CurrentCourse course = (CurrentCourse) currentCourseTable.getSelectionModel().getSelectedItem();
        midterm.setVisible(true);
        finalExam.setVisible(true);
        midtermPercentage.setVisible(true);
        finalPercentage.setVisible(true);
        ok.setVisible(true);
        ok.setOnAction(e ->{
            course.setMidterm(Float.parseFloat(midterm.getText()));
            course.setFinalExam(Float.parseFloat(finalExam.getText()));
            course.setMidtermPercentage(Float.parseFloat(midtermPercentage.getText()));
            course.setFinalPercentage(Float.parseFloat(finalPercentage.getText()));
            DataSource.getInstance().updateCurrentCourse(course);
            listCurrentCourses();
        });
    }

    @FXML
    public void finaliseCourse(){
        final CurrentCourse course = (CurrentCourse) currentCourseTable.getSelectionModel().getSelectedItem();
        DataSource.getInstance().InsertCompletedCourse(course);
        listCurrentCourses();
    }

    @FXML
    private void changeScene() {
        //Session.getInstance().setCurrentUser(null);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userStudents.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) currentCourseTable.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



