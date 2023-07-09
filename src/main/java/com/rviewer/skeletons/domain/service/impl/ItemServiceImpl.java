package com.rviewer.skeletons.domain.service.impl;

import com.rviewer.skeletons.domain.exception.SafeboxDoesNotExistException;
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
        return safeboxService.getSafebox(safeboxId)
                .flatMapMany(safebox -> itemRepository.findBySafeboxId(safebox.getId()))
                .switchIfEmpty(Mono.error(SafeboxDoesNotExistException::new));
    }

    @Override
    public Mono<Item> save(Long safeboxId, Item item) {
        return safeboxService.getSafebox(safeboxId)
                .map(safebox -> item.toBuilder().safeboxId(safebox.getId()).build())
                .flatMap(itemRepository::save)
                .switchIfEmpty(Mono.error(SafeboxDoesNotExistException::new));
    }
}
