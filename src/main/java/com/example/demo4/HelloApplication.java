package com.example.demo4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataSource;

import java.io.IOException;

public class HelloApplication extends Application {
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage =primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
//        LoginController loginController = fxmlLoader.load();
//        loginController.enableButton();
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        DataSource.getInstance().open();
        primaryStage.setTitle("Login Page");

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @Override
    public void init() throws Exception {
        super.init();
        if(!DataSource.getInstance().open()) {
            System.out.println("FATAL ERROR: Couldn't connect to database");
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}