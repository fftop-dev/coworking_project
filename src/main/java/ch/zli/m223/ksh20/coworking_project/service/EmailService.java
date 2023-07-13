package ch.zli.m223.ksh20.coworking_project.service;

public interface EmailService {
    public void sendEmail(String to, String subject, String text);
}
