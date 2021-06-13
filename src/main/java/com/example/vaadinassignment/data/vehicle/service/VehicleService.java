package com.example.vaadinassignment.data.vehicle.service;

import com.example.vaadinassignment.data.core.service.crud.CoreCRUDService;
import com.example.vaadinassignment.data.vehicle.entity.VehicleEntity;

import java.util.List;

public interface VehicleService extends CoreCRUDService<VehicleEntity> {
    List<VehicleEntity> getAllByNameFilter(String filter);
    List<VehicleEntity> getAllByProdYrFilter(String filter);
}
