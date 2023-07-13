package ch.zli.m223.ksh20.coworking_project.model.impl;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Base64;
import java.util.UUID;

import ch.zli.m223.ksh20.coworking_project.model.PasswordResetToken;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "PasswordResetTokens")
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

    public PasswordResetTokenImpl(UserImpl user, LocalDate expireDate) {
        this.uuid = UUID.randomUUID().toString();
        this.token = generateToken();
        this.user = user;
        this.expireDate = expireDate;
    }

    private String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[64];
        random.nextBytes(bytes);

        return Base64.getEncoder().encodeToString(bytes);
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
