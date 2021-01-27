package com.test.estudos.services.exceptions;


public class UserNaoEncontradoException extends RuntimeException{


        private static final long serialVersionUID = 1869300553614629710L;

        public UserNaoEncontradoException(String mensagem){
            super(mensagem);
        }

        public UserNaoEncontradoException (String mensagem, Throwable causa){
            super(mensagem, causa);
        }
}
