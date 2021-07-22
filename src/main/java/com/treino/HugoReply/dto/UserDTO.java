package com.treino.HugoReply.dto;

import com.treino.HugoReply.entities.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long codigo;
    private String nome;
    private int idade;

    public UserDTO() {

    }

    public UserDTO(Long codigo, String nome, int idade) {
        this.codigo = codigo;
        this.nome = nome;
        this.idade = idade;
    }

    public UserDTO(User entity) {
        this.codigo = entity.getCodigo();
        this.nome = entity.getNome();
        this.idade = entity.getIdade();
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
