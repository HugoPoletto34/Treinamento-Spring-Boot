package com.treino.HugoReply.controllers;

import com.treino.HugoReply.Exporter.DealershipExporter;
import com.treino.HugoReply.dto.Request.DealershipRequestDTO;
import com.treino.HugoReply.dto.Response.CityResponseDTO;
import com.treino.HugoReply.dto.Response.DealershipResponseDTO;
import com.treino.HugoReply.entities.Dealership;
import com.treino.HugoReply.entities.FederativeUnit;
import com.treino.HugoReply.services.CityService;
import com.treino.HugoReply.services.DealershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/concessionaria")
public class DealershipController {
    @Autowired
    private DealershipService service;
    @Autowired
    private CityService cityService;


    @GetMapping(value = "/listar-todos")
    public ResponseEntity listDealerships(){
        List<DealershipResponseDTO> list = service.findAll();
        if (list.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhuma concessionária foi encontrada.");
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/listar-todos/cidade/{cidade}")
    public ResponseEntity listDealershipInCity(@PathVariable String cidade) {
        ResponseEntity<CityResponseDTO> cidadeDTO = cityService.getByName(cidade);
        if (cidadeDTO.getStatusCodeValue() != 200)
            return cidadeDTO;
        List<DealershipResponseDTO> listDealerships = service.findAllDealershipsByCity(cidadeDTO.getBody());
        return ResponseEntity.ok().body(listDealerships);
    }

    @GetMapping(value = "/listar-todos/uf/{uf}")
    public ResponseEntity listDealershipInFU(@PathVariable String uf){
        FederativeUnit objUF;
        try {
            objUF = FederativeUnit.valueOf(uf.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Unidade Federativa \""+uf+"\" inválida: Verifique se a UF está correta.");
        }
        List<DealershipResponseDTO> listDealerships = service.findAllDealershipsByFU(objUF);
        return ResponseEntity.ok().body(listDealerships);
    }

    @GetMapping(value = "/mostrar/id/{id}")
    public ResponseEntity<DealershipResponseDTO> showDealershipById(@PathVariable Long id) {
        ResponseEntity concessionaria = service.getById(id);
        return concessionaria;
    }

    @GetMapping(value = "/mostrar/nome/{name}")
    public ResponseEntity<DealershipResponseDTO> showCityByName(@PathVariable String name) {
        ResponseEntity concessionaria = service.getByName(name);
        return concessionaria;
    }

    @GetMapping(value = "/mostrar/cnpj")
    public ResponseEntity<DealershipResponseDTO> showCityByCNPJ(@RequestBody DealershipRequestDTO cnpj) {
        ResponseEntity concessionaria = service.getByCNPJ(cnpj.getCnpj());
        return concessionaria;
    }

    @PostMapping (value = "/cadastrar")
    public ResponseEntity insert (@RequestBody DealershipRequestDTO dto) {
        ResponseEntity re = service.insert(dto);
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getCodigo()).toUri();
        return re;
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=dealerships_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<DealershipResponseDTO> listDealerships = service.findAll();

        DealershipExporter excelExporter = new DealershipExporter(listDealerships);

        excelExporter.export(response);
    }

}
