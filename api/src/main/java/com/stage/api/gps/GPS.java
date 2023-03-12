package com.stage.api.gps;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "gps")
public class GPS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double latitude;
    private double longitude;

    public GPS(){ }

    public GPS(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
