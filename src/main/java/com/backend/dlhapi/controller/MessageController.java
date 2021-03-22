package com.backend.dlhapi.controller;

import com.backend.dlhapi.model.MessageModel;
import com.backend.dlhapi.model.SessionModel;
import com.backend.dlhapi.resource.MessageResponse;
import com.backend.dlhapi.service.MessageService;
import com.backend.dlhapi.service.SessionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class MessageController {
    
    @Autowired
    private MessageService service;
    
    @Autowired
    private SessionService session;
    
    @PostMapping("message")
    public ResponseEntity getMessage(@RequestParam(value = "msg_to") String msg_to, @RequestParam(value = "msg_by") String msg_by, 
                                     @RequestParam(value = "api_key") String api_key, @RequestParam(value = "id_pengirim") String id_pengirim,
                                     @RequestParam(value = "id_penerima") String id_penerima){
        
        Optional sessionApiKey = session.ApiKeySet(api_key);
        List<MessageModel> data = service.getMessage(id_pengirim, id_penerima);
        if(sessionApiKey.isPresent()){
            if(!data.isEmpty()){
                return updateReceivedMessage(data, msg_to, msg_by);
            }
        }
        return new MessageResponse().NotFound();
    }
    
    @PostMapping("send_message")
    public ResponseEntity sendMessage(@RequestParam(value = "api_key") String api_key,
                                      @RequestParam(value = "message_by") String message_by, @RequestParam(value = "message_to") String message_to,
                                      @RequestParam(value = "message") String message, @RequestParam(value = "date") String date, 
                                      @RequestParam(value = "id_pengirim") String id_pengirim, @RequestParam(value = "id_penerima") String id_penerima,MessageModel model){
        
        Optional<SessionModel> sessionApiKey = session.ApiKeySet(api_key);
        if(sessionApiKey.isPresent()){
            if(message_by.equals("") || message_to.equals("") || date.equals("")){
                return new MessageResponse().Empty();
            }
            
            model.setId_pengirim(id_pengirim);
            model.setId_penerima(id_penerima);
            model.setMessage_by(message_by);
            model.setMessage_to(message_to);
            model.setReceived(String.valueOf(false));
            model.setMessage(message);
            model.setDate(date);
            
            service.sendMessageService(model);
            return new MessageResponse().Succes();
        }
         return new MessageResponse().NotFound();
    }
    
    private ResponseEntity updateReceivedMessage(List<MessageModel> model ,String msg_to, String msg_by){
        
            service.ReceivedUpdate(msg_to, msg_by);
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", "application/json; charset=utf-8");
            return new ResponseEntity(model,header,HttpStatus.OK);        
            
    }
    
    @PostMapping("notification_message")
    private ResponseEntity getNotificationMessage(@RequestParam(value = "api_key") String api_key, @RequestParam(value = "message_to") String message_to){
        List<MessageModel> model = service.getNotif(api_key, message_to);
        if(!model.isEmpty()){
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", "application/json; charset=utf-8");
            return new ResponseEntity(model,header,HttpStatus.OK);
        }
        return new MessageResponse().NotFound();
    }
            
    
}
