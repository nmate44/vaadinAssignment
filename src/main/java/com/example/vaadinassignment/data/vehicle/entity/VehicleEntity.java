package com.example.vaadinassignment.data.vehicle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "car")
@Entity
public class VehicleEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "doors")
    private int doors;
    @Column(name = "productionyear")
    private int productionYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }
}
