package ch.zli.m223.ksh20.coworking_project.controller.rest.dto;

public class UserInputDto {
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    public UserInputDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

}
