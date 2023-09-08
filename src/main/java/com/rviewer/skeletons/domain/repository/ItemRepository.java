package com.rviewer.skeletons.domain.repository;

import com.rviewer.skeletons.domain.model.Item;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public interface ItemRepository {

    Multi<Item> findBySafeboxId(Long safeboxId);

    Uni<Item> save(Item item);
}
