package com.rviewer.skeletons.domain.service.impl;

import com.rviewer.skeletons.domain.model.Item;
import com.rviewer.skeletons.domain.repository.ItemRepository;
import com.rviewer.skeletons.domain.service.ItemService;
import com.rviewer.skeletons.domain.service.SafeboxService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final SafeboxService safeboxService;

    @Override
    public Flux<Item> getItems(Long safeboxId) {
        return itemRepository.findBySafeboxId(safeboxId);
    }

    @Override
    public Mono<Item> save(Long safeboxId, Item item) {
        Item itemToSave = item.toBuilder().safeboxId(safeboxId).build();
        return itemRepository.save(itemToSave);
    }
}
