package com.example.vaadinassignment.authentication.user.entity;

import com.example.vaadinassignment.authentication.role.entity.RoleEntity;
import com.example.vaadinassignment.data.core.entity.CoreEntity;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@NamedQuery(name = UserEntity.FIND_USER_BY_USERNAME, query = "SELECT u FROM UserEntity u where u.username=:username")
@Table(name = "app_user")
@Entity
public class UserEntity extends CoreEntity implements UserDetails {
    public static final String FIND_USER_BY_USERNAME = "UserEntity.findUserByUsername";

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> authorities;

    @Override
    public List<RoleEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<RoleEntity> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

