package com.example.vaadinassignment.data.vehicle.entity;

import com.example.vaadinassignment.data.core.entity.CoreEntity;
import com.example.vaadinassignment.data.manufacturer.entity.ManufacturerEntity;

import javax.persistence.*;

@Table(name = "car")
@Entity
public class VehicleEntity extends CoreEntity {
    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "manufacturer")
    private ManufacturerEntity manufacturer;

    @Column(name = "doors")
    private Long doors;

    @Column(name = "productionyear")
    private Long productionYear;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ManufacturerEntity getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerEntity manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getDoors() {
        return doors;
    }

    public void setDoors(Long doors) {
        this.doors = doors;
    }

    public Long getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Long productionYear) {
        this.productionYear = productionYear;
    }
}
