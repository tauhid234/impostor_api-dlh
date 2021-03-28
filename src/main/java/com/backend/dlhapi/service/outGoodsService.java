package com.backend.dlhapi.service;

import com.backend.dlhapi.model.OutGoodsModel;
import com.backend.dlhapi.repository.OutGoodsRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class outGoodsService {
    @Autowired
    OutGoodsRepository outGoodsRepository;
    
    public Collection<OutGoodsModel> getAllInventoryIn(){
        return outGoodsRepository.findAll();
    }
    
    public void saveInventoryIn(OutGoodsModel em){
        outGoodsRepository.save(em);
    }
}
