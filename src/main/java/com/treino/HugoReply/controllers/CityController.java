package com.treino.HugoReply.controllers;

import com.treino.HugoReply.dto.Request.CityRequestDTO;
import com.treino.HugoReply.dto.Response.CityResponseDTO;
import com.treino.HugoReply.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/cidade")
public class CityController {
    @Autowired
    private CityService service;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<CityResponseDTO>> listCities(){
        List<CityResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/mostrar/id/{id}")
    public ResponseEntity<CityResponseDTO> showCityById(@PathVariable Long id) {
        CityResponseDTO cidade = service.findAllById(id);
        return ResponseEntity.ok().body(cidade);
    }

    @GetMapping(value = "/mostrar/nome/{name}")
    public ResponseEntity<CityResponseDTO> showCityByName(@PathVariable String name) {
        CityResponseDTO cidade = service.findAllByName(name);
        return ResponseEntity.ok().body(cidade);
    }

    @PostMapping (value = "/cadastrar")
    public ResponseEntity<CityRequestDTO> insert (@RequestBody CityRequestDTO dto) {
        dto = service.insert(dto);
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getCodigo()).toUri();
        return ResponseEntity.status(201).body(dto);
    }

}
