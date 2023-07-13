package ch.zli.m223.ksh20.coworking_project.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.ksh20.coworking_project.model.impl.PasswordResetTokenImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenImpl, Long> {
    public default PasswordResetTokenImpl addPasswordResetToken(UserImpl user, LocalDate expireDate) {
        return save(new PasswordResetTokenImpl(user, expireDate));
    }

    public default PasswordResetTokenImpl addPasswordResetToken(PasswordResetTokenImpl passwordResetToken) {
        return save(passwordResetToken);
    }

    public default PasswordResetTokenImpl getPasswordResetTokenByToken(String token) {
        return findAll().stream().filter(u -> u.getToken().equals(token)).findFirst().orElse(null);
    }
}
