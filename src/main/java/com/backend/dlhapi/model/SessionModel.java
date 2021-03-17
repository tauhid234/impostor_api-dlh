package com.backend.dlhapi.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection= "admin")
public class SessionModel {
    @Id
    private String id;    
    private String api_key;
    private String name;
    private String password;
    private String jabatan;
    private String email;
    private String no_hp;
    private String alamat;
    
    public SessionModel(){
        
    }
    
    public SessionModel(String name,String passwordnya, String id){
        this.name = name;
        this.password = passwordnya;
        this.id = id;
    }
    
    public String getID(){
        return id;
    }
    
    public void setID(String id){
        this.id = id;
    }
    
    public String getApi_key(){
        return api_key;
    }
    
    public void setApi_key(String api_key){
        this.api_key = api_key;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getJabatan(){
        return jabatan;
    }
    
    public void setJabatan(String jabatan){
        this.jabatan = jabatan;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getNo_hp(){
        return no_hp;
    }
    
    public void setNo_hp(String no_hp){
        this.no_hp = no_hp;
    }
    
    public String getAlamat(){
        return alamat;
    }
    
    public void setAlamat(String alamat){
        this.alamat = alamat;
    }
    
    public String toString(){
        return "[id "+id+", name "+name+"]";
    }
}
