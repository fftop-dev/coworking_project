package ch.zli.m223.ksh20.coworking_project.service.impl;

import ch.zli.m223.ksh20.coworking_project.model.User;
import ch.zli.m223.ksh20.coworking_project.repository.UserRepository;
import ch.zli.m223.ksh20.coworking_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        return new ArrayList<>(userRepository.findAll());
    }
}
