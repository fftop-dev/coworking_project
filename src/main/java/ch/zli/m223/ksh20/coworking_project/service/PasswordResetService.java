package ch.zli.m223.ksh20.coworking_project.service;

public interface PasswordResetService {
    public void sendPasswordResetEmail(String email);

    public void resetPassword(String token, String password);
}
