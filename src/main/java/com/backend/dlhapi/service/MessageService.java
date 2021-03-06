package com.backend.dlhapi.service;

import com.backend.dlhapi.model.MessageModel;
import com.backend.dlhapi.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageRepository msg_repo;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public List<MessageModel> getMessage(String id_pengirim, String id_penerima){
        Query query = new Query();
//        query.addCriteria(Criteria.where("message_to").is(msg_to));
//        query.addCriteria(Criteria.where("message_by").is(msg_by));
        query.addCriteria(Criteria.where("id_pengirim").is(id_pengirim));
        query.addCriteria(Criteria.where("id_penerima").is(id_penerima));
        List<MessageModel> model = mongoTemplate.find(query, MessageModel.class);
        return model;
    }
    
    public void sendMessageService(MessageModel mm){
        msg_repo.save(mm);
    }
    
    public void ReceivedUpdate(String msg_to, String msg_by){
        Query query = new Query();
        query.addCriteria(Criteria.where("message_to").is(msg_to));
        query.addCriteria(Criteria.where("message_by").is(msg_by));
        query.addCriteria(Criteria.where("received").is("false"));
        
        Update update = new Update();
        update.set("received", "true");
        mongoTemplate.updateMulti(query, update, MessageModel.class);
    }
    
    public List<MessageModel> getNotif(String api_key, String msg_to){
        Query query = new Query();
        query.addCriteria(Criteria.where("received").is("false"));
        query.addCriteria(Criteria.where("message_to").is(msg_to));
        
        List<MessageModel> model =  mongoTemplate.find(query, MessageModel.class);
        return model;
    }
}
