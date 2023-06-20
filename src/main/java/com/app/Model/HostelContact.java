package com.app.Model;
public class HostelContact {
    private long hostelContact;
    private long hostelOwnerContact;

    public HostelContact(long hostelContact, long hostelOwnerContact) {
        this.hostelContact = hostelContact;
        this.hostelOwnerContact = hostelOwnerContact;
    }

    public long getHostelContact() {
        return hostelContact;
    }

    public void setHostelContact(long hostelContact) {
        this.hostelContact = hostelContact;
    }

    public long getHostelOwnerContact() {
        return hostelOwnerContact;
    }

    public void setHostelOwnerContact(long hostelOwnerContact) {
        this.hostelOwnerContact = hostelOwnerContact;
    }

    @Override
    public String toString() {
        return "HostelContact{" +
                "hostelContact=" + hostelContact +
                ", hostelOwnerContact=" + hostelOwnerContact +
                '}';
    }
}
