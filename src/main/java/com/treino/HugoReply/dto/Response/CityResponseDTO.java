package com.treino.HugoReply.dto.Response;

import com.treino.HugoReply.entities.City;
import com.treino.HugoReply.entities.FederativeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityResponseDTO {
    private Long id;
    private String nome;
    private FederativeUnit uf;

    public CityResponseDTO(City entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.uf = entity.getUf();
    }
}
