package com.treino.HugoReply.dto.Request;

import com.treino.HugoReply.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String nome;
    private int idade;

    public UserRequestDTO(User u) {
        this.nome = u.getNome();
        this.idade = u.getIdade();
    }

    public User build() {
        User user = new User();
        user.setNome(this.nome);
        user.setIdade(this.idade);
        return user;
    }
}
