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

        UserImpl user1 = new UserImpl("Daniel", "Bengl", "daniel.bengl@gmail.com", "123", UserRole.MEMBER);
        UserImpl user2 = new UserImpl("Francesco", "Feroldi", "francesco.feroldi@gmail.com", "123", UserRole.ADMIN);
        UserImpl user3 = new UserImpl("Jenith", "Jeyranjan", "jenith.jeyranjan@gmail.com", "123", UserRole.GUEST);
        UserImpl user4 = new UserImpl("Philip", "Jovanovic", "philip@gmail.com", "test", UserRole.MEMBER);

        user1.setUuid("b628ece1-c66c-4e54-88d2-034e177d63c8");
        user2.setUuid("c2bd8bcf-90e2-4d52-bf45-49bd7ff00e1a");
        user3.setUuid("a96c411c-1a21-4d9a-8485-30a83d3b9754");
        user4.setUuid("163cad2f-8ed7-4a58-a2db-e2c75aaeb186");

        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);
        userRepository.addUser(user4);

        ReservationImpl reservation1 = new ReservationImpl(user1, LocalDate.now(), ReservationType.AFTERNOON);
        ReservationImpl reservation2 = new ReservationImpl(user2, LocalDate.now(), ReservationType.MORNING);
        ReservationImpl reservation3 = new ReservationImpl(user3, LocalDate.now(), ReservationType.FULL_DAY);
        ReservationImpl reservation4 = new ReservationImpl(user3, LocalDate.now(), ReservationType.AFTERNOON);

        reservation1.setUuid("b89ff036-10f3-4a0c-9e01-caf4d9184d19");
        reservation2.setUuid("1369fa4f-bf52-4b5a-84f4-35d4129e5455");
        reservation3.setUuid("73368b26-3b4f-4d46-90f9-57a495dd71e0");
        reservation4.setUuid("14561e7a-38a5-41b0-b6de-93f10c75b263");

        reservationRepository.addReservation(reservation1);
        reservationRepository.addReservation(reservation2);
        reservationRepository.addReservation(reservation3);
        reservationRepository.addReservation(reservation4);
    }

}