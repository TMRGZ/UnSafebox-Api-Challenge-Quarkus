package com.rviewer.skeletons.domain.repository;

import com.rviewer.skeletons.domain.model.Item;
import com.rviewer.skeletons.domain.model.Safebox;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public interface ItemRepository {

    Multi<Item> findBySafebox(Safebox safebox);

    Uni<Item> save(Item item);
}
