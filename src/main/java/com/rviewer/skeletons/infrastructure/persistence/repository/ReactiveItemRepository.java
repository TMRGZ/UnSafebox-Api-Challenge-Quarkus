package com.rviewer.skeletons.infrastructure.persistence.repository;

import com.rviewer.skeletons.infrastructure.persistence.dao.ItemDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReactiveItemRepository extends ReactiveCrudRepository<ItemDao, Long> {

    Flux<ItemDao> findBySafeboxId(Long safeboxId);

}
