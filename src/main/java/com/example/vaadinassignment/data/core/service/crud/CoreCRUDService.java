package com.example.vaadinassignment.data.core.service.crud;

import com.example.vaadinassignment.data.core.entity.CoreEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface CoreCRUDService <T extends CoreEntity> {
    @Transactional
    void add(T entity);

    List<T> getAll();

    T findById(int id);

    @Transactional
    void update(T entity);

    @Transactional
    void remove(T entity);
}
