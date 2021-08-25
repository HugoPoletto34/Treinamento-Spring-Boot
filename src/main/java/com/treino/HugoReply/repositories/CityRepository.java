package com.treino.HugoReply.repositories;

import com.treino.HugoReply.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CityRepository extends JpaRepository<City, Long> {
    @Query ("SELECT c\n" +
            "FROM City c\n" +
            "WHERE c.nome = :name\n")
    City getByName(@Param(value = "name") String name);
}
