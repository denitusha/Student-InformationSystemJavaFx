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
import javafx.stage.Stage;
import model.CurrentCourse;
import model.DataSource;
import model.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ongoingController {

    @FXML
    private TableView<CurrentCourse> currentCourseTable;
    @FXML
    private Button delete;
    private ObservableList<CurrentCourse> courses = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        listCurrentCourses();

        delete.disableProperty().bind(currentCourseTable.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    public void listCurrentCourses() {
        Task<ObservableList<CurrentCourse>> task  = new GetCurrentCoursesTask();
        currentCourseTable.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }


    @FXML
    private void removeCourse() {
        final CurrentCourse currentCourse = (CurrentCourse) currentCourseTable.getSelectionModel().getSelectedItem();
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                DataSource.getInstance().deleteCurrentCourse(currentCourse.getCode());

                return null;
            }
        };
        task.setOnSucceeded(e -> {
            listCurrentCourses();
        });
        new Thread(task).start();
    }
    @FXML
    private void changeScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("studentAfterLogin.fxml"));
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

class GetCurrentCoursesTask extends Task {

    Student student = Session.getInstance().getCurrentUser();


    @Override
    protected ObservableList<CurrentCourse> call() throws Exception {
        List<CurrentCourse> Currentcourses = new ArrayList<>();
        Currentcourses = DataSource.getInstance().queryCurrentCourses(student.getId());
        Session.getInstance().setCurrentCourses(Currentcourses);
        return FXCollections.observableArrayList(Currentcourses);
    }
}