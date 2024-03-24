package br.com.hallucinations.handler;

import br.com.hallucinations.exceptions.ExceptionsResponse;
import br.com.hallucinations.exceptions.UnsupportedMathOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    // Método para tratar as exceções mais genéricas, no java são as ExceptionHandler;
    // No REST são as internal server error, ou Error 500;
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionsResponse> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UnsupportedMathOperationException.class)
    public final ResponseEntity<ExceptionsResponse> handleBadRequestExceptions(Exception ex, WebRequest request){
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionsResponse, HttpStatus.BAD_REQUEST);
    }



}
