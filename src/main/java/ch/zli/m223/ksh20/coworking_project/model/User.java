package ch.zli.m223.ksh20.coworking_project.model;

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
    String setPassword();
    boolean checkPassword(String passwordHash);

}
