package ch.zli.m223.ksh20.coworking_project.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.ksh20.coworking_project.model.impl.PasswordResetTokenImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;

public interface PasswordResetTopenRepository extends JpaRepository<PasswordResetTokenImpl, Long> {
    public default PasswordResetTokenImpl addReservation(String token, UserImpl user, LocalDate expireDate) {
        return save(new PasswordResetTokenImpl(token, user, expireDate));
    }
}
