package com.rviewer.skeletons.infrastructure.persistence.repository;

import com.rviewer.skeletons.infrastructure.persistence.dao.SafeboxDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveSafeboxRepository extends ReactiveCrudRepository<SafeboxDao, Long> {

    Mono<SafeboxDao> findByNameIgnoreCase(String name);

}
