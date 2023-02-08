package com.example.demo4;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.DataSource;
import model.Student;
import model.User;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button loginbutton;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private Label wrongInput;

    @FXML
    public void initialize() {
        loginbutton.disableProperty().bind(Bindings.or(Username.textProperty().isEmpty(), Password.textProperty().isEmpty()));
    }

    @FXML
    public User loginUser(ActionEvent event) throws IOException{
        User user = new User();
        HelloApplication helloApplication = new HelloApplication();
        user = DataSource.getInstance().queryUsers(Username.getText().toString());
        if(user.getPassword() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username");
            alert.setContentText("The username you entered is not valid. Please try again.");
            alert.showAndWait();
            return null;
        }
        if(user.getPassword().equals(Password.getText().toString()) ){

            if(user.getType().equals("student")){

                Student student = DataSource.getInstance().queryStudent(user.getEmail());
                Session.getInstance().setCurrentUser(student);
                changeScene("studentAfterLogin.fxml");
            }else {
                changeScene("userStudents.fxml");
            }

           // Session.getInstance().setStudent();
            return user;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid password");
            alert.setContentText("The password you entered is not valid. Please try again.");
            alert.showAndWait();
            return null;
        }

    }
    private void changeScene(String sceneName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(sceneName));
        Scene scene = new Scene(root);
        Stage stage = (Stage) loginbutton.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("");
        stage.show();
    }
}