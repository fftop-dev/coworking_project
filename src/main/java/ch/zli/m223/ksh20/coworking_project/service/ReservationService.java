package ch.zli.m223.ksh20.coworking_project.service;

import ch.zli.m223.ksh20.coworking_project.model.Reservation;
import ch.zli.m223.ksh20.coworking_project.model.User;

import java.util.List;

public interface ReservationService {
    List<Reservation> getReservationList();
}
