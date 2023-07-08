package com.rviewer.skeletons.application.service;

import com.rviewer.skeletons.application.model.CreatedSafeboxResponseDto;
import com.rviewer.skeletons.application.model.SafeboxRequestDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface SafeboxApplicationService {

    Mono<ResponseEntity<CreatedSafeboxResponseDto>> createSafebox(Mono<SafeboxRequestDto> safeboxRequestDto);

}
