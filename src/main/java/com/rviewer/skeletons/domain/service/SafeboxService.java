package com.rviewer.skeletons.domain.service;

import com.rviewer.skeletons.domain.model.Safebox;
import reactor.core.publisher.Mono;

public interface SafeboxService {

    Mono<Long> createSafebox(String safeboxName, String safeboxPassword);

    Mono<Safebox> getSafebox(String name);

}
