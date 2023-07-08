package com.rviewer.skeletons.infrastructure.persistence.repository.impl;

import com.rviewer.skeletons.domain.model.Item;
import com.rviewer.skeletons.domain.repository.ItemRepository;
import com.rviewer.skeletons.infrastructure.mapper.ItemMapper;
import com.rviewer.skeletons.infrastructure.persistence.repository.ReactiveItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ItemRepositoryImpl implements ItemRepository {

    @Autowired
    private ReactiveItemRepository reactiveItemRepository;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Flux<Item> findBySafeboxId(Long safeboxId) {
        return reactiveItemRepository.findBySafeboxId(safeboxId).map(itemMapper::map);
    }
}
