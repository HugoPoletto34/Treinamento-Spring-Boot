package com.treino.HugoReply.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_cidade")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cidade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private UnidadeFederativa uf;

}
