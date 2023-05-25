package com.app.Model;

public class HostelOwnerModel {
    private String hostelName;
    private  String hostelOwnerName;
    private String  hostelAddress;
    private HostelContact hostelContact;
    private HostelFacilities facilities;

    public HostelOwnerModel(String hostelName, String hostelOwnerName, String hostelAddress, HostelContact hostelContact, HostelFacilities facilities) {
        this.hostelName = hostelName;
        this.hostelOwnerName = hostelOwnerName;
        this.hostelAddress = hostelAddress;
        this.hostelContact = hostelContact;
        this.facilities = facilities;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getHostelOwnerName() {
        return hostelOwnerName;
    }

    public void setHostelOwnerName(String hostelOwnerName) {
        this.hostelOwnerName = hostelOwnerName;
    }

    public String getHostelAddress() {
        return hostelAddress;
    }

    public void setHostelAddress(String hostelAddress) {
        this.hostelAddress = hostelAddress;
    }

    public HostelContact getHostelContact() {
        return hostelContact;
    }

    public void setHostelContact(HostelContact hostelContact) {
        this.hostelContact = hostelContact;
    }

    public HostelFacilities getFacilities() {
        return facilities;
    }

    public void setFacilities(HostelFacilities facilities) {
        this.facilities = facilities;
    }

    @Override
    public String toString() {
        return "HostelOwnerModel{" +
                "hostelName='" + hostelName + '\'' +
                ", hostelOwnerName='" + hostelOwnerName + '\'' +
                ", hostelAddress='" + hostelAddress + '\'' +
                ", hostelContact=" + hostelContact +
                ", facilities=" + facilities +
                '}';
    }
}
