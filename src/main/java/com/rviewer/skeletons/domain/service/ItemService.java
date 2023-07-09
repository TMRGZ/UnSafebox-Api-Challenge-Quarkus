package com.rviewer.skeletons.domain.service;

import com.rviewer.skeletons.domain.model.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemService {
    Flux<Item> getItems(Long safeboxId);

    Mono<Item> save(Long safeboxId, Item item);
}
