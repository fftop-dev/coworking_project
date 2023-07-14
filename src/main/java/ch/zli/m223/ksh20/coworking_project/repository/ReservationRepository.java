package ch.zli.m223.ksh20.coworking_project.repository;

import java.time.LocalDate;
import java.util.List;

import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.ksh20.coworking_project.model.Reservation;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationType;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<ReservationImpl, Long> {

    public default Reservation addReservation(UserImpl user, LocalDate date, ReservationType type) {
        return save(new ReservationImpl(user, date, type));
    }

    public default Reservation addReservation(ReservationImpl reservation) {
        return save(reservation);
    }

    @Query("SELECT r FROM Reservations r WHERE r.user.uuid = :uuid")
    List<Reservation> findAllForUuid(String uuid);

    default Reservation findByUuid(String uuid){
        return findAll().stream().filter(r -> r.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    @Transactional
    @Modifying
    @Query("UPDATE Reservations r SET r.status = :status WHERE r.uuid = :uuid")
    int updateReservationStatus(String uuid, ReservationStatus status);
}
