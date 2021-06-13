package com.example.vaadinassignment.data.vehicle.service;

import com.example.vaadinassignment.data.core.service.crud.CoreCRUDServiceImpl;
import com.example.vaadinassignment.data.manufacturer.entity.ManufacturerEntity;
import com.example.vaadinassignment.data.vehicle.entity.VehicleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<VehicleEntity> getAllByNameFilter(String filter) {
        if(filter == null || filter.isEmpty()) {
            return getAll();
        } else {
            return entityManager.createQuery(
                    "SELECT n FROM " + VehicleEntity.class.getSimpleName() + " n WHERE LOWER(n.type) LIKE LOWER(CONCAT('" + filter + "', '%'))"
            ).getResultList();
        }
    }

    @Override
    public List<VehicleEntity> getAllByProdYrFilter(String filter) {
        if(filter == null || filter.isEmpty()) {
            return getAll();
        } else {
            return entityManager.createQuery(
                    "SELECT n FROM " + VehicleEntity.class.getSimpleName() + " n WHERE n.productionYear = " + filter
            ).getResultList();
        }
    }
}
