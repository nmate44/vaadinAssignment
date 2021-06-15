package com.example.vaadinassignment.authentication.user.config;

import com.example.vaadinassignment.authentication.role.entity.RoleEntity;
import com.example.vaadinassignment.authentication.role.service.RoleService;
import com.example.vaadinassignment.authentication.user.entity.UserEntity;
import com.example.vaadinassignment.authentication.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserAppInitConfig {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @PostConstruct
    private void init() {
        List<RoleEntity> roleEntities = roleService.getAll();
        RoleEntity admin = new RoleEntity();
        if (roleEntities.isEmpty()) {
            admin.setAuthority("ROLE_ADMIN");
            roleService.add(admin);
        }

        List<UserEntity> userEntities = userService.getAll();
        if (userEntities.isEmpty()) {
            UserEntity entity = new UserEntity();
            entity.setPassword(new BCryptPasswordEncoder().encode("admin"));
            entity.setUsername("admin");
            entity.setAuthorities(new ArrayList<>());
            entity.getAuthorities().add(admin);
            userService.add(entity);
        }
    }
}

