package com.example.demo4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DataSource;
import model.Student;

import java.io.IOException;

public class userStudentsListController {

    @FXML
    private TableView<Student> studentTable;
    @FXML
    private Button courses;
    @FXML
    private Button add;

    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    public void initialize(){
        listStudents();
        courses.disableProperty().bind(studentTable.getSelectionModel().selectedItemProperty().isNull());
        update.disableProperty().bind(studentTable.getSelectionModel().selectedItemProperty().isNull());
        delete.disableProperty().bind(studentTable.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    private void listStudents() {
        Task<ObservableList<Student>> task = new GetStudentsTask();
        studentTable.itemsProperty().bind(task.valueProperty());

        new Thread(task).start();
    }

   @FXML
   private void createStudentPopUp() {
       Student student = new Student();
       Stage studentPopUp = new Stage();
       studentPopUp.setTitle("Enter Student Information");
       studentPopUp.initModality(Modality.APPLICATION_MODAL);

       GridPane grid = new GridPane();
       grid.setPadding(new Insets(10, 10, 10, 10));
       grid.setVgap(10);
       grid.setHgap(10);

       Label idLabel = new Label("ID:");
       TextField idField = new TextField();
       GridPane.setConstraints(idLabel, 0, 0);
       GridPane.setConstraints(idField, 1, 0);

       Label programLabel = new Label("Program:");
       TextField programField = new TextField();
       GridPane.setConstraints(programLabel, 0, 1);
       GridPane.setConstraints(programField, 1, 1);

       Label nameLabel = new Label("Name:");
       TextField nameField = new TextField();
       GridPane.setConstraints(nameLabel, 0, 2);
       GridPane.setConstraints(nameField, 1, 2);

       Label surnameLabel = new Label("Surname:");
       TextField surnameField = new TextField();
       GridPane.setConstraints(surnameLabel, 0, 3);
       GridPane.setConstraints(surnameField, 1, 3);

       Label semesterLabel = new Label("Semester:");
       TextField semesterField = new TextField();
       GridPane.setConstraints(semesterLabel, 0, 4);
       GridPane.setConstraints(semesterField, 1, 4);

       Label statusLabel = new Label("Status:");
       TextField statusField = new TextField();
       GridPane.setConstraints(statusLabel, 0, 5);
       GridPane.setConstraints(statusField, 1, 5);

       Label registrationDateLabel = new Label("Registration Date:");
       TextField registrationDateField = new TextField();
       GridPane.setConstraints(registrationDateLabel, 0, 6);
       GridPane.setConstraints(registrationDateField, 1, 6);

       Label cgpaLabel = new Label("CGPA:");
       TextField cgpaField = new TextField();
       GridPane.setConstraints(cgpaLabel, 0, 7);
       GridPane.setConstraints(cgpaField, 1, 7);

       Label birthDateLabel = new Label("Birth Date:");
       TextField birthDateField = new TextField();
       GridPane.setConstraints(birthDateLabel, 0, 8);
       GridPane.setConstraints(birthDateField, 1, 8);

       Label birthPlaceLabel = new Label("Birth Place:");
       TextField birthPlaceField = new TextField();
       GridPane.setConstraints(birthPlaceLabel, 0, 9);
       GridPane.setConstraints(birthPlaceField, 1, 9);

       Label fatherNameLabel = new Label("Father's Name:");
       TextField fatherNameField = new TextField();
       GridPane.setConstraints(fatherNameLabel, 0, 10);
       GridPane.setConstraints(fatherNameField, 1, 10);

       Label fatherSurNameLabel = new Label("Father's SurName:");
       TextField fatherSurNameField = new TextField();
       GridPane.setConstraints(fatherSurNameLabel, 0, 11);
       GridPane.setConstraints(fatherSurNameField, 1, 11);

       Label motherNameLabel = new Label("Mother's Name:");
       TextField motherNameField = new TextField();
       GridPane.setConstraints(motherNameLabel, 0, 12);
       GridPane.setConstraints(motherNameField, 1, 12);

       Label motherSurNameLabel = new Label("Mother's SurName:");
       TextField motherSurNameField = new TextField();
       GridPane.setConstraints(motherSurNameLabel, 0, 13);
       GridPane.setConstraints(motherSurNameField, 1, 13);

       Label emailLabel = new Label("Email:");
       TextField emailField = new TextField();
       GridPane.setConstraints(emailLabel, 0, 14);
       GridPane.setConstraints(emailField, 1, 14);

//
       Button addButton = new Button("Add");
       GridPane.setConstraints(addButton, 1, 15);
       addButton.setOnAction(e -> {
           int id = Integer.parseInt(idField.getText());
           String program = programField.getText();
           String name = nameField.getText();
           String surname = surnameField.getText();
           int semester = Integer.parseInt(semesterField.getText());
           String status = statusField.getText();
           String registrationDate = registrationDateField.getText();
           float cgpa = Float.parseFloat(cgpaField.getText());
           String birthDate = birthDateField.getText();
           String birthPlace = birthPlaceField.getText();
           String fatherName = fatherNameField.getText();
           String fatherSurname = fatherSurNameField.getText();
           String motherName = motherNameField.getText();
           String motherSurname = motherSurNameField.getText();
           String email = emailField.getText();

           student.setId(id);
           student.setProgram(program);
           student.setName(name);
           student.setSurname(surname);
           student.setSemester(semester);
           student.setStatus(status);
           student.setRegistrationDate(registrationDate);
           student.setCgpa(cgpa);
           student.setBirthDate(birthDate);
           student.setBirthPlace(birthPlace);
           student.setFatherName(fatherName);
           student.setFatherSurname(fatherSurname);
           student.setMotherName(motherName);
           student.setMotherSurname(motherSurname);
           student.setEmail(email);
           DataSource.getInstance().insertStudent(student);
           studentPopUp.close();
       });
       grid.getChildren().addAll(idLabel, idField, programLabel, programField, nameLabel, nameField,
               surnameLabel, surnameField, semesterLabel, semesterField, statusLabel, statusField, registrationDateLabel,
               registrationDateField, cgpaLabel, cgpaField, birthDateLabel, birthDateField, birthPlaceLabel,
               birthPlaceField, fatherNameLabel, fatherNameField, fatherSurNameLabel, fatherSurNameField,
               motherNameLabel, motherNameField, motherSurNameLabel, motherSurNameField, emailLabel, emailField);
       grid.getChildren().addAll(addButton);
       Scene scene = new Scene(grid, 500, 500);
       studentPopUp.setScene(scene);
       studentPopUp.showAndWait();
       listStudents();
   }
    @FXML
    private void createStudentUpdatePopUp() {
        final Student student = (Student) studentTable.getSelectionModel().getSelectedItem();
        Stage studentPopUp = new Stage();
        studentPopUp.setTitle("Enter Student Information you want to change");
        studentPopUp.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();
        GridPane.setConstraints(idLabel, 0, 0);
        GridPane.setConstraints(idField, 1, 0);

        Label programLabel = new Label("Program:");
        TextField programField = new TextField();
        GridPane.setConstraints(programLabel, 0, 1);
        GridPane.setConstraints(programField, 1, 1);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        GridPane.setConstraints(nameLabel, 0, 2);
        GridPane.setConstraints(nameField, 1, 2);

        Label surnameLabel = new Label("Surname:");
        TextField surnameField = new TextField();
        GridPane.setConstraints(surnameLabel, 0, 3);
        GridPane.setConstraints(surnameField, 1, 3);

        Label semesterLabel = new Label("Semester:");
        TextField semesterField = new TextField();
        GridPane.setConstraints(semesterLabel, 0, 4);
        GridPane.setConstraints(semesterField, 1, 4);

        Label statusLabel = new Label("Status:");
        TextField statusField = new TextField();
        GridPane.setConstraints(statusLabel, 0, 5);
        GridPane.setConstraints(statusField, 1, 5);

        Label registrationDateLabel = new Label("Registration Date:");
        TextField registrationDateField = new TextField();
        GridPane.setConstraints(registrationDateLabel, 0, 6);
        GridPane.setConstraints(registrationDateField, 1, 6);

        Label cgpaLabel = new Label("CGPA:");
        TextField cgpaField = new TextField();
        GridPane.setConstraints(cgpaLabel, 0, 7);
        GridPane.setConstraints(cgpaField, 1, 7);

        Label birthDateLabel = new Label("Birth Date:");
        TextField birthDateField = new TextField();
        GridPane.setConstraints(birthDateLabel, 0, 8);
        GridPane.setConstraints(birthDateField, 1, 8);

        Label birthPlaceLabel = new Label("Birth Place:");
        TextField birthPlaceField = new TextField();
        GridPane.setConstraints(birthPlaceLabel, 0, 9);
        GridPane.setConstraints(birthPlaceField, 1, 9);

        Label fatherNameLabel = new Label("Father's Name:");
        TextField fatherNameField = new TextField();
        GridPane.setConstraints(fatherNameLabel, 0, 10);
        GridPane.setConstraints(fatherNameField, 1, 10);

        Label fatherSurNameLabel = new Label("Father's SurName:");
        TextField fatherSurNameField = new TextField();
        GridPane.setConstraints(fatherSurNameLabel, 0, 11);
        GridPane.setConstraints(fatherSurNameField, 1, 11);

        Label motherNameLabel = new Label("Mother's Name:");
        TextField motherNameField = new TextField();
        GridPane.setConstraints(motherNameLabel, 0, 12);
        GridPane.setConstraints(motherNameField, 1, 12);

        Label motherSurNameLabel = new Label("Mother's SurName:");
        TextField motherSurNameField = new TextField();
        GridPane.setConstraints(motherSurNameLabel, 0, 13);
        GridPane.setConstraints(motherSurNameField, 1, 13);


        Button addButton = new Button("Update");
        GridPane.setConstraints(addButton, 1, 14);
        addButton.setOnAction(e -> {
            if(idField.getText().trim().isEmpty()){
                idField.setText(String.valueOf(student.getId()));
            }
            if(programField.getText().trim().isEmpty()){
                programField.setText(student.getProgram());
            }
            if(nameField.getText().trim().isEmpty()){
                nameField.setText(student.getName());
            }
            if(surnameField.getText().trim().isEmpty()){
                surnameField.setText(student.getSurname());
            }
            if(semesterField.getText().trim().isEmpty()){
                semesterField.setText(String.valueOf(student.getSemester()));
            }
            if(statusField.getText().trim().isEmpty()){
                statusField.setText(student.getStatus());
            }
            if(registrationDateField.getText().trim().isEmpty()){
                registrationDateField.setText(student.getRegistrationDate());
            }
            if(cgpaField.getText().trim().isEmpty()){
                cgpaField.setText(String.valueOf(student.getCgpa()));
            }
            if(birthDateField.getText().trim().isEmpty()){
                birthDateField.setText(student.getBirthDate());
            }
            if(birthPlaceField.getText().trim().isEmpty()){
                birthPlaceField.setText(student.getBirthPlace());
            }
            if(fatherNameField.getText().trim().isEmpty()){
                fatherNameField.setText(student.getFatherName());
            }
            if(fatherSurNameField.getText().trim().isEmpty()){
                fatherSurNameField.setText(student.getFatherSurname());
            }
            if(motherNameField.getText().trim().isEmpty()){
                motherNameField.setText(student.getMotherName());
            }
            if(motherSurNameField.getText().trim().isEmpty()){
                motherSurNameField.setText(student.getMotherSurname());
            }

            int id = Integer.parseInt(idField.getText());
            String program = programField.getText();
            String name = nameField.getText();
            String surname = surnameField.getText();
            int semester = Integer.parseInt(semesterField.getText());
            String status = statusField.getText();
            String registrationDate = registrationDateField.getText();
            float cgpa = Float.parseFloat(cgpaField.getText());
            String birthDate = birthDateField.getText();
            String birthPlace = birthPlaceField.getText();
            String fatherName = fatherNameField.getText();
            String fatherSurname = fatherSurNameField.getText();
            String motherName = motherNameField.getText();
            String motherSurname = motherSurNameField.getText();

            student.setId(id);
            student.setProgram(program);
            student.setName(name);
            student.setSurname(surname);
            student.setSemester(semester);
            student.setStatus(status);
            student.setRegistrationDate(registrationDate);
            student.setCgpa(cgpa);
            student.setBirthDate(birthDate);
            student.setBirthPlace(birthPlace);
            student.setFatherName(fatherName);
            student.setFatherSurname(fatherSurname);
            student.setMotherName(motherName);
            student.setMotherSurname(motherSurname);
            DataSource.getInstance().updateStudent(student);
            studentPopUp.close();
        });
        grid.getChildren().addAll(idLabel, idField, programLabel, programField, nameLabel, nameField,
                surnameLabel, surnameField, semesterLabel, semesterField, statusLabel, statusField, registrationDateLabel,
                registrationDateField, cgpaLabel, cgpaField, birthDateLabel, birthDateField, birthPlaceLabel,
                birthPlaceField, fatherNameLabel, fatherNameField, fatherSurNameLabel, fatherSurNameField,
                motherNameLabel, motherNameField, motherSurNameLabel, motherSurNameField);
        grid.getChildren().addAll(addButton);
        Scene scene = new Scene(grid, 500, 500);
        studentPopUp.setScene(scene);
        studentPopUp.showAndWait();
        listStudents();
    }

    @FXML
    private void createStudentDeletePopUp(){
        final Student student = (Student) studentTable.getSelectionModel().getSelectedItem();
        DataSource.getInstance().deleteStudent(student);
        listStudents();
    }
    @FXML
    private void changeScene() {
        final Student student = (Student) studentTable.getSelectionModel().getSelectedItem();
        Session.getInstance().setCurrentUser(student);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userStudentsCourses.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) studentTable.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class GetStudentsTask extends Task {

    @Override
    protected ObservableList<Student> call() throws Exception {
        return FXCollections.observableArrayList(DataSource.getInstance().queryStudents());
    }
}