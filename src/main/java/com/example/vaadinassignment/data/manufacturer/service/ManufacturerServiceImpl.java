package com.example.vaadinassignment.data.manufacturer.service;

import com.example.vaadinassignment.data.core.service.crud.CoreCRUDServiceImpl;
import com.example.vaadinassignment.data.manufacturer.entity.ManufacturerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl extends CoreCRUDServiceImpl<ManufacturerEntity> implements ManufacturerService{
    @Override
    protected void updateCore(ManufacturerEntity persistedEntity, ManufacturerEntity entity) {
        persistedEntity.setName(entity.getName());
    }

    @Override
    protected Class<ManufacturerEntity> getManagedClass() { return ManufacturerEntity.class; }

    @Override
    public List<ManufacturerEntity> getAllByFilter(String filter) {
        if(filter == null || filter.isEmpty()) {
            return getAll();
        } else {
            return entityManager.createQuery(
                    "SELECT n FROM " + ManufacturerEntity.class.getSimpleName() + " n WHERE LOWER(n.name) LIKE LOWER(CONCAT('%', '" + filter + "', '%'))"
            ).getResultList();
        }
    }
}
