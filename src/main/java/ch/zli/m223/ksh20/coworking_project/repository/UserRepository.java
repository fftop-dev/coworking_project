package ch.zli.m223.ksh20.coworking_project.repository;

import ch.zli.m223.ksh20.coworking_project.model.User;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserImpl, Long> {

    public default User addUser(String firstName, String lastName, String email, String password, UserRole role) {
        return save(new UserImpl(firstName, lastName, email, password, role));
    }
}
