package ch.zli.m223.ksh20.coworking_project.model.impl;

public enum ReservationStatus {
    PENDING(0),
    APPROVED(1),
    REJECTED(2);

    private final int value;

    ReservationStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
