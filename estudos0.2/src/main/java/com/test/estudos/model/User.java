package com.test.estudos.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

// O @Data já contempla o @Getter e @Setter dentro dele... então nos nossos projetos vc vai ver muito ele.
@Data
//@Getter
//@Setter
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

    public User(int id, String name, int idade, String email) {
        this.id = id;
        this.name= name;
        this.idade = idade;
        this.email = email;
    }

    @Override
    public void close() throws Exception {
    }

}
