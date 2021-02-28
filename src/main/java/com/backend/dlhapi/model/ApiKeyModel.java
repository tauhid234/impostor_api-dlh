package com.backend.dlhapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "api_key")
public class ApiKeyModel {
    @Id
    private Object id;
    private String key;
    
    
//    NOTE : REPOSITORY OPTIONAL YANG CUSTOM KALO FIND HARUS IKUTIN FIELD NYA, MISAL : findByKey
    public ApiKeyModel(){
        
    }
    
    public ApiKeyModel(String api_key){
        this.key = api_key;
    }
    
    public String getApiKey(){
        return key;
    }
    
    public void setApiKey(String key){
        this.key = key;
    }
}
