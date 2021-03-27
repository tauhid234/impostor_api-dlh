package com.backend.dlhapi.repository;

import com.backend.dlhapi.model.EntryGoodsModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntryGoodsRepository extends MongoRepository<EntryGoodsModel, String>{
    
}
