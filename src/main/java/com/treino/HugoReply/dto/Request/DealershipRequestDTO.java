package com.treino.HugoReply.dto.Request;

import com.treino.HugoReply.entities.City;
import com.treino.HugoReply.entities.Dealership;
import com.treino.HugoReply.entities.FederativeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealershipRequestDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private City cidade;

    public DealershipRequestDTO(Dealership d) {
        this.id = d.getId();
        this.nome = d.getNome();
        this.cnpj = d.getCnpj();
        this.telefone = d.getTelefone();
        this.email = d.getEmail();
        this.cidade = d.getCidade();
    }

    public Dealership build() {
        Dealership concessionaria = new Dealership();
        concessionaria.setId(this.id);
        concessionaria.setNome(this.nome);
        concessionaria.setCnpj(this.cnpj);
        concessionaria.setTelefone(this.telefone);
        concessionaria.setEmail(this.email);
        concessionaria.setCidade(this.cidade);
        return concessionaria;
    }
}
