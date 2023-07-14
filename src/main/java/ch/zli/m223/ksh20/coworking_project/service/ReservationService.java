package ch.zli.m223.ksh20.coworking_project.service;

import java.util.List;

import ch.zli.m223.ksh20.coworking_project.model.Reservation;

public interface ReservationService {
    List<Reservation> getReservationList();
    List<Reservation> getReservationList(String uuid);

    boolean setReservationStatus(String uuid, String status);

    boolean checkReservationUuid(String reservationUuid, String userUuid);

    boolean deleteReservation(String uuid);

    boolean addReservation(String user_uuid, String date, String type);
}
