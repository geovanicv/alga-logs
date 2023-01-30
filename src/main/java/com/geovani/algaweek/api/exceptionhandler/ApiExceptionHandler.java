package com.geovani.algaweek.api.exceptionhandler;

import com.geovani.algaweek.api.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        List<ExceptionMessage.ExceptionError> exceptions = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();

            exceptions.add(new ExceptionMessage.ExceptionError(nome, mensagem));
        }

        ExceptionMessage exception = new ExceptionMessage();
        exception.setDataHora(LocalDateTime.now());
        exception.setStatus(status.value());
        exception.setTitulo("Um ou mais campos inválidos");
        exception.setExceptions(exceptions);

        return handleExceptionInternal(ex, exception, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ExceptionMessage exception = new ExceptionMessage();
        exception.setDataHora(LocalDateTime.now());
        exception.setStatus(status.value());
        exception.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex, exception, new HttpHeaders(), status, request);

    }
}
