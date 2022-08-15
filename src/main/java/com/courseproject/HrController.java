package com.courseproject;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.courseproject.Controller.copyByStream;

public class HrController {

    ObservableList<String> sal = FXCollections.observableArrayList("per day", "per month", "per year");

    ObservableList<String> bon = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text bonus;

    @FXML
    private Text errorLabel;

    @FXML
    private Text greetings;

    @FXML
    private Button logout;

    @FXML
    private ComboBox<String> period;

    @FXML
    private Text name;

    @FXML
    private Text position;


    @FXML
    Label lbl = new Label();


    @FXML
    private Text salary;


    @FXML
    private Button editPass;

    @FXML
    private TextField thFn;

    @FXML
    private TextField thSt;

    @FXML
    private TextField tuFn;

    @FXML
    private TextField tuSt;

    @FXML
    private TextField weFn;

    @FXML
    private TextField weSt;


    @FXML
    private TextField moFn;

    @FXML
    private TextField moSt;

    @FXML
    private TextField frFn;

    @FXML
    private TextField frSt;

    @FXML
    private Text entry;

    @FXML
    private Hyperlink editAvatar;

    @FXML
    private Circle avatar;

    @FXML
    private AnchorPane tableBlock;

    @FXML
    private AnchorPane statsBlock;

    @FXML
    private AnchorPane profileBlock;

    @FXML
    private PieChart genderChart;

    @FXML
    private ComboBox<String> sr_st;

    @FXML
    private Button addUser;

    @FXML
    private Button apply;

    @FXML
    private TableView<User> hrtable;

    @FXML
    private Button editUser;

    @FXML
    private Button refresh;

    @FXML
    private Button emp_table;

    @FXML
    private Button profile_but;

    @FXML
    private Button stats_but;

    @FXML
    private TableColumn<User, Integer> tableBonus;

    @FXML
    private TableColumn<User, Integer> tableStatus;

    @FXML
    private TableColumn<User, Integer> tableId;

    @FXML
    private TableColumn<User, String> tableLogin;

    @FXML
    private TableColumn<User, String> tableName;

    @FXML
    private TableColumn<User, String> tableGender;

    @FXML
    private TableColumn<User, String> tablePos;

    @FXML
    private TableColumn<User, String> tableRole;

    @FXML
    private TableColumn<User, Integer> tableSalary;

    ObservableList<User> usersList = FXCollections.observableArrayList();
    ObservableList<String> statusList = FXCollections.observableArrayList("Works", "Fired");

    @FXML
    protected void initialize() throws SQLException {
        sr_st.setValue("Works");
        setTableWorks();
        sr_st.setItems(statusList);

        File resourcesDir = new File("src/main/resources/com/courseproject/img");
        File nullavatar = new File(resourcesDir.getAbsolutePath() + "/" + Controller.userId + ".png");
        if (nullavatar.exists()) {
            Image image = new Image(resourcesDir.getAbsolutePath() + "/" + Controller.userId + ".png");
            avatar.setFill(new ImagePattern(image));
        } else {
            System.out.println("not exist");
            Image image = new Image(resourcesDir.getAbsolutePath() + "/" + "0.png");
            avatar.setFill(new ImagePattern(image));
        }
        profile_but.setOnAction(actionEvent -> {
            profileBlock.setVisible(true);
            statsBlock.setVisible(false);
            tableBlock.setVisible(false);
        });
        stats_but.setOnAction(actionEvent -> {
            profileBlock.setVisible(false);
            statsBlock.setVisible(true);
            tableBlock.setVisible(false);
        });
        emp_table.setOnAction(actionEvent -> {
            profileBlock.setVisible(false);
            statsBlock.setVisible(false);
            tableBlock.setVisible(true);
        });

        editAvatar.setOnAction(actionEvent -> {
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(imageFilter);
            String FileDis = String.valueOf(fileChooser.showOpenDialog(null));
            System.out.println(FileDis);
            Image imagee = new Image(FileDis);
            avatar.setFill(new ImagePattern(imagee));
            File resourcesDirectory = new File("src/main/resources/com/courseproject/img");
            System.out.println(resourcesDirectory.getAbsolutePath());
            try {
                copyByStream(FileDis, resourcesDirectory.getAbsolutePath() + "/" + Controller.userId + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        sr_st.setOnAction(actionEvent -> {
            if (sr_st.getValue().equals("Works")) {
                try {
                    hrtable.getItems().clear();
                    setTableWorks();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    hrtable.getItems().clear();
                    setTableNotWorks();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        greetings.setText("HRManager Panel");

        editUser.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("edituser.fxml"));

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

        addUser.setOnAction(event -> {
            System.out.println(Controller.userName + " add user");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("adduser.fxml"));

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

        refresh.setOnAction(event -> {
            try {
                hrtable.getItems().clear();
                setHrtable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        logout.setOnAction(event -> {
            System.out.println(Controller.userName + "exit");
            logout.getScene().getWindow().hide();

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


        DatabaseHandler dbhandler = new DatabaseHandler();
        name.setText(Controller.userName);
        position.setText(Controller.userPos);
        setForm(Controller.userId);

        entry.setText(dbhandler.getEntryById(Controller.userId));
        period.setItems(sal);
        salary.setText(Controller.userSalary + " som");
        bonus.setText(Controller.userBonus + " som");

        period.setOnAction(event -> {
            String sa = period.getValue();
            lbl.setText(period.getValue());
            if (sa.equals("per month")) {
                salary.setText(Controller.userSalary + " som");
            } else if (sa.equals("per year")) {
                salary.setText(Controller.userSalary * 12 + " som");
            } else {
                salary.setText(Controller.userSalary / 30 + " som");
            }

        });

        editPass.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("changepass.fxml"));

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

    @FXML
    private void setHrtable() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();

        ResultSet result = dbHandler.getAllUser();

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            usersList.add(new User(result.getInt("id"),
                    result.getString("name"),
                    result.getString("login"),
                    result.getString("password"),
                    result.getInt("salary"),
                    result.getString("role"),
                    result.getString("pos"),
                    result.getInt("bonus"),
                    result.getString("gender"),
                    result.getString("status")));

        }
        tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        tableGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tableSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tableRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tablePos.setCellValueFactory(new PropertyValueFactory<>("pos"));
        tableBonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        tableStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableId.setSortType(TableColumn.SortType.DESCENDING);

        hrtable.setItems(usersList);
    }

    @FXML
    private void setTableWorks() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();

        ResultSet result = dbHandler.getAllUser();

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (result.getString("status").equals("Works")) {
                usersList.add(new User(result.getInt("id"),
                        result.getString("name"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getInt("salary"),
                        result.getString("role"),
                        result.getString("pos"),
                        result.getInt("bonus"),
                        result.getString("gender"),
                        result.getString("status")));
            }

        }
        tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        tableGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tableSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tableRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tablePos.setCellValueFactory(new PropertyValueFactory<>("pos"));
        tableBonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        tableStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableId.setSortType(TableColumn.SortType.DESCENDING);

        hrtable.setItems(usersList);
    }


    @FXML
    private void setTableNotWorks() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();

        ResultSet result = dbHandler.getAllUser();

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (!result.getString("status").equals("Works")) {
                usersList.add(new User(result.getInt("id"),
                        result.getString("name"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getInt("salary"),
                        result.getString("role"),
                        result.getString("pos"),
                        result.getInt("bonus"),
                        result.getString("gender"),
                        result.getString("status")));
            }

        }
        tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        tableGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tableSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tableRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tablePos.setCellValueFactory(new PropertyValueFactory<>("pos"));
        tableBonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        tableStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableId.setSortType(TableColumn.SortType.DESCENDING);

        hrtable.setItems(usersList);
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
    private void setForm(int id) {

        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resulttime = dbHandler.getTimeById(id);
        while (true) {
            try {
                if (!resulttime.next()) break;
                moSt.setText(resulttime.getString("moSt"));
                moFn.setText(resulttime.getString("moFn"));
                thSt.setText(resulttime.getString("thSt"));
                thFn.setText(resulttime.getString("thFn"));
                frSt.setText(resulttime.getString("frSt"));
                frFn.setText(resulttime.getString("frFn"));
                weSt.setText(resulttime.getString("weSt"));
                weFn.setText(resulttime.getString("weFn"));
                tuSt.setText(resulttime.getString("tuSt"));
                tuFn.setText(resulttime.getString("tuFn"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
