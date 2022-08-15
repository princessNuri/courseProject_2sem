package com.courseproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SearchController {
    @FXML
    private TableColumn<DirectorVariables, Integer> SearchBonusColumn;

    @FXML
    private Button SearchButton;

    @FXML
    private TextField SearchField;

    @FXML
    private TableColumn<DirectorVariables, Integer> SearchIDColumn;

    @FXML
    private Pane SearchMenu;

    @FXML
    private TableColumn<DirectorVariables, String> SearchNameColumn;

    @FXML
    private TableColumn<DirectorVariables, String> SearchPositionColumn;

    @FXML
    private TableColumn<DirectorVariables, String> SearchRoleColumn;

    @FXML
    private TableColumn<DirectorVariables, Integer> SearchSalaryColumn;

    @FXML
    private TableView<DirectorVariables> SearchTable;

    @FXML
    public void initialize() throws SQLException {


    }

}
