package com.example.vaadinassignment.authentication.user.service;

import com.example.vaadinassignment.authentication.user.entity.UserEntity;
import com.example.vaadinassignment.data.core.service.crud.CoreCRUDService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends CoreCRUDService<UserEntity>, UserDetailsService {

}
