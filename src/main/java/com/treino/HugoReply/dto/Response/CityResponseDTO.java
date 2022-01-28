package com.treino.HugoReply.dto.Response;

import com.treino.HugoReply.entities.City;
import com.treino.HugoReply.entities.FederativeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    public static CityResponseDTO build(City cidade) {
        CityResponseDTO resp = new CityResponseDTO();
        resp.setId(cidade.getId());
        resp.setNome(cidade.getNome());
        resp.setUf(cidade.getUf());

        return resp;
    }

    public static List<CityResponseDTO> converterListToDTOList(List<City> list) {
        List<CityResponseDTO> response = new ArrayList<>();

        for (City d : list) {
            response.add(build(d));
        }
        return response;
    }
}
