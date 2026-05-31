package com.example.coursework;

import java.util.Arrays;

public class Dealer {
    private String name;
    private String phoneNumber;
    private String location;
    private final DealerItem[] DEALER_ITEMS;

    public Dealer(String name, String phoneNumber, String location, DealerItem[] DEALER_ITEMS) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.DEALER_ITEMS = DEALER_ITEMS;
    }

    public String getName() {return name;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getLocation() {return location;}
    public DealerItem[] getItems() {return DEALER_ITEMS;}

    public void setName(String name) {this.name = name;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setLocation(String location) {this.location = location;}

    @Override
    public String toString() {
        return "Dealer Name: " + name + ", Phone Number: " +
                phoneNumber + " , Location: "
                + location +
                ", Items: " + Arrays.toString(DEALER_ITEMS);
    }
}