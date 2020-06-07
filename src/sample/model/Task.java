package sample.model;

import java.sql.Timestamp;

public class Task {

    private int userid;



    private Timestamp datecreated;
    private String task;
    private String description;
    private String deadline;



    public Task() {
    }

    public Task(Timestamp datecreated, String task, String description) {
        this.datecreated = datecreated;
        this.task = task;
        this.description = description;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }


}
