package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import sample.database.DatabaseHandler;
import sample.model.Task;

import javax.xml.crypto.Data;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AdditemController {

    private int userid;
    private ResultSet resultSet = null;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField additemTask;

    @FXML
    private JFXTextField additemDescription;

    @FXML
    private JFXTextField additemDeadline;

    @FXML
    private JFXButton additemAddButton;

    @FXML
    private JFXButton additemShowTask;

    @FXML
    private JFXListView<String> additemlistTask;




    @FXML
    void initialize() {




        additemAddButton.setOnAction(event->{
            DatabaseHandler databaseHandler = new DatabaseHandler();

            java.sql.Timestamp timestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
            Task task = new Task();
            task.setDatecreated(timestamp);
            task.setUserid(userid);
            task.setDescription(additemDescription.getText());
            task.setDeadline(additemDeadline.getText());
            task.setTask(additemTask.getText());


            try {
                databaseHandler.addTask(task);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            additemDescription.setText("");
            additemDeadline.setText("");
            additemTask.setText("");



        });

        additemShowTask.setOnAction(event->{

            DatabaseHandler databaseHandler = new DatabaseHandler();

            resultSet = databaseHandler.tasklist(userid);
            ArrayList<String> arrayList = new ArrayList<>();
            while(true){
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    arrayList.add(resultSet.getString("task") +" | " + resultSet.getString("description") +" | "+ resultSet.getString("deadline"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            ObservableList<String> listview = FXCollections.observableArrayList(arrayList);
            additemlistTask.setItems(listview);


        });




    }

    public int getUserid() {
        return this.userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }


}
