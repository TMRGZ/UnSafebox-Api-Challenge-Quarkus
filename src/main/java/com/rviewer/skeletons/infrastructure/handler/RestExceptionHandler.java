package com.rviewer.skeletons.infrastructure.handler;

import com.rviewer.skeletons.domain.exception.SafeboxAlreadyExistsException;
import com.rviewer.skeletons.domain.exception.SafeboxDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(SafeboxDoesNotExistException.class)
    public ResponseEntity<Void> safeboxNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SafeboxAlreadyExistsException.class)
    public ResponseEntity<Void> safeboxAlreadyExists() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
