package com.rviewer.skeletons.infrastructure.controller.impl;

import com.rviewer.skeletons.application.model.CreatedSafeboxResponseDto;
import com.rviewer.skeletons.application.model.SafeboxItemDto;
import com.rviewer.skeletons.application.model.SafeboxRequestDto;
import com.rviewer.skeletons.application.service.ItemApplicationService;
import com.rviewer.skeletons.application.service.SafeboxApplicationService;
import com.rviewer.skeletons.infrastructure.controller.SafeboxApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SafeboxApiController implements SafeboxApi {

    @Autowired
    private SafeboxApplicationService safeboxApplicationService;

    @Autowired
    private ItemApplicationService itemApplicationService;

    @Override
    public Mono<ResponseEntity<CreatedSafeboxResponseDto>> createSafebox(Mono<SafeboxRequestDto> safeboxRequestDto, ServerWebExchange exchange) {
        return safeboxApplicationService.createSafebox(safeboxRequestDto);
    }

    @Override
    public Mono<ResponseEntity<Flux<SafeboxItemDto>>> getSafeboxItems(Long id, ServerWebExchange exchange) {
        return itemApplicationService.getSafeboxItems(id);
    }

    @Override
    public Mono<ResponseEntity<Void>> saveSafeboxItems(Long id, Flux<SafeboxItemDto> safeboxItemDto, ServerWebExchange exchange) {
        return itemApplicationService.saveSafeboxItems(id, safeboxItemDto);
    }
}
