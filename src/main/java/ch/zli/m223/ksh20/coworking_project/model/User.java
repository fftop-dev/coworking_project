package ch.zli.m223.ksh20.coworking_project.model;

import java.util.List;

import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationImpl;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserRole;

public interface User {

    String getUuid();

    String getFirstName();

    void setFirstName(String first_name);

    String getLastName();

    void setLastName(String last_name);

    String getEmail();

    void setEmail(String email);

    UserRole getRole();

    void setRole(UserRole role);

    void setPassword(String password);

    boolean checkPassword(String passwordHash);

    List<ReservationImpl> getReservations();
}
