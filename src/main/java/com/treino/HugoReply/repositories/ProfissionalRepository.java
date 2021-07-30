package com.treino.HugoReply.repositories;

import com.treino.HugoReply.entities.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

}
