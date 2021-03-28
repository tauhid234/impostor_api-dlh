package com.backend.dlhapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "out_goods")
public class OutGoodsModel {

    @Id
    private String id;
    private String name;
    private String amount_out;
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
    
    public void setAmount_out(String amount_out){
        this.amount_out = amount_out;
    }
    
    public String getAmount_out(){
        return amount_out;
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
