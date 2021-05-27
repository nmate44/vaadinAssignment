package com.example.vaadinassignment.data.manufacturer.entity;

import com.example.vaadinassignment.data.core.entity.CoreEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "manufacturer")
@Entity
public class ManufacturerEntity extends CoreEntity {
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
