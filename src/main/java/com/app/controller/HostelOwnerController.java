package com.app.controller;

import com.app.Model.HostelContact;
import com.app.Model.HostelOwnerModel;
import com.app.exceptionHandler.HostelException;
import com.app.service.HostelOwnerService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
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
    public HostelOwnerModel createHostel(@RequestBody HostelOwnerModel hostelOwnerModel) throws Exception {
        return hostelOwnerService.createHostel(hostelOwnerModel);

    }

    @PutMapping(value = "/update", produces = "application/json")
    public UpdateResult updateHostel(@RequestBody HostelOwnerModel hostelOwnerModel) throws HostelException {
        return hostelOwnerService.updateHostel(hostelOwnerModel);
    }

    @GetMapping(value = "/AllHostels", produces = "application/json")

    public List<HostelOwnerModel> allHostels(){

        return hostelOwnerService.getAll();
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
