package com.app.service;

import com.app.Model.HostelOwnerModel;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class HostelOwnerService {
    private final MongoTemplate mongoTemplate;
    HostelOwnerService(final MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
    public HostelOwnerModel createHostel(HostelOwnerModel hostelOwnerModel){
       return mongoTemplate.save(hostelOwnerModel);
    }

}
