package com.backend.dlhapi.repository;

import com.backend.dlhapi.model.MessageModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<MessageModel, String>{
    
}
