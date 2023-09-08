package com.rviewer.skeletons.domain.service;

import com.rviewer.skeletons.domain.model.Item;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public interface ItemService {
    Multi<Item> getItems(Long safeboxId);

    Uni<Item> save(Long safeboxId, Item item);
}
