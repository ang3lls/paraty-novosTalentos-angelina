package com.test.estudos.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
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
