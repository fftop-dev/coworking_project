package ch.zli.m223.ksh20.coworking_project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zli.m223.ksh20.coworking_project.model.User;
import ch.zli.m223.ksh20.coworking_project.service.AuthenticationService;
import ch.zli.m223.ksh20.coworking_project.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserService userService;

    @Override
    public boolean login(String email, String password) {
        User user = userService.getUserByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("User with email " + email + " does not exist");
        }

        return user.checkPassword(password);
    }

}
