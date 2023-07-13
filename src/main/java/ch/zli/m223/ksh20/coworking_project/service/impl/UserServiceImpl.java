package ch.zli.m223.ksh20.coworking_project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.UserInputDto;
import ch.zli.m223.ksh20.coworking_project.model.User;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserRole;
import ch.zli.m223.ksh20.coworking_project.repository.UserRepository;
import ch.zli.m223.ksh20.coworking_project.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public User createUser(UserInputDto inputDto) {

        if (userRepository.findByEmail(inputDto.email) != null) {
            throw new IllegalArgumentException("User with email " + inputDto.email + " already exists");
        }

        UserImpl user = new UserImpl(inputDto.firstName, inputDto.lastName, inputDto.email, inputDto.password,
                UserRole.MEMBER);

        userRepository.save(user);

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
