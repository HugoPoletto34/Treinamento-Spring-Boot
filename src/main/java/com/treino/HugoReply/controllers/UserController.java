package com.treino.HugoReply.controllers;

import com.treino.HugoReply.dto.UserDTO;
import com.treino.HugoReply.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/u")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<UserDTO>> listUsers(){
        List<UserDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/mostrar/{id}")
    public ResponseEntity<UserDTO> showUser(@PathVariable Long id) {
        UserDTO user = service.findAllById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping (value = "/cadastrar")
    public ResponseEntity<UserDTO> insert (@RequestBody UserDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
