package com.backend.dlhapi.service;

import com.backend.dlhapi.model.EntryGoodsModel;
import com.backend.dlhapi.repository.EntryGoodsRepository;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryGoodsService {
    @Autowired
    EntryGoodsRepository entry_repo;
    
    public Collection<EntryGoodsModel> getAllInventoryIn(){
        return entry_repo.findAll();
    }
    
    public void saveInventoryIn(EntryGoodsModel em){
        entry_repo.save(em);
    }
}
