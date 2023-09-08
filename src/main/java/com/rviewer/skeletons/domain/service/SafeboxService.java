package com.rviewer.skeletons.domain.service;

import com.rviewer.skeletons.domain.model.Safebox;
import io.smallrye.mutiny.Uni;
import reactor.core.publisher.Mono;

public interface SafeboxService {

    Uni<Safebox> createSafebox(String safeboxName, String safeboxPassword);

    Uni<Safebox> getSafebox(Long id);

    Uni<Safebox> getSafebox(String name);

}
