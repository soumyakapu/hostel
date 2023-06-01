package com.app.Model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HostelOwnerModel {
    private String hostelName;
    private  String hostelOwnerName;
    private String  hostelAddress;
    private HostelContact hostelContact;
    private HostelFacilities facilities;
    private boolean isActive;

}
