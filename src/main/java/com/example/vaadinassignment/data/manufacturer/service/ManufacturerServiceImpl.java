package com.example.vaadinassignment.data.manufacturer.service;

import com.example.vaadinassignment.data.core.service.crud.CoreCRUDServiceImpl;
import com.example.vaadinassignment.data.manufacturer.entity.ManufacturerEntity;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerServiceImpl extends CoreCRUDServiceImpl<ManufacturerEntity> implements ManufacturerService{
    @Override
    protected void updateCore(ManufacturerEntity persistedEntity, ManufacturerEntity entity) {
        persistedEntity.setName(entity.getName());
    }

    @Override
    protected Class<ManufacturerEntity> getManagedClass() { return ManufacturerEntity.class; }
}
