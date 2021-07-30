package com.treino.HugoReply.controllers;

import com.treino.HugoReply.dto.Request.HospitalRequestDTO;
import com.treino.HugoReply.dto.Response.HospitalResponseDTO;
import com.treino.HugoReply.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/h")
public class HospitalController {
    @Autowired
    private HospitalService service;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<HospitalResponseDTO>> listHospitais(){
        List<HospitalResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/mostrar/{id}")
    public ResponseEntity<HospitalResponseDTO> showHospitais(@PathVariable Long id) {
        HospitalResponseDTO user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping (value = "/cadastrar")
    public ResponseEntity<HospitalRequestDTO> insert (@RequestBody HospitalRequestDTO dto) {
        dto = service.insert(dto.build());
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getCodigo()).toUri();
        return ResponseEntity.status(201).body(dto);
    }

}
