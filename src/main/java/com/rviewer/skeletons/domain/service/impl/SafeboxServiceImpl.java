package com.rviewer.skeletons.domain.service.impl;

import com.rviewer.skeletons.domain.model.Item;
import com.rviewer.skeletons.domain.model.Safebox;
import com.rviewer.skeletons.domain.repository.SafeboxRepository;
import com.rviewer.skeletons.domain.service.ItemService;
import com.rviewer.skeletons.domain.service.PasswordManager;
import com.rviewer.skeletons.domain.service.SafeboxService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SafeboxServiceImpl implements SafeboxService {

    private final SafeboxRepository safeboxRepository;

    private final PasswordManager passwordManager;

    @Override
    public Mono<Long> createSafebox(String safeboxName, String safeboxPassword) {
        return safeboxRepository.save(generateEncodedSafebox(safeboxName, safeboxPassword))
                .map(Safebox::getId);
    }

    private Safebox generateEncodedSafebox(String safeboxName, String safeboxPassword) {
        return Safebox.builder()
                .name(safeboxName)
                .password(passwordManager.encode(safeboxPassword))
                .build();
    }

    @Override
    public Mono<Safebox> getSafebox(String name) {
        return safeboxRepository.findByNameIgnoreCase(name);
    }
}
