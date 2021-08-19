package com.treino.HugoReply.dto.Response;

import com.treino.HugoReply.entities.Profissional;
import com.treino.HugoReply.entities.TipoProfissional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoResponseDTO {
    private Long codigo;
    private String nome;

    public TipoResponseDTO(TipoProfissional p) {
        this.codigo = p.getCodigo();
        this.nome = p.getNome();
    }
}
