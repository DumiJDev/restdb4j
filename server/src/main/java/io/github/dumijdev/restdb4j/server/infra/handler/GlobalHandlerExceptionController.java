package io.github.dumijdev.restdb4j.server.infra.handler;

import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.InvalidException;
import io.github.dumijdev.restdb4j.server.application.core.domain.exceptions.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class GlobalHandlerExceptionController extends ResponseEntityExceptionHandler {
  @ExceptionHandler({ValidationException.class, InvalidException.class})
  public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
    var errors = ex.exceptions();

    return ResponseEntity.badRequest().body(errors);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
    var responseEntity =  ResponseEntity.internalServerError().headers(headers);

    if (ex instanceof ValidationException ve) {
      return responseEntity.body(ve.exceptions());
    }

    return responseEntity.body(List.of(new ValidationException.ItemException("internal", ex.getMessage())));
  }
}
