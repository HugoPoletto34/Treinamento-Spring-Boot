package com.treino.HugoReply.dto.Request;

import com.treino.HugoReply.entities.City;
import com.treino.HugoReply.entities.FederativeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityRequestDTO {
    private Long id;
    private String nome;
    private FederativeUnit uf;

    public CityRequestDTO(City c) {
        this.id = c.getId();
        this.nome = c.getNome();
        this.uf = c.getUf();
    }

    public City build() {
        City cidade = new City();
        cidade.setId(this.id);
        cidade.setNome(this.nome);
        cidade.setUf(this.uf);
        return cidade;
    }
}
