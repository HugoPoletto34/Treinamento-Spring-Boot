package com.treino.HugoReply.services;

import com.treino.HugoReply.dto.UserDTO;
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
    public List<UserDTO> findAll () {
        List<User> list = repository.findAllUsers();
        return list.stream().map(x -> new UserDTO (x)).collect(Collectors.toList());
    }

    @Transactional
    public UserDTO findAllById (Long id) {
        User user = repository.getById(id);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO insert (UserDTO dto) {
        User user = new User(null, dto.getNome(), dto.getIdade());
        user = repository.save(user);
        return new UserDTO(user);
    }
}
