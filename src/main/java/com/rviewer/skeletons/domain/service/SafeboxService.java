package com.rviewer.skeletons.domain.service;

import com.rviewer.skeletons.domain.model.Item;
import com.rviewer.skeletons.domain.model.Safebox;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SafeboxService {

    Mono<Long> createSafebox(String safeboxName, String safeboxPassword);

    Mono<Safebox> openSafebox(String name, String password);

    void saveSafeboxItems(Long safeboxId, Flux<Item> itemsToSave);

}
