package com.backend.dlhapi.service;

import com.backend.dlhapi.model.SessionModel;
import com.backend.dlhapi.repository.SessionRepository;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    SessionRepository repo;
    
    //    FINDALL
    public Collection<SessionModel> getData(){
        return repo.findAll();
    }
    
    public Optional<SessionModel> getSession(String nama){
        return repo.findByName(nama);
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
