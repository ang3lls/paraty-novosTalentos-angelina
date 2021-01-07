package com.novostalentos.demo;

import javax.lang.model.element.Name;
import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NOME")
    private String name;

    @Column(name = "IDADE")
    private int idade;

    @Column(name = "EMAIL")
    private String email;
}
