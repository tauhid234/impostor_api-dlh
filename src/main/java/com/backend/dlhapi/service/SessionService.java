package com.backend.dlhapi.service;

import com.backend.dlhapi.model.SessionModel;
import com.backend.dlhapi.repository.SessionRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    SessionRepository repo;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    //    FINDALL
    public Collection<SessionModel> getData(){
        return repo.findAll();
    }
    
    public List<SessionModel> getSession(String pass, String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("password").is(pass));
        query.addCriteria(Criteria.where("name").is(name));
        List<SessionModel> ss = mongoTemplate.find(query, SessionModel.class);
        return ss;
    }
    
    public Optional<SessionModel> getSessionName(String name){
        return repo.findByName(name);
    }
    
    public Optional<SessionModel> getId(String id){
        return repo.findById(id);
    }
    
    public Optional<SessionModel> getPassword(String pass){
        return repo.findByPassword(pass);
    }
    
    public void insert(SessionModel sc){
        repo.save(sc);
    }
    
    public void update(SessionModel sc){
        repo.save(sc);
    }
    
    public Optional<SessionModel> delete(String name){
        return repo.deleteByName(name);
    }
}
