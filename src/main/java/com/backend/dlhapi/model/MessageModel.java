package com.backend.dlhapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
public class MessageModel {
    
    @Id
    private String id;
    private String message_by;
    private String message_to;
    private String received;
    private String message;
    private String date;
    
    public MessageModel(){
        
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getId(){
        return id;
    }
    
    public void setMessage_by(String msg_by){
        this.message_by = msg_by;
    }
    
    public String getMessage_by(){
        return message_by;
    }
    
    public void setMessage_to(String msg_to){
        this.message_to = msg_to;
    }
    
    public String getMessage_to(){
        return message_to;
    }
    
    public void setReceived(String received){
        this.received = received;
    }
    
    public String getReceived(){
        return received;
    }
    
    public void setMessage(String msg){
        this.message = msg;
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setDate(String date){
        this.date = date;
    }
    
    public String getDate(){
        return date;
    }
}
