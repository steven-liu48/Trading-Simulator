package com.example.trading_simulator;

import java.sql.Date;
import java.sql.Time;

public class Stock {
    private String name;
    private String fullName;

    // Stock info
    private double price;
    private double priceChange;
//    private double prevClose;
    private double open;
//    private double dayLow;
//    private double dayHigh;
//    private double hisLow;
//    private double hisHigh;
//    private double MarketCap;
//    private double volume;
//    private double circulatingSupply;
//    private double oneYearTargetEstimation;
//    private double avgVolume;
//    private double PERatio;
//    private double EPS;
//    private Time mktTime;
//    private Date startDate;
//
    // Company info
//    private String companyName;
//    private String companyAddress;
//    private String phoneNumber;
//    private String website;
//    private String sector;
//    private String industry;
//    private String numEmployees;
//    private String companyDescription;

    public Stock(String name, String fullName) {
        this.name = name;
        this.fullName = fullName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(double priceChange) {
        this.priceChange = priceChange;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }
}
