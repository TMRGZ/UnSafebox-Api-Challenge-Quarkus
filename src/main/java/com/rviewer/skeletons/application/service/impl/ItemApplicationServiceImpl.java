package com.rviewer.skeletons.application.service.impl;

import com.rviewer.skeletons.application.mapper.ItemDtoMapper;
import com.rviewer.skeletons.application.model.SafeboxItemDto;
import com.rviewer.skeletons.application.service.ItemApplicationService;
import com.rviewer.skeletons.domain.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemApplicationServiceImpl implements ItemApplicationService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDtoMapper itemDtoMapper;

    @Override
    public Mono<ResponseEntity<Flux<SafeboxItemDto>>> getSafeboxItems(Long id) {
        return Mono.just(
                ResponseEntity.ok(
                        itemService.getItems(id).map(itemDtoMapper::map)));
    }

    @Override
    public Mono<ResponseEntity<Void>> saveSafeboxItems(Long id, Flux<SafeboxItemDto> safeboxItemDto) {
        return safeboxItemDto.map(itemDtoMapper::map)
                .flatMap(item -> itemService.save(id, item))
                .then(Mono.just(ResponseEntity.status(HttpStatus.CREATED).build()));
    }
}
