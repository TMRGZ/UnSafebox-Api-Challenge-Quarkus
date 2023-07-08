package com.rviewer.skeletons.domain.repository;

import com.rviewer.skeletons.domain.model.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemRepository {

    Flux<Item> findBySafeboxId(Long safeboxId);

    Mono<Item> save(Item item);
}
