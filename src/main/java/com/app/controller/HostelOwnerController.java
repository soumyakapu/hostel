package com.app.controller;

import com.app.Model.HostelOwnerModel;
import com.app.service.HostelOwnerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/")
public class HostelOwnerController {
    private final HostelOwnerService hostelOwnerService;
    HostelOwnerController(final  HostelOwnerService hostelOwnerService){
        this.hostelOwnerService = hostelOwnerService;
    }
    @PostMapping("/createHostel")
    public HostelOwnerModel createHostel(@RequestBody HostelOwnerModel hostelOwnerModel){
      return   hostelOwnerService.createHostel(hostelOwnerModel);
    }
}
