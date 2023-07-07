package com.rviewer.skeletons.domain.service;

public interface PasswordEncoder {

    String encode(String password);

    boolean matches(String plainPassword, String encodedPassword);

}
