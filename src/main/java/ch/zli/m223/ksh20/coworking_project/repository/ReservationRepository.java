package ch.zli.m223.ksh20.coworking_project.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.ksh20.coworking_project.model.Reservation;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationType;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;

public interface ReservationRepository extends JpaRepository<ReservationImpl, Long> {

    public default Reservation addReservation(UserImpl user, LocalDate date, ReservationType type) {
        return save(new ReservationImpl(user, date, type));
    }

    public default Reservation addReservation(ReservationImpl reservation) {
        return save(reservation);
    }
}
