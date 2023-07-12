package ch.zli.m223.ksh20.coworking_project.model;

import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationStatus;
import ch.zli.m223.ksh20.coworking_project.model.impl.ReservationType;
import ch.zli.m223.ksh20.coworking_project.model.impl.UserImpl;

import java.time.LocalDate;

public interface Reservation {
    String getUuid();
    void setUuid(String uuid);

    UserImpl getUser();
    void setUser(UserImpl user);

    LocalDate getDate();
    void setDate(LocalDate date);

    ReservationType getType();
    void setType(ReservationType type);

    ReservationStatus getStatus();
    void setStatus(ReservationStatus status);
}
