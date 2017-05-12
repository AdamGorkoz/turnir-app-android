package com.models;

import java.util.Date;

/**
 * Created by turka on 10/29/2016.
 */

public class MessageModel {
    private String Title;
    private String Body;
    private int ID;
    private Date CreationDate;

    public void setTitle(String title) {
        Title = title;
    }

    public void setBody(String body) {
        Body = body;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setRecordStatus(int recordStatus) {
        RecordStatus = recordStatus;
    }

    public int getRecordStatus() {
        return RecordStatus;
    }

    private int RecordStatus;

    public String getTitle(){
        return Title;
    }

    public String getBody(){
        return Body;
    }

    public int getId(){
        return this.ID;
    }

    public Date getDate(){
        return CreationDate;
    }
}
