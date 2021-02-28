package com.backend.dlhapi.repository;

import com.backend.dlhapi.model.SessionModel;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends MongoRepository<SessionModel, String>{
    public Optional<SessionModel> findByName(String name);
    
    public Optional<SessionModel> deleteByName(String name);
}
