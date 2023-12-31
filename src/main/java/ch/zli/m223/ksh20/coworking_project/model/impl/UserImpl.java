package ch.zli.m223.ksh20.coworking_project.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Result;
import ch.zli.m223.ksh20.coworking_project.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "Users")
public class UserImpl implements User {

    @Id
    @Column(unique = true)
    private String uuid;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ReservationImpl> reservations;

    protected UserImpl() {

    }

    public UserImpl(String first_name, String last_name, String email, String password, UserRole role) {
        uuid = UUID.randomUUID().toString();
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.passwordHash = hashPassword(password);
        this.role = role;
        this.reservations = new ArrayList<>();
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public UserRole getRole() {
        return role;
    }

    @Override
    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public void setPassword(String password) {
        this.passwordHash = hashPassword(password);
    }

    private String hashPassword(String password) {
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        return bcryptHashString;
    }

    @Override
    public boolean checkPassword(String password) {
        Result result = BCrypt.verifyer().verify(password.toCharArray(), this.passwordHash);
        return result.verified;
    }

    @Override
    public List<ReservationImpl> getReservations() {
        return reservations;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
