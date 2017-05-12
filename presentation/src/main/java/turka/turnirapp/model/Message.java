package turka.turnirapp.model;

import java.util.Date;

/**
 * Created by turka on 10/22/2016.
 */

public class Message {

    private String Title;
    private String Body;
    private int ID;
    private Date CreationDate;

    public String getTitle(){
        return Title;
    }

    public String getContent(){
        return Body;
    }

    public int getId(){
        return this.ID;
    }

    public Date getDate(){
        return CreationDate;
    }

    public Message(int Id,String title, String content,Date date){
        this.Title =  title;
        this.Body = content;
        this.ID = Id;
        this.CreationDate = date;
    }

}
