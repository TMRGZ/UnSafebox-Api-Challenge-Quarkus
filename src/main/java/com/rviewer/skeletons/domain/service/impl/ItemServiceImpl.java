package com.rviewer.skeletons.domain.service.impl;

import com.rviewer.skeletons.domain.exception.SafeboxDoesNotExistException;
import com.rviewer.skeletons.domain.model.Item;
import com.rviewer.skeletons.domain.repository.ItemRepository;
import com.rviewer.skeletons.domain.service.ItemService;
import com.rviewer.skeletons.domain.service.SafeboxService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final SafeboxService safeboxService;

    @Override
    public Multi<Item> getItems(Long safeboxId) {
        log.info("Getting items from safebox {}", safeboxId);
        return safeboxService.getSafebox(safeboxId)
                .onItem().ifNull().failWith(() -> {
                    log.error("Safebox {} does not exist, cancelling...", safeboxId);
                    return new SafeboxDoesNotExistException();
                })
                .onItem().transformToMulti(itemRepository::findBySafebox);
    }

    @Override
    public Uni<Item> save(Long safeboxId, Item item) {
        log.info("Storing item {} into safebox {}", item, safeboxId);
        return safeboxService.getSafebox(safeboxId)
                .onItem().ifNull().failWith(() -> {
                    log.error("Safebox with id={} does not exist, cancelling...", safeboxId);
                    return new SafeboxDoesNotExistException();
                })
                .map(safebox -> item.toBuilder().safeboxId(safebox.getId()).build())
                .flatMap(itemRepository::save);
    }
}
