package ch.zli.m223.ksh20.coworking_project.model;

import java.time.LocalDate;

public interface PasswordResetToken {

    public String getUuid();

    public void setUuid(String uuid);

    public String getToken();

    public void setToken(String token);

    public User getUser();

    public void setUser(User user);

    public LocalDate getExpireDate();

    public void setExpireDate(LocalDate expireDate);
}
