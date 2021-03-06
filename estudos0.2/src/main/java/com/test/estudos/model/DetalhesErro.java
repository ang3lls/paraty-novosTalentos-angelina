package com.test.estudos.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
public class DetalhesErro {

    private String titulo;

    private Long status;

    private Long timestamp;

    public DetalhesErro(String titulo, Long status, Long timestemp){
        this.titulo = titulo;
        this.status = status;
        this.timestamp = timestemp;
    }
}
