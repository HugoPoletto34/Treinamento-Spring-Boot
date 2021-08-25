package com.treino.HugoReply.controllers;

import com.treino.HugoReply.dto.Request.CityRequestDTO;
import com.treino.HugoReply.dto.Request.DealershipRequestDTO;
import com.treino.HugoReply.dto.Response.CityResponseDTO;
import com.treino.HugoReply.dto.Response.DealershipResponseDTO;
import com.treino.HugoReply.entities.Dealership;
import com.treino.HugoReply.entities.FederativeUnit;
import com.treino.HugoReply.services.CityService;
import com.treino.HugoReply.services.DealershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/concessionaria")
public class DealershipController {
    @Autowired
    private DealershipService service;
    @Autowired
    private CityService cityService;

    @GetMapping(value = "/listar-todos")
    public ResponseEntity<List<DealershipResponseDTO>> listDealerships(){
        List<DealershipResponseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/listar-todos/cidade/{cidade}")
    public ResponseEntity<List<DealershipResponseDTO>> listDealershipInCity(@PathVariable String cidade){
        CityResponseDTO cidadeDTO = cityService.findAllByName(cidade);
        List<DealershipResponseDTO> listDealerships = service.findAllDealershipsByCity(cidadeDTO);
        return ResponseEntity.ok().body(listDealerships);
    }

    @GetMapping(value = "/listar-todos/uf/{uf}")
    public ResponseEntity<List<DealershipResponseDTO>> listDealershipInFU(@PathVariable String uf){
        FederativeUnit objUF = FederativeUnit.valueOf(uf);
        List<DealershipResponseDTO> listDealerships = service.findAllDealershipsByFU(objUF);
        return ResponseEntity.ok().body(listDealerships);
    }

    @GetMapping(value = "/mostrar/id/{id}")
    public ResponseEntity<DealershipResponseDTO> showDealershipById(@PathVariable Long id) {
        DealershipResponseDTO concessionaria = service.getById(id);
        return ResponseEntity.ok().body(concessionaria);
    }

    @GetMapping(value = "/mostrar/nome/{name}")
    public ResponseEntity<DealershipResponseDTO> showCityByName(@PathVariable String name) {
        DealershipResponseDTO concessionaria = service.getByName(name);
        return ResponseEntity.ok().body(concessionaria);
    }



    @GetMapping(value = "/mostrar/cnpj")
    public ResponseEntity<DealershipResponseDTO> showCityByCNPJ(@RequestBody DealershipRequestDTO cnpj) {
        DealershipResponseDTO concessionaria = service.getByCNPJ(cnpj.getCnpj());
        return ResponseEntity.ok().body(concessionaria);
    }

    @PostMapping (value = "/cadastrar")
    public ResponseEntity<DealershipRequestDTO> insert (@RequestBody DealershipRequestDTO dto) {
        dto = service.insert(dto);
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getCodigo()).toUri();
        return ResponseEntity.status(201).body(dto);
    }

}
