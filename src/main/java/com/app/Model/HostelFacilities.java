package com.app.Model;

public class HostelFacilities {
    private String electricity;
    private String  water;
    private boolean roomAvailability;

    public HostelFacilities(String electricity, String water, boolean roomAvailability) {
        this.electricity = electricity;
        this.water = water;
        this.roomAvailability = roomAvailability;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public boolean isRoomAvailability() {
        return roomAvailability;
    }

    public void setRoomAvailability(boolean roomAvailability) {
        this.roomAvailability = roomAvailability;
    }

    @Override
    public String toString() {
        return "HostelFacilities{" +
                "electricity='" + electricity + '\'' +
                ", water='" + water + '\'' +
                ", roomAvailability=" + roomAvailability +
                '}';
    }
}
