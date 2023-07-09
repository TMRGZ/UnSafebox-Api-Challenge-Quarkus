package com.rviewer.skeletons.domain.service.impl;

import com.rviewer.skeletons.domain.exception.SafeboxAlreadyExistsException;
import com.rviewer.skeletons.domain.model.Safebox;
import com.rviewer.skeletons.domain.repository.SafeboxRepository;
import com.rviewer.skeletons.domain.service.PasswordManager;
import com.rviewer.skeletons.domain.service.SafeboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class SafeboxServiceImpl implements SafeboxService {

    private final SafeboxRepository safeboxRepository;

    private final PasswordManager passwordManager;

    @Override
    public Mono<Safebox> createSafebox(String safeboxName, String safeboxPassword) {
        log.info("Creating safebox with name {} and password (hidden)", safeboxName);
        return getSafebox(safeboxName).hasElement().flatMap(exists -> BooleanUtils.isFalse(exists)
                ? safeboxRepository.save(generateEncodedSafebox(safeboxName, safeboxPassword))
                : Mono.error(() -> {
                    log.error("Safebox {} already exists, cancelling...", safeboxName);
                    return new SafeboxAlreadyExistsException();
                }
        ));
    }

    private Safebox generateEncodedSafebox(String safeboxName, String safeboxPassword) {
        log.info("Encoding password for new safebox {}", safeboxName);
        return Safebox.builder()
                .name(safeboxName)
                .password(passwordManager.encode(safeboxPassword))
                .build();
    }

    @Override
    public Mono<Safebox> getSafebox(Long id) {
        log.info("Retrieving safebox {}", id);
        return safeboxRepository.findById(id);
    }

    @Override
    public Mono<Safebox> getSafebox(String name) {
        log.info("Retrieving safebox with name {}", name);
        return safeboxRepository.findByNameIgnoreCase(name);
    }
}
