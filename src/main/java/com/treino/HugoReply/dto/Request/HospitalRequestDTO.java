package com.treino.HugoReply.dto.Request;

import com.treino.HugoReply.entities.Endereco;
import com.treino.HugoReply.entities.Hospital;
import com.treino.HugoReply.entities.Profissional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalRequestDTO {
    private Long codigo;
    private String nome;
    @Embedded
    private Endereco endereco;
    private List<Profissional> profissionais;

    public HospitalRequestDTO(Hospital u) {
        this.codigo = u.getCodigo();
        this.nome = u.getNome();
        this.endereco = u.getEndereco();
        this.profissionais = u.getProfissionais();
    }

    public Hospital build() {
        Hospital user = new Hospital();
        user.setCodigo(this.codigo);
        user.setNome(this.nome);
        user.setEndereco(this.endereco);
        user.setProfissionais(this.profissionais);
        return user;
    }
}
