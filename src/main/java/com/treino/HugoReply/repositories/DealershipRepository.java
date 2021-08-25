package com.treino.HugoReply.repositories;

import com.treino.HugoReply.dto.Response.DealershipResponseDTO;
import com.treino.HugoReply.entities.Dealership;
import com.treino.HugoReply.entities.FederativeUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DealershipRepository extends JpaRepository<Dealership, Long> {
    @Query ("SELECT c\n" +
            "FROM Dealership c\n" +
            "WHERE c.nome = :name\n")
    Dealership getByName(@Param(value = "name") String name);

    @Query("SELECT c\n" +
            "FROM Dealership c\n" +
            "WHERE c.cnpj = :cnpj\n")
    Dealership getByCNPJ(@Param(value = "cnpj") String cnpj);

    @Query ("SELECT c\n" +
            "FROM Dealership c\n" +
            "WHERE c.cidade.nome = :city\n")
    List<Dealership> findAllByCity(@Param(value = "city") String city);

    @Query ("SELECT c\n" +
            "FROM Dealership c\n" +
            "WHERE c.cidade.uf = :fu\n")
    List<Dealership> findAllByFU(@Param(value = "fu") FederativeUnit fu);
}
