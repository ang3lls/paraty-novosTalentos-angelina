package com.test.estudos.handler;

import com.test.estudos.model.DetalhesErro;
import com.test.estudos.services.exceptions.UserNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(UserNaoEncontradoException.class)
    public ResponseEntity<DetalhesErro> handlerUserNaoEncontradoException
            (UserNaoEncontradoException e, HttpServletRequest request){

        DetalhesErro erro = new DetalhesErro("O usuário não pôde ser encontrado",
                404l,System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
