package com.backend.dlhapi.repository;

import com.backend.dlhapi.model.GoodsModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends MongoRepository<GoodsModel, String>{
    
}
