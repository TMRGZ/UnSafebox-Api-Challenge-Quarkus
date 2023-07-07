package com.rviewer.skeletons.domain.repository;

import com.rviewer.skeletons.domain.model.Safebox;

import java.util.List;
import java.util.Optional;

public interface SafeboxRepository {

    Optional<Safebox> findById(String safeboxId);

    List<Safebox> findByNameIgnoreCase(String name);

    Safebox save(Safebox safebox);
}
