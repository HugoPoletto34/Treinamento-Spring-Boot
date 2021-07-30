package com.treino.HugoReply.dto.Response;

import com.treino.HugoReply.entities.Profissional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalResponseDTO {
    private Long matricula;
    private String nome;
    private int idade;
    private double salario;
    private Date dataNascimento;
    private String tipoProfissional;
    private List<HospitalResponseDTO> hospitais;

    public ProfissionalResponseDTO(Profissional p) {
        this.matricula = p.getMatricula();
        this.nome = p.getNome();
        this.idade = p.getIdade();
        this.salario= p.getSalario();
        this.dataNascimento = p.getDataNascimento();
        this.tipoProfissional = p.getTipoProfissional();
        this.hospitais = p.getHospitais().stream().map(x -> new HospitalResponseDTO(x)).collect(Collectors.toList());
    }

    public ProfissionalResponseDTO build() {
        ProfissionalResponseDTO p = new ProfissionalResponseDTO();
        p.setNome(this.nome);
        p.setMatricula(this.matricula);
        p.setIdade(this.idade);
        p.setSalario(this.salario);
        p.setDataNascimento(this.dataNascimento);
        p.setTipoProfissional(this.tipoProfissional);
        p.setHospitais(this.hospitais);
        return p;
    }
}
