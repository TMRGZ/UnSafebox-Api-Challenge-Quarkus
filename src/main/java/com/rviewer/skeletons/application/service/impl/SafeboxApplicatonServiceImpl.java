package com.rviewer.skeletons.application.service.impl;

import com.rviewer.skeletons.application.mapper.ItemDtoMapper;
import com.rviewer.skeletons.application.model.CreatedSafeboxResponseDto;
import com.rviewer.skeletons.application.model.SafeboxItemDto;
import com.rviewer.skeletons.application.model.SafeboxRequestDto;
import com.rviewer.skeletons.application.service.SafeboxApplicationService;
import com.rviewer.skeletons.domain.service.SafeboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class SafeboxApplicatonServiceImpl implements SafeboxApplicationService {

    @Autowired
    private SafeboxService safeboxService;

    @Autowired
    private ItemDtoMapper itemDtoMapper;

    @Override
    public Mono<ResponseEntity<CreatedSafeboxResponseDto>> createSafebox(Mono<SafeboxRequestDto> safeboxRequestDto) {
        return safeboxRequestDto.map(request -> safeboxService.createSafebox(request.getName(), request.getPassword()))
                .flatMap(Function.identity())
                .map(CreatedSafeboxResponseDto::new)
                .map(responseBody -> ResponseEntity.status(HttpStatus.CREATED).body(responseBody));
    }

    @Override
    public Mono<ResponseEntity<Void>> saveSafeboxItems(Long id, Flux<SafeboxItemDto> safeboxItemDto) {
        safeboxService.saveSafeboxItems(id, safeboxItemDto.map(itemDtoMapper::map));
        return Mono.just(ResponseEntity.status(HttpStatus.CREATED).build());
    }
}
