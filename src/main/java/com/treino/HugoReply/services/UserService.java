package com.treino.HugoReply.services;

import com.treino.HugoReply.dto.Request.UserRequestDTO;
import com.treino.HugoReply.dto.Response.UserResponseDTO;
import com.treino.HugoReply.entities.User;
import com.treino.HugoReply.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Transactional
    public List<UserResponseDTO> findAll () {
        List<User> list = repository.findAllUsers();
        return list.stream().map(x -> new UserResponseDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public UserResponseDTO findAllById (Long id) {
        User user = repository.getById(id);
        return new UserResponseDTO(user);
    }

    @Transactional
    public UserRequestDTO insert (UserRequestDTO dto) {
        User u = repository.save(dto.build());
        return new UserRequestDTO(u);
    }
}
