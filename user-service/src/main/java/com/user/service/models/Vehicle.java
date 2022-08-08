package com.user.service.models;

public class Vehicle {
    private String brand;
    private String model;
    private Integer userId;

    public Vehicle() {
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
