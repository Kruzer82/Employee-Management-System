package com.jabbour.ems.backend.entity;

import com.jabbour.ems.backend.service.renderer.annotations.Renderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity(name = "USER")
public class User extends AbstractEntity implements UserDetails {
    @Email(message = "Email niepoprawny")
    @NotBlank(message = "Nie może być puste")
    @Column(name = "USERNAME")
    @Renderer(fieldName = "username",label = "Nazwa użytkownika", editable = true, hidden = false, position = 1L, clazz = String.class)
    private String username;

    @NotBlank(message = "Nie może być puste")
    @Column(name = "PASSWORD_HASH")
    @Renderer(fieldName = "password",label = "Hasło (Hash)", editable = false, hidden = false, position = 1L, clazz = String.class)
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public String toString() {
        return "User{id= " + getId() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
