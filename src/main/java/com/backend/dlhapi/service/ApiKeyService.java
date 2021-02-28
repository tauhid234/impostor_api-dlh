package com.backend.dlhapi.service;

import com.backend.dlhapi.model.ApiKeyModel;
import com.backend.dlhapi.repository.ApiKeyRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {
    @Autowired
    ApiKeyRepository keyRepo;
    
    public Optional<ApiKeyModel> getKey(String api_key){
        return keyRepo.findByKey(api_key);
    }
}
