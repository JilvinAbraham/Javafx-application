package sample.database;

import sample.model.Task;
import sample.model.User;

import java.sql.*;

public class DatabaseHandler extends Configs{

    Connection dbConnection;

    public Connection getConnection() throws SQLException, ClassNotFoundException {


        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);
        return dbConnection;

    }

    //Write

    public void signUpUser(User user) throws SQLException {

        String insert = "INSERT INTO " + Const.USERS_TABLE +
                "(" + Const.USERS_FIRSTNAME +
                "," + Const.USERS_LASTTNAME +
                "," + Const.USERS_USERNAME +
                "," + Const.USERS_PASSWORD +
                "," + Const.USERS_LOCATION +
                "," + Const.USERS_GENDER + ")" + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insert);

            preparedStatement.setString(1,user.getFirstname());
            preparedStatement.setString(2,user.getLastname());
            preparedStatement.setString(3,user.getUsername());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getLocation());
            preparedStatement.setString(6,user.getGender());

            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet validUser(User user){
        ResultSet resultSet = null;

        if(!user.getUsername().equals("") && !user.getPassword().equals("")){
            String query = "SELECT * FROM " +  Const.USERS_TABLE  + " WHERE " + Const.USERS_USERNAME + "=?" +
                    " AND " + Const.USERS_PASSWORD + "=?";

            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setString(1,user.getUsername());
                preparedStatement.setString(2,user.getPassword());

                resultSet = preparedStatement.executeQuery();


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return resultSet;
    }

    public void addTask(Task task) throws SQLException {

        String insert = "INSERT INTO " + Const.TASKS_TABLE +
                "(" + Const.USERS_ID +
                "," + Const.TASKS_DATE +
                "," + Const.TASK_DESCRIPTION +
                "," + Const.TASKS_TASK +
                "," + Const.TASKS_DEADLINE + ")" + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insert);

            preparedStatement.setInt(1, task.getUserid());
            preparedStatement.setTimestamp(2, task.getDatecreated());
            preparedStatement.setString(3,task.getDescription());
            preparedStatement.setString(4,task.getTask());
            preparedStatement.setString(5,task.getDeadline());


            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet tasklist(int userid){
        ResultSet resultSet = null;

        String query = "SELECT " + Const.TASKS_TASK + "," + Const.TASK_DESCRIPTION + "," + Const.TASKS_DEADLINE + " FROM " + Const.TASKS_TABLE + " WHERE " + Const.USERS_ID  + "=?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1,userid);

          resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }



}
