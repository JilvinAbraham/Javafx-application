package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Handler;

public class LoginController {

    private int userid;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField loginUsername;

    @FXML
    private JFXButton loginLoginButton;

    @FXML
    private JFXButton loginSignUpButton;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    void initialize() {

       loginLoginButton.setOnAction(event ->{

           String loginText = loginUsername.getText().trim();
           String loginpPwd = loginPassword.getText().trim();

           User user = new User();
           user.setUsername(loginText);
           user.setPassword(loginpPwd);

           DatabaseHandler databaseHandler = new DatabaseHandler();
           ResultSet userRow = databaseHandler.validUser(user);
           int counter = 0;

           try{
           while(userRow.next()){
               counter++;
               userid = userRow.getInt("userid");
           }
           if(counter==1){

               loginSignUpButton.getScene().getWindow().hide();
               FXMLLoader loader = new FXMLLoader();
               loader.setLocation(getClass().getResource("/sample/view/additem.fxml"));

               try {
                   loader.load();
               }catch (IOException e){
                   e.printStackTrace();
               }



               Parent root = loader.getRoot();
               Stage stage = new Stage();
               stage.setScene(new Scene(root));

               AdditemController additemController = loader.getController();
               additemController.setUserid(userid);


               stage.showAndWait();
//
           }
           else{
               System.out.println("Enter valid credentials");
           }
           }catch (SQLException e){
               e.printStackTrace();
           }




       });

       loginSignUpButton.setOnAction(event -> {
           loginSignUpButton.getScene().getWindow();
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));

           try {
               loader.load();
           }catch (IOException e){
               e.printStackTrace();
           }

           Parent root = loader.getRoot();  
           Stage stage = new Stage();
           stage.setScene(new Scene(root));
           stage.showAndWait();


       });


    }





}


