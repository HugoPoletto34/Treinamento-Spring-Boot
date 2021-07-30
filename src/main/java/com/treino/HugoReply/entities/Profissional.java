package com.treino.HugoReply.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "tb_profissional")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Profissional implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;
    private String nome;
    private int idade;
    private double salario;
    private Date dataNascimento;
    private String tipoProfissional;
    @ManyToMany
    @JoinTable (name = "tb_hospital_profissional",
            joinColumns = @JoinColumn (name = "profissional_matricula"),
            inverseJoinColumns = @JoinColumn (name = "hospital_codigo")
    )
    private List<Hospital> hospitais;
}
