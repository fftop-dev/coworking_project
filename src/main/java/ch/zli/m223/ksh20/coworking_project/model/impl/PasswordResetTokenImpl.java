package ch.zli.m223.ksh20.coworking_project.model.impl;

import java.time.LocalDate;
import java.util.UUID;

import ch.zli.m223.ksh20.coworking_project.model.PasswordResetToken;
import ch.zli.m223.ksh20.coworking_project.model.User;
import jakarta.persistence.*;

@Entity
public class PasswordResetTokenImpl implements PasswordResetToken {

    @Id
    @Column(unique = true)
    private String uuid;

    @Column(nullable = false)
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid", nullable = false)
    private UserImpl user;

    @Column(nullable = false)
    private LocalDate expireDate;

    public PasswordResetTokenImpl() {

    }

    public PasswordResetTokenImpl(String token, UserImpl user, LocalDate expireDate) {
        this.uuid = UUID.randomUUID().toString();
        this.token = token;
        this.user = user;
        this.expireDate = expireDate;
    }

    @Override
    public String getUuid() {
        return this.uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public UserImpl getUser() {
        return this.user;
    }

    @Override
    public void setUser(UserImpl user) {
        this.user = user;
    }

    @Override
    public LocalDate getExpireDate() {
        return this.expireDate;
    }

    @Override
    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

}
