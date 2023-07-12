package ch.zli.m223.ksh20.coworking_project.model.impl;

public enum ReservationType {

    FULL_DAY(0),
    MORNING(1),
    AFTERNOON(2);

    private final int value;

    ReservationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
