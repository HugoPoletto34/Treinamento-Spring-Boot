package com.treino.HugoReply.dto.Response;

import com.treino.HugoReply.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long codigo;
    private String nome;
    private int idade;

    public UserResponseDTO(User entity) {
        this.codigo = entity.getCodigo();
        this.nome = entity.getNome();
        this.idade = entity.getIdade();
    }
}
