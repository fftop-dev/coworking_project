package ch.zli.m223.ksh20.coworking_project.repository;

import ch.zli.m223.ksh20.coworking_project.model.Reservation;
import ch.zli.m223.ksh20.coworking_project.model.User;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationType;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<ReservationImpl, Long> {

    public default Reservation addReservation(UserImpl user, LocalDate date, ReservationType type) {
        return save(new ReservationImpl(user, date, type));
    }
}
