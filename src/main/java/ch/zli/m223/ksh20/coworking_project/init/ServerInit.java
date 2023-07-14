package ch.zli.m223.ksh20.coworking_project.init;

import java.time.LocalDate;

import ch.zli.m223.ksh20.coworking_project.model.User;
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

        UserImpl user1 = new UserImpl("Daniel", "Bengl", "daniel.bengl@gmail.com", "123", UserRole.MEMBER);
        UserImpl user2 = new UserImpl("Francesco", "Feroldi", "francesco.feroldi@gmail.com", "123", UserRole.ADMIN);
        UserImpl user3 = new UserImpl("Jenith", "Jeyranjan", "jenith.jeyranjan@gmail.com", "123", UserRole.GUEST);
        UserImpl user4 = new UserImpl("Philip", "Jovanovic", "philip@gmail.com", "test", UserRole.MEMBER);


        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);
        userRepository.addUser(user4);

        ReservationImpl reservation1 = new ReservationImpl(user1, LocalDate.now(), ReservationType.AFTERNOON);
        ReservationImpl reservation2 = new ReservationImpl(user2, LocalDate.now(), ReservationType.MORNING);
        ReservationImpl reservation3 = new ReservationImpl(user3, LocalDate.now(), ReservationType.FULL_DAY);
        ReservationImpl reservation4 = new ReservationImpl(user3, LocalDate.now(), ReservationType.AFTERNOON);

        reservationRepository.addReservation(reservation1);
        reservationRepository.addReservation(reservation2);
        reservationRepository.addReservation(reservation3);
        reservationRepository.addReservation(reservation4);
    }

}