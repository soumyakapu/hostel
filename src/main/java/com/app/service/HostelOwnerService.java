package com.app.service;

import com.app.Model.HostelContact;
import com.app.Model.HostelFacilities;
import com.app.Model.HostelOwnerModel;
import com.app.Model.LoginRequest;
import com.app.exceptionHandler.HostelException;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.lang.Nullable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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

       Update update = new Update();
       Query query = new Query();
       query.addCriteria(Criteria.where("_id").is(new ObjectId(hostelOwnerModel.getId())));
      Optional<HostelOwnerModel> optionalHostelOwnerModel = Optional.ofNullable(mongoTemplate.findOne(query,HostelOwnerModel.class));

      if(!optionalHostelOwnerModel.isPresent()){
          throw new HostelException("hostel not present");
      }
      HostelOwnerModel hostelOwnerModel1 = optionalHostelOwnerModel.get();
      HostelFacilities facilities = hostelOwnerModel1.getFacilities();

      if (hostelOwnerModel.getFacilities()!= null && hostelOwnerModel.getFacilities().getElectricity() != null)  {
          facilities.setElectricity(hostelOwnerModel.getFacilities().getElectricity());
      }

      if (hostelOwnerModel.getFacilities() != null && hostelOwnerModel.getFacilities().getWater() != null)  facilities.setWater(hostelOwnerModel.getFacilities().getWater());

       HostelContact contacts = hostelOwnerModel1.getHostelContact();

      if(hostelOwnerModel.getHostelContact() != null && hostelOwnerModel.getHostelContact().getHostelContact() > 5_99_99_99_999L && hostelOwnerModel.getHostelContact().getHostelContact() < 9999999999L){
          contacts.setHostelContact(hostelOwnerModel.getHostelContact().getHostelContact());
      }

      if(hostelOwnerModel.getHostelContact() != null && hostelOwnerModel.getHostelContact().getHostelOwnerContact() > 5_99_99_99_999L && hostelOwnerModel.getHostelContact().getHostelOwnerContact() < 9999999999L){
           contacts.setHostelOwnerContact(hostelOwnerModel.getHostelContact().getHostelOwnerContact());
       }


       if (hostelOwnerModel.getHostelName() != null && !hostelOwnerModel.getHostelName().isEmpty()) {
           update.set("hostelName", hostelOwnerModel.getHostelName());}
       if (hostelOwnerModel.getHostelOwnerName()!=null && !hostelOwnerModel.getHostelOwnerName().isEmpty()){
           update.set("hostelOwnerName", hostelOwnerModel.getHostelOwnerName());}
       if(hostelOwnerModel.getHostelAddress()!=null && !hostelOwnerModel.getHostelAddress().isEmpty()) {
           update.set("hostelAddress",hostelOwnerModel.getHostelAddress());
       }

       update.set("facilities", facilities);
       update.set("hostelContact", contacts);


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
    public String activateHostel(String id){
       Query query = new Query().addCriteria(Criteria.where("_id").is(id).and("isActive").is(false));
       Update update = new Update();
       update.set("isActive",true);
       mongoTemplate.updateFirst(query,update,HostelOwnerModel.class);
       return "Hostel successfully activated";
    }
    public String deactiveHostel(String id){
        Query query = new Query().addCriteria(Criteria.where("_id").is(id).and("isActive").is(true));
        Update update = new Update();
        update.set("isActive",false);
        mongoTemplate.updateFirst(query,update,HostelOwnerModel.class);
        return "Hostel successfully deactivated";
    }


    public boolean login(LoginRequest loginRequest) throws HostelException {
       Query query = new Query().addCriteria(Criteria.where("email").is(loginRequest.getEmail()));
        HostelOwnerModel ownerModel = mongoTemplate.findOne(query, HostelOwnerModel.class);
        if(ownerModel == null){
            throw new HostelException("User not found");
        }
        return  ownerModel.getPassword().equals(loginRequest.getPassword());
    }
}
