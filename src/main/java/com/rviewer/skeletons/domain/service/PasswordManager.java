package com.rviewer.skeletons.domain.service;

public interface PasswordManager {

    String encode(String password);

    boolean matches(String plainPassword, String encodedPassword);

}
