package com.rviewer.skeletons.infrastructure.persistence.repository.impl;

import com.rviewer.skeletons.domain.model.Safebox;
import com.rviewer.skeletons.domain.repository.SafeboxRepository;
import com.rviewer.skeletons.infrastructure.mapper.SafeboxMapper;
import com.rviewer.skeletons.infrastructure.persistence.repository.ReactiveSafeboxRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class SafeboxRepositoryImpl implements SafeboxRepository {

    private final ReactiveSafeboxRepository reactiveSafeboxRepository;

    private final SafeboxMapper safeboxMapper;

    @Override
    public Uni<Safebox> findById(Long id) {
        return reactiveSafeboxRepository.findById(id).map(safeboxMapper::map);
    }

    @Override
    public Uni<Safebox> findByNameIgnoreCase(String name) {
        return reactiveSafeboxRepository.findByNameIgnoreCase(name).map(safeboxMapper::map);
    }

    @Override
    public Uni<Safebox> save(Safebox safebox) {
        return reactiveSafeboxRepository.persist(safeboxMapper.map(safebox)).map(safeboxMapper::map);
    }
}
