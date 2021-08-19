package kbbg;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handel 404 Exection
 */
@ControllerAdvice
class KbbgNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(KbbgNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String employeeNotFoundHandler(KbbgNotFoundException ex) {
    return ex.getMessage();
  }
}
