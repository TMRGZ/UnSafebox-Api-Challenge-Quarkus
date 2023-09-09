package com.rviewer.skeletons.infrastructure.persistence.repository.impl;

import com.rviewer.skeletons.domain.model.Item;
import com.rviewer.skeletons.domain.model.Safebox;
import com.rviewer.skeletons.domain.repository.ItemRepository;
import com.rviewer.skeletons.infrastructure.mapper.ItemMapper;
import com.rviewer.skeletons.infrastructure.mapper.SafeboxMapper;
import com.rviewer.skeletons.infrastructure.persistence.repository.ReactiveItemRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final ReactiveItemRepository reactiveItemRepository;

    private final SafeboxMapper safeboxMapper;

    private final ItemMapper itemMapper;

    @Override
    public Multi<Item> findBySafebox(Safebox safebox) {
        return reactiveItemRepository.findBySafeboxId(safeboxMapper.map(safebox))
                .onItem().transformToMulti(Multi.createFrom()::iterable)
                .map(itemMapper::map);
    }

    @Override
    public Uni<Item> save(Item item) {
        return reactiveItemRepository.persist(itemMapper.map(item))
                .map(itemMapper::map);
    }
}
