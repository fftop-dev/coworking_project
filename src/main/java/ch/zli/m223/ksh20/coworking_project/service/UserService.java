package ch.zli.m223.ksh20.coworking_project.service;

import java.util.List;

import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.UserInputDto;
import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.UserNoReservationDto;
import ch.zli.m223.ksh20.coworking_project.model.User;

public interface UserService {

    List<User> getUserList();

    User createUser(UserInputDto inputDto);

    UserNoReservationDto updateUser(String uuid, UserInputDto inputDto);

    User getUserByEmail(String email);
}
