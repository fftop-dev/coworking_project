package ch.zli.m223.ksh20.coworking_project.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.ksh20.coworking_project.model.User;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserImpl, Long> {

    public default User addUser(String firstName, String lastName, String email, String password, UserRole role) {
        return save(new UserImpl(firstName, lastName, email, password, role));
    }

    public default void addUser(UserImpl joeMama) {
        save(joeMama);
    }

    public default User findByEmail(String email) {
        return findAll().stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }

    public default User findByUuid(String uuid){
        return findAll().stream().filter(u -> u.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.firstName = :firstName, u.lastName = :lastName, u.email = :email, u.passwordHash = :password WHERE u.uuid = :uuid")
    void updateUserByUuid(String uuid, String firstName, String lastName, String email, String password);
}
