package com.rviewer.skeletons.domain.repository;

import com.rviewer.skeletons.domain.model.Safebox;
import io.smallrye.mutiny.Uni;

public interface SafeboxRepository {

    Uni<Safebox> findById(Long id);

    Uni<Safebox> findByNameIgnoreCase(String name);

    Uni<Safebox> save(Safebox safebox);
}
