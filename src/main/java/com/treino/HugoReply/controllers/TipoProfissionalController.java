package com.treino.HugoReply.controllers;

import com.treino.HugoReply.dto.Request.ProfissionalRequestDTO;
import com.treino.HugoReply.dto.Request.TipoRequestDTO;
import com.treino.HugoReply.dto.Response.TipoResponseDTO;
import com.treino.HugoReply.entities.Profissional;
import com.treino.HugoReply.entities.TipoProfissional;
import com.treino.HugoReply.services.ProfissionalService;
import com.treino.HugoReply.services.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/tp")
public class TipoProfissionalController {
    @Autowired
    private ProfissionalService pService;
    @Autowired
    private TipoService tService;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<TipoResponseDTO>> listTipos(){
        List<TipoResponseDTO> list = tService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/mostrar/{id}")
    public ResponseEntity<TipoResponseDTO> showHospitais(@PathVariable Long id) {
        TipoResponseDTO user = tService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<TipoRequestDTO> insert (@RequestBody TipoRequestDTO dto) {
        dto = tService.insert(dto.build());
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getCodigo()).toUri();
        return ResponseEntity.status(201).body(dto);
    }
}
