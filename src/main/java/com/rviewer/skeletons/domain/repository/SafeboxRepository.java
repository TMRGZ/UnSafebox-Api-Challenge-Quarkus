package com.rviewer.skeletons.domain.repository;

import com.rviewer.skeletons.domain.model.Safebox;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SafeboxRepository {

    Mono<Safebox> findById(Long safeboxId);

    Flux<Safebox> findByNameIgnoreCase(String name);

    Mono<Safebox> save(Safebox safebox);
}
