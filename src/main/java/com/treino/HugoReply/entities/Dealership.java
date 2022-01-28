package com.treino.HugoReply.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_concessionaria")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dealership implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private Double valorVendas;
    private Double porcentagemValorVendas;
    @OneToOne
    private City cidade;

}
