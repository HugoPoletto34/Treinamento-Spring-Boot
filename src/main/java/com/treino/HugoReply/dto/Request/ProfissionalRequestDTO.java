package com.treino.HugoReply.dto.Request;

import com.treino.HugoReply.entities.Profissional;
import com.treino.HugoReply.entities.TipoProfissional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalRequestDTO {
    private Long matricula;
    private String nome;
    private int idade;
    private double salario;
    private Date dataNascimento;
    private TipoProfissional tipoProfissional;

    public ProfissionalRequestDTO(Profissional p) {
        this.matricula = p.getMatricula();
        this.nome = p.getNome();
        this.idade = p.getIdade();
        this.salario= p.getSalario();
        this.dataNascimento = p.getDataNascimento();
        this.tipoProfissional = p.getTipoProfissional();
    }

    public Profissional build() {
        Profissional prof = new Profissional();
        prof.setMatricula(this.matricula);
        prof.setNome(this.nome);
        prof.setIdade(this.idade);
        prof.setSalario(this.salario);
        prof.setTipoProfissional(this.tipoProfissional);
        prof.setDataNascimento(this.dataNascimento);
        return prof;
    }
}
