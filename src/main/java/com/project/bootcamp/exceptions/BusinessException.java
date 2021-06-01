package com.project.bootcamp.exceptions;

//diz pro java que é uma excecao herdando de runtimeexception
public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}
