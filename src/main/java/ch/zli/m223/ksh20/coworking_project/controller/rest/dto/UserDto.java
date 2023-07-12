package ch.zli.m223.ksh20.coworking_project.controller.rest.dto;

import java.util.List;
import java.util.stream.Collectors;

import ch.zli.m223.ksh20.coworking_project.model.User;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserRole;

public class UserDto {

    public String uuid;
    public String firstName;
    public String lastName;
    public String email;
    public UserRole role;
    public List<ReservationDto> reservations;

    public UserDto(User user) {
        uuid = user.getUuid();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        role = user.getRole();
        reservations = user.getReservations().stream().map(ReservationDto::new).collect(Collectors.toList());
    }
}
