package com.rviewer.skeletons.infrastructure.persistence.repository;

import com.rviewer.skeletons.infrastructure.persistence.dao.SafeboxDao;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReactiveSafeboxRepository implements PanacheRepository<SafeboxDao> {

    public Uni<SafeboxDao> findByNameIgnoreCase(String name) {
        return find(SafeboxDao.Fields.name, name).firstResult();
    }

}
