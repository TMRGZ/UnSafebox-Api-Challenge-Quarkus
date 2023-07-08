package com.rviewer.skeletons.infrastructure.config;

import com.rviewer.skeletons.domain.repository.SafeboxRepository;
import com.rviewer.skeletons.domain.service.PasswordManager;
import com.rviewer.skeletons.domain.service.SafeboxAuthService;
import com.rviewer.skeletons.domain.service.SafeboxService;
import com.rviewer.skeletons.domain.service.impl.SafeboxServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Autowired
    private SafeboxRepository safeboxRepository;

    @Autowired
    private PasswordManager passwordManager;

    private SafeboxServiceImpl safeboxService;

    @PostConstruct
    public void setup() {
        this.safeboxService = new SafeboxServiceImpl(safeboxRepository, passwordManager);
    }

    @Bean
    public SafeboxService safeboxService() {
        return safeboxService;
    }

    @Bean
    public SafeboxAuthService safeboxAuthService() {
        return safeboxService;
    }
}
