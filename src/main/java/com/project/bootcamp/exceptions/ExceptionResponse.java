package com.project.bootcamp.exceptions;

//recebe a mensagem de resposta
public class ExceptionResponse {

    private String message;

    public ExceptionResponse(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
