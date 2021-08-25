package com.treino.HugoReply.dto.Response;

import com.treino.HugoReply.entities.City;
import com.treino.HugoReply.entities.Dealership;
import com.treino.HugoReply.entities.FederativeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealershipResponseDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private City cidade;

    public DealershipResponseDTO(Dealership entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cnpj = entity.getCnpj();
        this.telefone = entity.getTelefone();
        this.email = entity.getEmail();
        this.cidade = entity.getCidade();
    }
}
