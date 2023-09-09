package com.rviewer.skeletons.infrastructure.handler;

import com.rviewer.skeletons.domain.exception.SafeboxAlreadyExistsException;
import com.rviewer.skeletons.domain.exception.SafeboxDoesNotExistException;
import com.rviewer.skeletons.domain.exception.UnsafeboxException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RestExceptionHandler implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException exception) {
        return switch (exception) {
            case SafeboxDoesNotExistException safeboxDoesNotExistException -> toResponse(safeboxDoesNotExistException);
            case SafeboxAlreadyExistsException safeboxAlreadyExistsException ->
                    toResponse(safeboxAlreadyExistsException);
            case UnsafeboxException unsafeboxException -> toResponse(unsafeboxException);
            default -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        };
    }

    private Response toResponse(SafeboxDoesNotExistException safeboxDoesNotExistException) {
        return Response.status(Response.Status.NOT_FOUND)
                .build();
    }

    private Response toResponse(SafeboxAlreadyExistsException safeboxAlreadyExistsException) {
        return Response.status(Response.Status.CONFLICT)
                .build();
    }

    private Response toResponse(UnsafeboxException unsafeboxException) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .build();
    }
}
