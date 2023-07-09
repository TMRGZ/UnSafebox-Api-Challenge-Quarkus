package com.rviewer.skeletons.domain.service.impl;

import com.rviewer.skeletons.domain.exception.SafeboxDoesNotExistException;
import com.rviewer.skeletons.domain.model.Item;
import com.rviewer.skeletons.domain.repository.ItemRepository;
import com.rviewer.skeletons.domain.service.ItemService;
import com.rviewer.skeletons.domain.service.SafeboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final SafeboxService safeboxService;

    @Override
    public Flux<Item> getItems(Long safeboxId) {
        log.info("Getting items from safebox {}", safeboxId);
        return safeboxService.getSafebox(safeboxId).hasElement().flatMapMany(exists -> BooleanUtils.isTrue(exists)
                ? itemRepository.findBySafeboxId(safeboxId)
                : Mono.error(() -> {
                    log.error("Safebox {} does not exist, cancelling...", safeboxId);
                    return new SafeboxDoesNotExistException();
                }
        ));
    }

    @Override
    public Mono<Item> save(Long safeboxId, Item item) {
        log.info("Storing item {} into safebox {}", item, safeboxId);
        return safeboxService.getSafebox(safeboxId)
                .map(safebox -> item.toBuilder().safeboxId(safebox.getId()).build())
                .flatMap(itemRepository::save)
                .switchIfEmpty(Mono.error(() -> {
                    log.error("Safebox with id={} does not exist, cancelling...", safeboxId);
                    return new SafeboxDoesNotExistException();
                }));
    }
}
