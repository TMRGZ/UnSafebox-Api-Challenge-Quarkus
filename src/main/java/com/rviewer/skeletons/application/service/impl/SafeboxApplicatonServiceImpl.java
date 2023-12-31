package com.rviewer.skeletons.application.service.impl;

import com.rviewer.skeletons.application.model.CreatedSafeboxResponseDto;
import com.rviewer.skeletons.application.model.SafeboxRequestDto;
import com.rviewer.skeletons.application.service.SafeboxApplicationService;
import com.rviewer.skeletons.domain.model.Safebox;
import com.rviewer.skeletons.domain.service.SafeboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SafeboxApplicatonServiceImpl implements SafeboxApplicationService {

    @Autowired
    private SafeboxService safeboxService;

    @Override
    public Mono<ResponseEntity<CreatedSafeboxResponseDto>> createSafebox(Mono<SafeboxRequestDto> safeboxRequestDto) {
        return safeboxRequestDto.flatMap(request -> safeboxService.createSafebox(request.getName(), request.getPassword()))
                .map(Safebox::getId)
                .map(CreatedSafeboxResponseDto::new)
                .map(responseBody -> ResponseEntity.status(HttpStatus.CREATED).body(responseBody));
    }
}
