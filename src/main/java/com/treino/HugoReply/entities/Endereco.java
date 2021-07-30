package com.treino.HugoReply.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class Endereco {
    private String logadouro;
    private int numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
}
