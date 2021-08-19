package com.treino.HugoReply.repositories;

import com.treino.HugoReply.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    @Query("SELECT obj FROM Hospital obj")
    List<Hospital> findAllHospital();

    @Query ("SELECT h\n" +
            "FROM Hospital h\n" +
            "LEFT JOIN h.profissionais p " +
            "WHERE p.matricula = :matricula")
    List<Hospital> findAllHospitalsWhoPofissionalWorks(@Param(value = "matricula") Long matricula);
    
}
