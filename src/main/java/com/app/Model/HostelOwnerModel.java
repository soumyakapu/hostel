package com.app.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "hostelOwnerModel")
public class HostelOwnerModel {
    @Id
    private String id;
    private String hostelName;
    private String hostelOwnerName;
    private String  hostelAddress;
    private String email;
    private String password;
    private HostelContact hostelContact;
    private HostelFacilities facilities;
    private boolean isActive;

}
