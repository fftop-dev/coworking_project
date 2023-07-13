package ch.zli.m223.ksh20.coworking_project.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zli.m223.ksh20.coworking_project.model.impl.PasswordResetTokenImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;
import ch.zli.m223.ksh20.coworking_project.repository.PasswordResetTokenRepository;
import ch.zli.m223.ksh20.coworking_project.service.PasswordResetService;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordResetTokenRepository repository;

    @Override
    public void sendPasswordResetEmail(String email) {
        UserImpl user = (UserImpl) userService.getUserByEmail(email);

        if (user == null)
            return; // Do nothing (security reasons)

        PasswordResetTokenImpl token = new PasswordResetTokenImpl(user, LocalDate.now().plusDays(1));
        repository.save(token);

        // TODO: send mail
        System.out.println("http://localhost:8080/web/password-reset?token=" + token.getToken());
    }

    @Override
    public void resetPassword(String token, String password) {
        PasswordResetTokenImpl tokenObj = repository.getPasswordResetTokenByToken(token);

        if (tokenObj == null)
            throw new IllegalArgumentException("Invalid token");

        if (tokenObj.getExpireDate().isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Token expired");

        tokenObj.getUser().setPassword(password);

        tokenObj.setExpireDate(LocalDate.now());
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
