package com.rviewer.skeletons.infrastructure.persistence.repository;

import com.rviewer.skeletons.infrastructure.persistence.dao.ItemDao;
import com.rviewer.skeletons.infrastructure.persistence.dao.SafeboxDao;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ReactiveItemRepository implements PanacheRepository<ItemDao> {

    public Uni<List<ItemDao>> findBySafeboxId(SafeboxDao safebox) {
        return list(ItemDao.Fields.safebox, safebox);
    }
}
