package com.backend.dlhapi.repository;

import com.backend.dlhapi.model.OutGoodsModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutGoodsRepository extends MongoRepository<OutGoodsModel, Object>{
    
}
