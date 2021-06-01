package com.project.bootcamp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//responsavel por interceptar quando algo de errado acontecer e retonar isso pro frontend
//extends... vai tratar excecoes de response
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    //aconteceu uma excecao do tipo Business entra aqui
    @ExceptionHandler(BusinessException.class)
    //recebe a business exception e retorna o codigo de status da familia 400 junto com a mensagem de erro e
    protected ResponseEntity<ExceptionResponse> handleSecurity(BusinessException e){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ExceptionResponse(e.getMessage()));
    }

    //aconteceu uma excecao do tipo NotFound entra aqui
    @ExceptionHandler(NotFoundException.class)
    //recebe notfound exception e retorna o codigo de status da familia 404 junto com a mensagem de erro e
    protected ResponseEntity<ExceptionResponse> handleSecurity(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(e.getMessage()));
    }

}
