package com.treino.HugoReply.dto.Response;

import com.treino.HugoReply.entities.City;
import com.treino.HugoReply.entities.Dealership;
import com.treino.HugoReply.entities.FederativeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private Double valorVendas;
    private Double porcentagemValorVendas;

    public DealershipResponseDTO(Dealership entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cnpj = entity.getCnpj();
        this.telefone = entity.getTelefone();
        this.email = entity.getEmail();
        this.cidade = entity.getCidade();
        this.valorVendas = entity.getValorVendas();
        this.porcentagemValorVendas = entity.getPorcentagemValorVendas();
    }

    public static DealershipResponseDTO build(Dealership concessionaria) {
        DealershipResponseDTO resp = new DealershipResponseDTO();
        resp.setId(concessionaria.getId());
        resp.setNome(concessionaria.getNome());
        resp.setCnpj(concessionaria.getCnpj());
        resp.setTelefone(concessionaria.getTelefone());
        resp.setEmail(concessionaria.getEmail());
        resp.setCidade(concessionaria.getCidade());
        resp.setValorVendas(concessionaria.getValorVendas());
        resp.setPorcentagemValorVendas(concessionaria.getPorcentagemValorVendas());
        return resp;
    }

    public static List<DealershipResponseDTO> converterListToDTOList(List<Dealership> list) {
        List<DealershipResponseDTO> response = new ArrayList<>();

        for (Dealership d : list) {
            response.add(build(d));
        }
        return response;
    }
}
