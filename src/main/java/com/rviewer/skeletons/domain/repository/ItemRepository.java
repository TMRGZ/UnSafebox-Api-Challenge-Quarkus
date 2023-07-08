package com.rviewer.skeletons.domain.repository;

import com.rviewer.skeletons.domain.model.Item;
import reactor.core.publisher.Flux;

public interface ItemRepository {

    Flux<Item> findBySafeboxId(Long safeboxId);
}
