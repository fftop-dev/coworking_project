package ch.zli.m223.ksh20.coworking_project.controller.rest.dto;

import ch.zli.m223.ksh20.coworking_project.model.User;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserRole;

public class UserDto {
    public String firstName;
    public String lastName;
    public String email;
    public UserRole role;

    public UserDto(User user) {
        firstName = user.getFirstName();
        lastName =user.getLastName();
        email = user.getEmail();
        role = user.getRole();
    }
}
