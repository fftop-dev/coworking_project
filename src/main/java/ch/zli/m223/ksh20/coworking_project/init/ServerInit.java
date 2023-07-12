package ch.zli.m223.ksh20.coworking_project.init;

import ch.zli.m223.ksh20.coworking_project.model.Reservation;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationStatus;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationType;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserRole;
import ch.zli.m223.ksh20.coworking_project.repository.ReservationRepository;
import ch.zli.m223.ksh20.coworking_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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

        reservationRepository.addReservation(new UserImpl("Testuser", "Martin", "a.b@c.com", "123", UserRole.MEMBER), LocalDate.now(), ReservationType.AFTERNOON);

        System.out.println(userRepository.findAll());


    }

}