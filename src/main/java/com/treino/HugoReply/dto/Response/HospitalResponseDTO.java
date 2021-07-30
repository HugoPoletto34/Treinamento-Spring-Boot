package com.treino.HugoReply.dto.Response;

import com.treino.HugoReply.dto.Request.ProfissionalRequestDTO;
import com.treino.HugoReply.entities.Endereco;
import com.treino.HugoReply.entities.Hospital;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalResponseDTO {
    private Long codigo;
    private String nome;
    @Embedded
    private Endereco endereco;
    private List<ProfissionalRequestDTO> profissionais;

    public HospitalResponseDTO(Hospital entity) {
        this.codigo = entity.getCodigo();
        this.nome = entity.getNome();
        this.endereco = entity.getEndereco();
        this.profissionais = entity.getProfissionais().stream().map(x -> new ProfissionalRequestDTO(x)).collect(Collectors.toList());
    }
}
