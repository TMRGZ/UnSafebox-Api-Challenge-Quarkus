package com.rviewer.skeletons.domain.service;

import java.util.List;

public interface SafeboxAuthService {

    List<String> openSafebox(String name, String password);

}
