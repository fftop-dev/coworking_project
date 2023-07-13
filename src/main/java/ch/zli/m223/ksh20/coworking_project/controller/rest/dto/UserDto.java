package ch.zli.m223.ksh20.coworking_project.controller.rest.dto;

import java.util.List;
import java.util.stream.Collectors;

import ch.zli.m223.ksh20.coworking_project.model.User;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserRole;

public class UserDto extends UserNoReservationDto {

    public List<ReservationDto> reservations;

    public UserDto(User user) {
        super(user);
        reservations = user.getReservations().stream().map(ReservationDto::new).collect(Collectors.toList());
    }
}
