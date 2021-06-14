package com.example.vaadinassignment.authentication.role.service;

import com.example.vaadinassignment.authentication.role.entity.RoleEntity;
import com.example.vaadinassignment.data.core.service.crud.CoreCRUDServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends CoreCRUDServiceImpl<RoleEntity> implements RoleService {

    @Override
    protected void updateCore(RoleEntity persistedEntity, RoleEntity entity) {
        persistedEntity.setAuthority(entity.getAuthority());
    }

    @Override
    protected Class<RoleEntity> getManagedClass() {
        return RoleEntity.class;
    }
}
