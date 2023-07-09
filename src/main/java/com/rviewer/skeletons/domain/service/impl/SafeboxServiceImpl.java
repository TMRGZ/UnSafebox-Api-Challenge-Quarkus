package com.rviewer.skeletons.domain.service.impl;

import com.rviewer.skeletons.domain.exception.SafeboxAlreadyExistsException;
import com.rviewer.skeletons.domain.model.Safebox;
import com.rviewer.skeletons.domain.repository.SafeboxRepository;
import com.rviewer.skeletons.domain.service.PasswordManager;
import com.rviewer.skeletons.domain.service.SafeboxService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SafeboxServiceImpl implements SafeboxService {

    private final SafeboxRepository safeboxRepository;

    private final PasswordManager passwordManager;

    @Override
    public Mono<Safebox> createSafebox(String safeboxName, String safeboxPassword) {
        return safeboxRepository.findByNameIgnoreCase(safeboxName)
                .hasElement().flatMap(exists -> BooleanUtils.isTrue(exists)
                        ? Mono.error(SafeboxAlreadyExistsException::new)
                        : safeboxRepository.save(generateEncodedSafebox(safeboxName, safeboxPassword)));
    }

    private Safebox generateEncodedSafebox(String safeboxName, String safeboxPassword) {
        return Safebox.builder()
                .name(safeboxName)
                .password(passwordManager.encode(safeboxPassword))
                .build();
    }

    @Override
    public Mono<Safebox> getSafebox(Long id) {
        return safeboxRepository.findById(id);
    }

    @Override
    public Mono<Safebox> getSafebox(String name) {
        return safeboxRepository.findByNameIgnoreCase(name);
    }
}
