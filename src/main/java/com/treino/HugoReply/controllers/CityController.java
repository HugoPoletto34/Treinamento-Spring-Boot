package com.treino.HugoReply.controllers;

import com.treino.HugoReply.dto.Request.CityRequestDTO;
import com.treino.HugoReply.dto.Response.CityResponseDTO;
import com.treino.HugoReply.dto.Response.DealershipResponseDTO;
import com.treino.HugoReply.entities.City;
import com.treino.HugoReply.services.CityService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/cidade")
public class CityController {
    @Autowired
    private CityService service;

    @GetMapping(value = "/listar")
    public ResponseEntity listCities(){
        List<CityResponseDTO> list = service.findAll();
        if (list.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhuma cidade foi encontrada.");
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/mostrar/id/{id}")
    public ResponseEntity showCityById(@PathVariable Long id) {
        ResponseEntity respCidade = service.getById(id);
        return respCidade;
    }

    @GetMapping(value = "/mostrar/nome/{name}")
    public ResponseEntity showCityByName(@PathVariable String name) {
        ResponseEntity respCidade = service.getByName(name);
        return respCidade;
    }

    @PostMapping (value = "/cadastrar")
    public ResponseEntity insert (@RequestBody CityRequestDTO dto) {
        ResponseEntity re = service.insert(dto);
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getCodigo()).toUri();
        return re;
    }

}
