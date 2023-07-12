package ch.zli.m223.ksh20.coworking_project.model.impl;

public enum UserRole {
    GUEST(0),
    MEMBER(1),
    ADMIN(2);

    private final int value;

    UserRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
