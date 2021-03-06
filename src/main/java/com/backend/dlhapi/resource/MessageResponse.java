package com.backend.dlhapi.resource;

import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class MessageResponse {
    public ResponseEntity Succes(){
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=utf-8");
        JSONObject data = new JSONObject();
        data.putOnce("message", "Server completed the request as expected");
        data.putOnce("status", "success");
        return new ResponseEntity(data.toString(),header,HttpStatus.OK);
    }
    
    public ResponseEntity Accept(Optional data2){
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=utf-8");
        JSONObject data = new JSONObject();
        data.append("data", data2.get());
        data.putOnce("status", "success");
        return new ResponseEntity(data.toString(),header,HttpStatus.OK);
    }
    
    public Optional Unknown(Optional data, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return data;
    }
    
    public ResponseEntity NotFound(){
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=utf-8");
        JSONObject data = new JSONObject();
        data.putOnce("message", "The requested resource does not exist");
        data.putOnce("status", "failed");
        return new ResponseEntity(data.toString(),header,HttpStatus.NOT_FOUND);
    }
    
    public ResponseEntity BadRequest(){
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=utf-8");
        JSONObject data = new JSONObject();
        data.putOnce("message", "Client sent an invalid request");
        data.putOnce("status", "failed");
        return new ResponseEntity(data.toString(),header,HttpStatus.BAD_REQUEST);
    }
}
