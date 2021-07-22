package com.treino.HugoReply.repositories;

import com.treino.HugoReply.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT obj FROM User obj")
    List<User> findAllUsers();
}
