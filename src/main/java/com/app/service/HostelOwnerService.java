package com.app.service;

import com.app.Model.HostelOwnerModel;
import com.app.exceptionHandler.HostelException;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class HostelOwnerService {
    private String Collection_name= "hostelOwnerModel";
    private final MongoTemplate mongoTemplate;
   public  HostelOwnerService(final MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
    public HostelOwnerModel createHostel(HostelOwnerModel hostelOwnerModel) throws HostelException {
        validateHostel(hostelOwnerModel);
       return mongoTemplate.save(hostelOwnerModel);
    }
    private void validateHostel(HostelOwnerModel hostelOwnerModel) throws HostelException {
       if(hostelOwnerModel.getHostelName() == null || hostelOwnerModel.getHostelName().isEmpty()){
           throw new HostelException("Hostel Name is Mandatory");
       }
       if(hostelOwnerModel.getHostelContact().getHostelOwnerContact()<=0){
           throw  new HostelException("Hostel Owner Contact should be  valid");
       }
       if(hostelOwnerModel.getHostelContact().getHostelContact()<=0){
           throw  new HostelException("Hostel Contact is not valid");
       }
   }
   public  HostelOwnerModel updateHostel( HostelOwnerModel hostelOwnerModel) throws HostelException {
       validateHostel(hostelOwnerModel);
       Query query = new Query();
       query.addCriteria(Criteria.where("_id").is(new ObjectId(String.valueOf(hostelOwnerModel.getHostelContact()))));
       Document document = new Document();
       Update update = Update.fromDocument(document);
       return  mongoTemplate.
               save(hostelOwnerModel,Collection_name);
   }


}
