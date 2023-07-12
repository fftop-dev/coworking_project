package ch.zli.m223.ksh20.coworking_project.controller.rest.dto;

import ch.zli.m223.ksh20.coworking_project.model.Reservation;
import ch.zli.m223.ksh20.coworking_project.model.User;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationStatus;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationType;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserRole;

import java.time.LocalDate;

public class ReservationDto {

    public String uuid;

    public UserImpl user;
    public LocalDate date;
    public ReservationType type;
    public ReservationStatus status;

    public ReservationDto(Reservation reservation) {
        uuid = reservation.getUuid();
        user = reservation.getUser();
        date =reservation.getDate();
        type = reservation.getType();
        status = reservation.getStatus();
    }
}
