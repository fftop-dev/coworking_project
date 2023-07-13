package ch.zli.m223.ksh20.coworking_project.init;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationType;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserRole;
import ch.zli.m223.ksh20.coworking_project.repository.ReservationRepository;
import ch.zli.m223.ksh20.coworking_project.repository.UserRepository;

@Component
public class ServerInit implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.addUser("Daniel", "Bengl", "daniel.bengl@gmail.com", "123", UserRole.MEMBER);
        userRepository.addUser("Francesco", "Feroldi", "francesco.feroldi@gmail.com", "123", UserRole.ADMIN);
        userRepository.addUser("Jenith", "Jeyranjan", "jenith.jeyranjan@gmail.com", "123", UserRole.GUEST);

        UserImpl user = new UserImpl("Philip", "Jovanovic", "philip@gmail.com", "test", UserRole.MEMBER);
        userRepository.addUser(user);

        ReservationImpl reservation = new ReservationImpl(user, LocalDate.now(), ReservationType.AFTERNOON);
        ReservationImpl reservation2 = new ReservationImpl(user, LocalDate.now(), ReservationType.MORNING);
        ReservationImpl reservation3 = new ReservationImpl(user, LocalDate.now(), ReservationType.AFTERNOON);
        reservationRepository.addReservation(reservation);
        reservationRepository.addReservation(reservation2);
        reservationRepository.addReservation(reservation3);
    }

}