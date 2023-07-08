package com.rviewer.skeletons.application.service;

import com.rviewer.skeletons.application.model.SafeboxItemDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemApplicationService {
    Mono<ResponseEntity<Flux<SafeboxItemDto>>> getSafeboxItems(Long id);

    Mono<ResponseEntity<Void>> saveSafeboxItems(Long id, Flux<SafeboxItemDto> safeboxItemDto);
}
