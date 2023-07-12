package ch.zli.m223.ksh20.coworking_project.init;

import ch.zli.m223.ksh20.coworking_project.model.impl.UserRole;
import ch.zli.m223.ksh20.coworking_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerInit implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.addUser("Daniel", "Bengl", "daniel.bengl@gmail.com", "123", UserRole.MEMBER);
        userRepository.addUser("Francesco", "Feroldi", "francesco.feroldi@gmail.com", "123", UserRole.MEMBER);
        userRepository.addUser("Jenith", "Jeyranjan", "jenith.jeyranjan@gmail.com", "123", UserRole.MEMBER);
    }

}