package com.example.vaadinassignment.data.core.service.crud;

import com.example.vaadinassignment.data.core.entity.CoreEntity;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public interface CoreCRUDService <T extends CoreEntity> {

    @Transactional
    void add(T entity);

    T findById(Long id);

    List<T> getAll();

    @Transactional
    void update(T entity);

    @Transactional
    void remove(T entity);
}
