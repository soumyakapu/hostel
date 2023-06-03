package com.app.controller;

import com.app.Model.HostelOwnerModel;
import com.app.exceptionHandler.HostelException;
import com.app.service.HostelOwnerService;
import com.mongodb.client.result.UpdateResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class HostelOwnerController {
    private final HostelOwnerService hostelOwnerService;
   public HostelOwnerController(final  HostelOwnerService hostelOwnerService){
        this.hostelOwnerService = hostelOwnerService;
    }

    @PostMapping (value = "/post", produces = "application/json")
    @PreAuthorize("isAuthenticated()")
    public HostelOwnerModel createHostel(@RequestBody HostelOwnerModel hostelOwnerModel) throws Exception {
        return hostelOwnerService.createHostel(hostelOwnerModel);

    }
    @PutMapping(value = "/update", produces = "application/json")
    public UpdateResult updateHostel(@RequestBody HostelOwnerModel hostelOwnerModel) throws HostelException {
       return  hostelOwnerService.updateHostel(hostelOwnerModel);
    }

    @GetMapping("/get")
    public  String get(){
       return "getting";
    }
}
