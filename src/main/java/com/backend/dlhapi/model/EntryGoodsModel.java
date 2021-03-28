package com.backend.dlhapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "entry_goods")
public class EntryGoodsModel {
    @Id
    private String id;
    private String name;
    private String amount_entry;
    private String information;
    private String date;
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getId(){
        return id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setAmount_entry(String amount_entry){
        this.amount_entry = amount_entry;
    }
    
    public String getAmount_entry(){
        return amount_entry;
    }
    
    public void setInformation(String information){
        this.information = information;
    }
    
    public String getInformation(){
        return information;
    }
    
    public void setDate(String date){
        this.date = date;
    }
    
    public String getDate(){
        return date;
    }
}
