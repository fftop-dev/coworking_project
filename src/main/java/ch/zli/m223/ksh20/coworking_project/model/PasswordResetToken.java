package ch.zli.m223.ksh20.coworking_project.model;

import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;

import java.time.LocalDate;

import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;

public interface PasswordResetToken {

    public String getUuid();

    public void setUuid(String uuid);

    public String getToken();

    public void setToken(String token);

    public UserImpl getUser();

    public void setUser(UserImpl user);

    public LocalDate getExpireDate();

    public void setExpireDate(LocalDate expireDate);
}
