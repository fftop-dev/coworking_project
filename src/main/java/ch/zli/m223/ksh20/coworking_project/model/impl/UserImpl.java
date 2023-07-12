package ch.zli.m223.ksh20.coworking_project.model.impl;

import ch.zli.m223.ksh20.coworking_project.model.User;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class UserImpl implements User {

    @Id
    @Column(unique = true)
    private String uuid;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private UserRole role;

    protected UserImpl() {

    }

    public UserImpl(String first_name, String last_name, String email, String passwordHash, UserRole role) {
        uuid = UUID.randomUUID().toString();
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }
    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String getFirst_name() {
        return first_name;
    }

    @Override
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public String getLast_name() {
        return last_name;
    }

    @Override
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public UserRole getRole() {
        return role;
    }

    @Override
    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String setPassword() {
        return null;
    }

    @Override
    public boolean checkPassword(String passwordHash){
        return this.passwordHash.equals(passwordHash);
    }
}

