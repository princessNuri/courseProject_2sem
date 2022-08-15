package com.courseproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DirectorController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ApplyButtom;

    @FXML
    private Button ChangeSalaryButtom;

    @FXML
    private TableColumn<DirectorVariables, Integer> BonusColumn;

    @FXML
    private AnchorPane DirMenu;

    @FXML
    private TableView<User> DirTable;

    @FXML
    private Text DirText;

    @FXML
    private TableColumn<DirectorVariables, Integer> IDColumn;

    @FXML
    private Button LogoutButtom;

    @FXML
    private Button SalaryReportButtom;

    @FXML
    private Button SearchButtom;

    @FXML
    private TableColumn<DirectorVariables, String> NameColumn;

    @FXML
    private TableColumn<DirectorVariables, String> PositionColumn;

    @FXML
    private TableColumn<DirectorVariables, String> RoleColumn;

    @FXML
    private TableColumn<DirectorVariables, Integer> SalaryColumn;

    ObservableList<User> usersList = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {
        setDirTable();
        DirText.setText("Hello, dear " + Controller.userName + "!");


        LogoutButtom.setOnAction(ActionEvent -> {
            System.out.println(Controller.userName + "exit");
            LogoutButtom.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("log_in.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();
        });

        SalaryReportButtom.setOnAction(ActionEvent -> {


        });

        ChangeSalaryButtom.setOnAction(ActionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("changesalary.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();


        });

        SearchButtom.setOnAction(ActionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("search.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.show();
        });


    }

    public void setDirTable() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet result = dbHandler.getAllUser();
        DirectorVariables director = new DirectorVariables();

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            usersList.add(new DirectorVariables(result.getInt("id"),
                    result.getString("name"),
                    result.getInt("salary"),
                    result.getString("role"),
                    result.getString("pos"),
                    result.getInt("bonus")));
        }
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        SalaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        RoleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        PositionColumn.setCellValueFactory(new PropertyValueFactory<>("pos"));
        BonusColumn.setCellValueFactory(new PropertyValueFactory<>("bonus"));

        IDColumn.setSortType(TableColumn.SortType.DESCENDING);
        DirTable.setItems(usersList);
    }

}


