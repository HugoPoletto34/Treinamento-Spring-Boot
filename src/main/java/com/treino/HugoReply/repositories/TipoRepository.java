package com.treino.HugoReply.repositories;

import com.treino.HugoReply.entities.Profissional;
import com.treino.HugoReply.entities.TipoProfissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<TipoProfissional, Long> {

}
