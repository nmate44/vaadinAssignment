package com.example.vaadinassignment.data.manufacturer.service;

import com.example.vaadinassignment.data.core.service.crud.CoreCRUDService;
import com.example.vaadinassignment.data.manufacturer.entity.ManufacturerEntity;

import java.util.List;

public interface ManufacturerService extends CoreCRUDService<ManufacturerEntity> {
    List<ManufacturerEntity> getAllByFilter(String filter);
}
