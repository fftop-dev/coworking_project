package ch.zli.m223.ksh20.coworking_project.model.impl;

import java.time.LocalDate;
import java.util.UUID;

import ch.zli.m223.ksh20.coworking_project.model.Reservation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "Reservations")
public class ReservationImpl implements Reservation {

    @Id
    @Column(unique = true)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid", nullable = false)
    private UserImpl user;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private ReservationType type;

    @Column(nullable = false)
    private ReservationStatus status;

    public ReservationImpl() {

    }

    public ReservationImpl(UserImpl user, LocalDate date, ReservationType type) {
        uuid = UUID.randomUUID().toString();
        this.user = user;
        this.date = date;
        this.type = type;
        this.status = ReservationStatus.PENDING;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public UserImpl getUser() {
        return user;
    }

    public void setUser(UserImpl user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ReservationType getType() {
        return type;
    }

    public void setType(ReservationType type) {
        this.type = type;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
