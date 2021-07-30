package com.treino.HugoReply.controllers;

import com.treino.HugoReply.dto.Request.ProfissionalRequestDTO;
import com.treino.HugoReply.dto.Response.ProfissionalResponseDTO;
import com.treino.HugoReply.entities.Hospital;
import com.treino.HugoReply.entities.Profissional;
import com.treino.HugoReply.services.HospitalService;
import com.treino.HugoReply.services.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/p")
public class ProfissionalController {
    @Autowired
    private ProfissionalService pService;
    @Autowired
    private HospitalService hService;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<ProfissionalResponseDTO>> listHospitais(){
        List<ProfissionalResponseDTO> list = pService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/mostrar/{id}")
    public ResponseEntity<ProfissionalResponseDTO> showHospitais(@PathVariable Long id) {
        ProfissionalResponseDTO user = pService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<ProfissionalRequestDTO> insert (@RequestBody ProfissionalRequestDTO dto) {
        dto = pService.insert(dto);
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getCodigo()).toUri();
        return ResponseEntity.status(201).body(dto);
    }

    @PostMapping(value = "/associarProfissionalHospital/{matricula}/{codigo}")
    public ResponseEntity<ProfissionalResponseDTO> associar(@PathVariable Long matricula, @PathVariable Long codigo) {
        Profissional prof = pService.getProfissionalById(matricula);
        Hospital hospital = hService.getHospitalById(codigo);

        prof.getHospitais().add(hospital);
        pService.insert(new ProfissionalRequestDTO(prof));

        return ResponseEntity.ok().body(new ProfissionalResponseDTO(prof));
    }

}
