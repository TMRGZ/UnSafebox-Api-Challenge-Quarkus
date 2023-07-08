package com.rviewer.skeletons.infrastructure.config;

import com.rviewer.skeletons.domain.service.SafeboxService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.Collections;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorizeExchangeSpec -> {
                    authorizeExchangeSpec.pathMatchers(HttpMethod.POST, "/safebox").permitAll();
                    authorizeExchangeSpec.anyExchange().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService(SafeboxService safeboxService) {
        return username -> safeboxService.getSafebox(username)
                .map(safebox -> new User(safebox.getName(), safebox.getPassword(), Collections.emptyList()));
    }
}
