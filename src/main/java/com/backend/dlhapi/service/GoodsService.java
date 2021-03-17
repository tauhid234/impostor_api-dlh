package com.backend.dlhapi.service;

import com.backend.dlhapi.model.GoodsModel;
import com.backend.dlhapi.repository.GoodsRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {
    @Autowired
    GoodsRepository Goodsrepo;
    
    public Collection<GoodsModel> getDataGoods(){
        return Goodsrepo.findAll();
    }
    
    public void insertGoods(GoodsModel gd){
        Goodsrepo.save(gd);
    }
    
}
    