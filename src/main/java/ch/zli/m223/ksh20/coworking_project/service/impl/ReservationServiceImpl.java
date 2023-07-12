package ch.zli.m223.ksh20.coworking_project.service.impl;

import ch.zli.m223.ksh20.coworking_project.model.Reservation;
import ch.zli.m223.ksh20.coworking_project.repository.ReservationRepository;
import ch.zli.m223.ksh20.coworking_project.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getReservationList() {
        return new ArrayList<>(reservationRepository.findAll());
    }
}