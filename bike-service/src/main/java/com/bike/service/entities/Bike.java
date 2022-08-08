package com.bike.service.entities;

import javax.persistence.*;


@Table(name="`bike`")
@Entity
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bikeId;
    private Integer userId;
    private String brand;
    private String model;

    public Bike() {
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

}
