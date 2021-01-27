package com.test.estudos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
public class User implements AutoCloseable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "O campo nome não pode ser vazio")
    // @Column(name = "NOME")
    private String name;

    //@NotEmpty(message = "O campo idade é de preenchimento obrigatório")
    //@Column(name = "IDADE")
    private int idade;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    //@Column(name = "EMAIL")
    private String email;

    @Override
    public void close() throws Exception {

    }
}
