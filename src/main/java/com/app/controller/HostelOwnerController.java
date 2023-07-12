package com.app.controller;

import com.app.Model.HostelContact;
import com.app.Model.HostelOwnerModel;
import com.app.Model.ServerResponse;
import com.app.exceptionHandler.HostelException;
import com.app.service.HostelOwnerService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class HostelOwnerController {
    private final HostelOwnerService hostelOwnerService;

    public HostelOwnerController(final HostelOwnerService hostelOwnerService) {
        this.hostelOwnerService = hostelOwnerService;
    }

    @PostMapping(value = "/post", produces = "application/json")
    @PreAuthorize("isAuthenticated()")
    public ServerResponse createHostel(@RequestBody HostelOwnerModel hostelOwnerModel) throws Exception {
        try{
            hostelOwnerService.createHostel(hostelOwnerModel);
           return ServerResponse.builder().status(HttpStatus.CREATED).message("Successfully Registered").build();
        }catch(HostelException e){
            return ServerResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage()).build();
        }

    }

    @PutMapping(value = "/update", produces = "application/json")
    public UpdateResult updateHostel(@RequestBody HostelOwnerModel hostelOwnerModel) throws HostelException {
        return hostelOwnerService.updateHostel(hostelOwnerModel);
    }

    @GetMapping(value = "/AllHostels", produces = "application/json")

    public ServerResponse allHostels(){
        hostelOwnerService.getAll();
        return ServerResponse.builder().status(HttpStatus.OK).build();

    }
    @GetMapping(value = "/hostelbyname", produces = "application/json")
    public HostelOwnerModel hostelByName(String hostelOwnerModel){
    return  hostelOwnerService.getHostelBYName(hostelOwnerModel);
    }
    @DeleteMapping(value = "/delteBYName")
    public DeleteResult deletehostelByName(String  hostelName){
        return hostelOwnerService.deleteByHostelName(hostelName);
    }
    @PutMapping("/activate")
    public String  activateHostel(String  id){
      return  hostelOwnerService.activateHostel(id);
    }
    @PutMapping("/deactive")
    public String  deactivateHostel(String id){
        return hostelOwnerService.deactiveHostel(id);
    }
    @GetMapping("/get")

    public String get() {
        return "getting";
    }
}
