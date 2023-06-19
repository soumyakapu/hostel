package com.app.service;

import com.app.Model.HostelOwnerModel;
import com.app.exceptionHandler.HostelException;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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
   public UpdateResult updateHostel(HostelOwnerModel hostelOwnerModel) throws HostelException {


       Query query = new Query();
       query.addCriteria(Criteria.where("_id").is(new ObjectId(hostelOwnerModel.getId())));
       Update update = new Update();



       if (hostelOwnerModel.getHostelName() != null && !hostelOwnerModel.getHostelName().isEmpty()) {
           update.set("hostelName", hostelOwnerModel.getHostelName());}
       if (hostelOwnerModel.getHostelOwnerName()!=null && !hostelOwnerModel.getHostelOwnerName().isEmpty()){
           update.set("hostelOwnerName", hostelOwnerModel.getHostelOwnerName());}
       if(hostelOwnerModel.getHostelAddress()!=null && !hostelOwnerModel.getHostelAddress().isEmpty()) update.set("hostelAddress",hostelOwnerModel.getHostelAddress());
       if(hostelOwnerModel.getHostelContact().getHostelContact()>=10 ) update.set("hostelContact",hostelOwnerModel.getHostelContact().getHostelContact());
       if(hostelOwnerModel.getHostelContact().getHostelOwnerContact()>=10)update.set("hostelOwnerContact",hostelOwnerModel.getHostelContact().getHostelOwnerContact());
       if (hostelOwnerModel.getFacilities().getElectricity()!=null && hostelOwnerModel.getFacilities().getElectricity().isEmpty()) update.set("electricity",hostelOwnerModel.getFacilities().getElectricity());
       if(hostelOwnerModel.getFacilities().getWater()!=null && hostelOwnerModel.getFacilities().getWater().isEmpty()) update.set("water",hostelOwnerModel.getFacilities().getWater());
//       if (hostelOwnerModel.getFacilities().isRoomAvailability()!=false) update.set("roomAvailability",hostelOwnerModel.getFacilities().isRoomAvailability());
       UpdateResult updateResult = mongoTemplate.updateFirst(query, update, HostelOwnerModel.class);
       if (updateResult.getMatchedCount() < 0) {
           throw new HostelException("No hostel found");
       }

       return updateResult;

   }
    public List<HostelOwnerModel> getAll(){
       return mongoTemplate.findAll(HostelOwnerModel.class);
    }
    public HostelOwnerModel getHostelBYName(String hostelOwnerModel){
       Query query = new Query().addCriteria(Criteria.where("hostelName").is(hostelOwnerModel).and("isActive").is(true));
       return   mongoTemplate.findOne(query,HostelOwnerModel.class,Collection_name);
    }
    public DeleteResult deleteByHostelName(String  hostelName){
       Query query = new Query().addCriteria(Criteria.where("hostelName").is(hostelName).and("isActive").is(false));
       return mongoTemplate.remove(query,Collection_name);
    }


}
