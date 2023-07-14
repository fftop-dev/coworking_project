package ch.zli.m223.ksh20.coworking_project.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ch.zli.m223.ksh20.coworking_project.model.User;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationStatus;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationType;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;
import ch.zli.m223.ksh20.coworking_project.repository.ReservationRepository;
import ch.zli.m223.ksh20.coworking_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zli.m223.ksh20.coworking_project.model.Reservation;
import ch.zli.m223.ksh20.coworking_project.repository.ReservationRepository;
import ch.zli.m223.ksh20.coworking_project.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Reservation> getReservationList() {
        return new ArrayList<>(reservationRepository.findAll());
    }

    @Override
    public List<Reservation> getReservationList(String uuid) {
        return new ArrayList<>(reservationRepository.findAllForUuid(uuid));
    }

    @Override
    public boolean setReservationStatus(String uuid, String status) {
        Reservation reservation = reservationRepository.findByUuid(uuid);
        if (reservation != null){
            if (status.equals("APPROVED")){
                reservation.setStatus(ReservationStatus.APPROVED);
            }
            else if (status.equals("REJECTED")){
                reservation.setStatus(ReservationStatus.REJECTED);
            }
            else{
                return false;
            }
            return reservationRepository.updateReservationStatus(uuid, reservation.getStatus()) == 1;
        }
        return false;
    }

    @Override
    public boolean checkReservationUuid(String reservationUuid, String userUuid) {
        Reservation reservation = reservationRepository.findByUuid(reservationUuid);
        if (reservation != null){
            if (reservation.getUser().getUuid().equals(userUuid)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteReservation(String uuid) {
        if ((ReservationImpl) reservationRepository.findByUuid(uuid) != null){
            reservationRepository.delete((ReservationImpl) reservationRepository.findByUuid(uuid));
            return true;
        }
        return false;
    }

    @Override
    public boolean addReservation(String user_uuid, String date, String type) {
        UserImpl user = (UserImpl) userRepository.findByUuid(user_uuid);
        LocalDate dateParsed = LocalDate.parse(date);
        ReservationType typeParsed = null;
        if (type.equals("AFTERNOON")){
            typeParsed = ReservationType.AFTERNOON;
        }
        else if (type.equals("MORNING")){
            typeParsed = ReservationType.MORNING;
        }
        else if (type.equals("FULL_DAY")){
            typeParsed = ReservationType.FULL_DAY;
        }
        else{
            return false;
        }

        if (user == null){
            return false;
        }

        ReservationImpl reservation = new ReservationImpl(user, dateParsed, typeParsed);
        return reservationRepository.addReservation(reservation) != null;
    }

    @Override
    public Object getReservationStats() {
        Map<String, Integer> stats = new HashMap<>();

        var reservations = reservationRepository.findAll();
        stats.put("total reservations", reservations.size());
        stats.put("total approved",
                (int) reservations.stream().filter(r -> r.getStatus() == ReservationStatus.APPROVED).count());
        stats.put("total rejected",
                (int) reservations.stream().filter(r -> r.getStatus() == ReservationStatus.REJECTED).count());
        stats.put("total pending",
                (int) reservations.stream().filter(r -> r.getStatus() == ReservationStatus.PENDING).count());

        stats.put("total last week",
                (int) reservations.stream().filter(r -> r.getDate().isAfter(LocalDate.now().minusDays(7))).count());
        stats.put("total last month",
                (int) reservations.stream().filter(r -> r.getDate().isAfter(LocalDate.now().minusDays(30))).count());
        stats.put("total last year",
                (int) reservations.stream().filter(r -> r.getDate().isAfter(LocalDate.now().minusDays(365))).count());

        return stats;
    }
}
