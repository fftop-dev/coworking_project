package ch.zli.m223.ksh20.coworking_project.service.impl;

import java.util.ArrayList;
import java.util.List;

import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.UserInputDto;
import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.UserNoReservationDto;
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
            return null;
        }

        UserImpl user = new UserImpl(inputDto.firstName, inputDto.lastName, inputDto.email, inputDto.password,
                UserRole.MEMBER);

        userRepository.save(user);

        return user;
    }

    @Override
    public UserNoReservationDto updateUser(String uuid, UserInputDto inputDto) {
        if (userRepository.findByEmail(inputDto.email) == null){

            User user = userRepository.findByUuid(uuid);

            if (!inputDto.firstName.isBlank()) {
                user.setFirstName(inputDto.firstName);
            }
            if (!inputDto.lastName.isBlank()) {
                user.setLastName(inputDto.lastName);
            }
            if (!inputDto.email.isBlank()) {
                user.setEmail(inputDto.email);
            }
            if (!inputDto.password.isBlank()) {
                user.setPassword(inputDto.password);
            }

            userRepository.updateUserByUuid(uuid, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPasswordHash());
            return new UserNoReservationDto(user);

        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(User user) {
        UserImpl userImpl = (UserImpl) user;
        return userRepository.save(userImpl);
    }

    @Override
    public UserDto getUserByUuid(String uuid) {
        if (userRepository.findByUuid(uuid) != null){
            return new UserDto(userRepository.findByUuid(uuid));
        }
        return null;
    }

    @Override
    public boolean deleteUserByUuid(String uuid) {
        try {
            userRepository.delete((UserImpl) userRepository.findByUuid(uuid));
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
