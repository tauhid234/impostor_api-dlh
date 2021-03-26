package com.backend.dlhapi.service;

import com.backend.dlhapi.model.GoodsModel;
import com.backend.dlhapi.model.SessionModel;
import com.backend.dlhapi.repository.GoodsRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {
    @Autowired
    GoodsRepository Goodsrepo;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public Collection<GoodsModel> getDataGoods(){
        return Goodsrepo.findAll();
    }
    
    public List<GoodsModel> CountData(String year){
        Query query = new Query();
        query.addCriteria(Criteria.where("year").is(year));
        List<GoodsModel> data = mongoTemplate.find(query, GoodsModel.class);
        return data;
    }
    
    public void insertGoods(GoodsModel gd){
        Goodsrepo.save(gd);
    }
    
}
    