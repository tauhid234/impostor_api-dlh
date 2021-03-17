package com.backend.dlhapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "goods")
public class GoodsModel {
    
    @Id
    private String id;
    private String name;
    private String date;
    private String amount;
    
    public GoodsModel(){
        
    }
    
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
    
    public void setDate(String date){
        this.date = date;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setAmount(String amount){
        this.amount = amount;
    }
    
    public String getAmount(){
        return amount;
    }
    
    @Override
    public String toString(){
        return "{ id = "+id+" name_goods = "+name+" date = "+date+" amount = "+amount+" }"; 
    }
}
