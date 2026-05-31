package com.example.coursework;

public class DealerItem {
    private String dealerItemName;
    private String dealerItemBrand;
    private double dealerItemPrice;
    private short dealerItemQuantity;

    public DealerItem(String dealerItemName, String dealerItemBrand, double dealerItemPrice, short dealerItemQuantity) {
        this.dealerItemName = dealerItemName;
        this.dealerItemBrand = dealerItemBrand;
        this.dealerItemPrice = dealerItemPrice;
        this.dealerItemQuantity = dealerItemQuantity;
    }

    public String getDealerItemName() {return dealerItemName;}
    public String getDealerItemBrand() {return dealerItemBrand;}
    public double getDealerItemPrice() {return dealerItemPrice;}
    public short getDealerItemQuantity() {return dealerItemQuantity;}

    public void setDealerItemName(String dealerItemName) {this.dealerItemName = dealerItemName;}
    public void setDealerItemBrand(String dealerItemBrand) {this.dealerItemBrand = dealerItemBrand;}
    public void setDealerItemPrice(double dealerItemPrice) {this.dealerItemPrice = dealerItemPrice;}
    public void setDealerItemQuantity(short dealerItemQuantity) {this.dealerItemQuantity = dealerItemQuantity;}
}
