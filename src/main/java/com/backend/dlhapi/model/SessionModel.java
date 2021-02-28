package com.backend.dlhapi.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection= "admin")
public class SessionModel {
    @Id
    private ObjectId id;    
    private String name;
    private String password;
    
    public SessionModel(){
        
    }
    
    public SessionModel(String name,String pass){
        this.name = name;
        this.password = pass;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPass(){
        return password;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
