package com.example.vaadinassignment.data.vehicle.service;

import com.example.vaadinassignment.data.core.service.crud.CoreCRUDServiceImpl;
import com.example.vaadinassignment.data.vehicle.entity.VehicleEntity;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl extends CoreCRUDServiceImpl<VehicleEntity> implements VehicleService {
    @Override
    protected void updateCore(VehicleEntity persistedEntity, VehicleEntity entity) {
        persistedEntity.setType(entity.getType());
        persistedEntity.setManufacturer(entity.getManufacturer());
        persistedEntity.setDoors(entity.getDoors());
        persistedEntity.setProductionYear(entity.getProductionYear());
    }

    @Override
    protected Class<VehicleEntity> getManagedClass() { return VehicleEntity.class; }
}
