package ch.zli.m223.ksh20.coworking_project.controller.rest.dto;

import java.time.LocalDate;

import ch.zli.m223.ksh20.coworking_project.model.Reservation;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationStatus;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationType;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;

public class UserReservationDto {

    public String uuid;

    public String user_uuid;
    public LocalDate date;
    public ReservationType type;
    public ReservationStatus status;

    public UserReservationDto(Reservation reservation) {
        uuid = reservation.getUuid();
        user_uuid = reservation.getUser().getUuid();
        date = reservation.getDate();
        type = reservation.getType();
        status = reservation.getStatus();
    }
}
