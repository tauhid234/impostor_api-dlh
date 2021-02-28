package com.backend.dlhapi.repository;

import com.backend.dlhapi.model.ApiKeyModel;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends MongoRepository<ApiKeyModel, String> {
    public Optional<ApiKeyModel> findByKey(String api_key);
}
