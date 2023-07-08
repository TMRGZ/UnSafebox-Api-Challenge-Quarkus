package com.rviewer.skeletons.infrastructure.persistence.repository.impl;

import com.rviewer.skeletons.domain.model.Safebox;
import com.rviewer.skeletons.domain.repository.SafeboxRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SafeboxRepositoryImpl implements SafeboxRepository {
    @Override
    public Optional<Safebox> findById(String safeboxId) {
        return Optional.empty();
    }

    @Override
    public List<Safebox> findByNameIgnoreCase(String name) {
        return null;
    }

    @Override
    public Safebox save(Safebox safebox) {
        return null;
    }
}
