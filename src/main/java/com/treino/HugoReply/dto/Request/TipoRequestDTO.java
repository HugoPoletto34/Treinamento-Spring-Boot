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
public class TipoRequestDTO {
    private Long codigo;
    private String nome;

    public TipoRequestDTO(TipoProfissional p) {
        this.codigo = p.getCodigo();
        this.nome = p.getNome();
    }

    public TipoProfissional build() {
        TipoProfissional tipo = new TipoProfissional();
        tipo.setCodigo(this.codigo);
        tipo.setNome(this.nome);
        return tipo;
    }
}
