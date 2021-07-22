package com.treino.HugoReply.controllers;

import com.treino.HugoReply.dto.Request.UserRequestDTO;
import com.treino.HugoReply.dto.Response.UserResponseDTO;
import com.treino.HugoReply.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/u")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<UserResponseDTO>> listUsers(){
        List<UserResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/mostrar/{id}")
    public ResponseEntity<UserResponseDTO> showUser(@PathVariable Long id) {
        UserResponseDTO user = service.findAllById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping (value = "/cadastrar")
    public ResponseEntity<UserRequestDTO> insert (@RequestBody UserRequestDTO dto) {
        dto = service.insert(dto);
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getCodigo()).toUri();
        return ResponseEntity.status(201).body(dto);
    }

}
