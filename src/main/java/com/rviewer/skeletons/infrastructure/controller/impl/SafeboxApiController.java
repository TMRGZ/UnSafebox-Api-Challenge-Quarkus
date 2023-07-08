package com.rviewer.skeletons.infrastructure.controller.impl;

import com.rviewer.skeletons.application.model.CreatedSafeboxResponseDto;
import com.rviewer.skeletons.application.model.SafeboxItemDto;
import com.rviewer.skeletons.application.model.SafeboxRequestDto;
import com.rviewer.skeletons.infrastructure.controller.SafeboxApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SafeboxApiController implements SafeboxApi {

    @Override
    public Mono<ResponseEntity<CreatedSafeboxResponseDto>> createSafebox(Mono<SafeboxRequestDto> safeboxRequestDto, ServerWebExchange exchange) {
        return SafeboxApi.super.createSafebox(safeboxRequestDto, exchange);
    }

    @Override
    public Mono<ResponseEntity<Flux<SafeboxItemDto>>> getSafeboxItems(String id, ServerWebExchange exchange) {
        return SafeboxApi.super.getSafeboxItems(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<Void>> saveSafeboxItems(String id, Flux<SafeboxItemDto> safeboxItemDto, ServerWebExchange exchange) {
        return SafeboxApi.super.saveSafeboxItems(id, safeboxItemDto, exchange);
    }
}
