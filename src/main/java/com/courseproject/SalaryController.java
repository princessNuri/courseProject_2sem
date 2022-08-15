package com.courseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SalaryController {

    ObservableList<String> roles = FXCollections.observableArrayList("hrmanager", "worker", "director");

    @FXML
    private Button ApplySalaryButtom;

    @FXML
    private TextField bonus;

    @FXML
    private Text errorLabel;

    @FXML
    private TextField name;

    @FXML
    private TextField position;

    @FXML
    private ComboBox<String> role;

    @FXML
    private TextField salary;

    @FXML
    Label lbl = new Label();

    @FXML
    public void initialize() throws SQLException {

        role.setItems(roles);
        role.setOnAction(event -> lbl.setText(role.getValue()));
        ApplySalaryButtom.setOnAction(ActionEvent -> {


        });


    }
}
