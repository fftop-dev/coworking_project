package ch.zli.m223.ksh20.coworking_project.service.impl;

import ch.zli.m223.ksh20.coworking_project.service.PasswordResetService;

public class PasswordResetServiceImpl implements PasswordResetService {

    @Override
    public void sendPasswordResetEmail(String email) {
        // TODO: check if user exists
        // TODO: generate token
        // TODO: send email
    }

    @Override
    public void resetPassword(String token, String password) {
        // TODO: check if token is valid (validatePasswordResetToken)
        // TODO: get user by token
        // TODO: set new password
    }

    @Override
    public void validatePasswordResetToken(String token) {
        // TODO: check if token is valid
    }

    @Override
    public void invalidatePasswordResetToken(String token) {
        // TODO: get token from database
        // TODO: update token expiration date
    }

}
