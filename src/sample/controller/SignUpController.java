package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField signupFirstname;

    @FXML
    private JFXButton signUpsignUpButton;

    @FXML
    private JFXButton signUpsignInButton;

    @FXML
    private JFXTextField signUpLastname;

    @FXML
    private JFXTextField signUpUsername;

    @FXML
    private JFXTextField signUpLocation;

    @FXML
    private JFXRadioButton signUpMale;

    @FXML
    private JFXRadioButton signUpFemale;

    @FXML
    private JFXTextField signUpPassword;


    @FXML
    void initialize() {



        signUpsignUpButton.setOnAction(event->{
            createUser();
        });




    }

    public void createUser(){

        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstname = signupFirstname.getText();
        String lastname = signUpLastname.getText();
        String username = signUpUsername.getText();
        String password = signUpPassword.getText();
        String location = signUpLocation.getText();
        String gender = "Male";

        if(signUpFemale.isSelected())
        {
            gender = "Female";
        }

        User user = new User(firstname,lastname,username,password,location,gender);

        try {
            databaseHandler.signUpUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
