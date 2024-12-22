package io.github.dumijdev.restdbserver.infra.handler;

import io.github.dumijdev.restdbserver.application.core.domain.exceptions.InvalidException;
import io.github.dumijdev.restdbserver.application.core.domain.exceptions.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalHandlerExceptionController extends ResponseEntityExceptionHandler {
  @ExceptionHandler({ValidationException.class, InvalidException.class})
  public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
    var errors = ex.exceptions();

    return ResponseEntity.badRequest().body(errors);
  }
}
