package com.novostalentos.demo.model;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

// O @Getter e @Setter já existem dentro do @Data então precisa anotar a classe com eles!
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

   // @Column(name = "NOME")
    private String name;

    //@Column(name = "IDADE")
    private int idade;

    //@Column(name = "EMAIL")
    private String email;

}
