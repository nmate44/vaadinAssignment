package com.example.vaadinassignment.authentication.user.service;

import com.example.vaadinassignment.authentication.user.entity.UserEntity;
import com.example.vaadinassignment.data.core.service.crud.CoreCRUDServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;

@Service
public class UserServiceImpl extends CoreCRUDServiceImpl<UserEntity> implements UserService {
    @Override
    protected void updateCore(UserEntity persistedEntity, UserEntity entity) {
        persistedEntity.setAuthorities(entity.getAuthorities());
        persistedEntity.setUsername(entity.getUsername());
    }

    @Override
    protected Class<UserEntity> getManagedClass() {
        return UserEntity.class;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TypedQuery<UserEntity> query = entityManager.createNamedQuery(UserEntity.FIND_USER_BY_USERNAME, UserEntity.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
