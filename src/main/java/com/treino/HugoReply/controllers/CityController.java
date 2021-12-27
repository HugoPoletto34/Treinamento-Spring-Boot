package com.treino.HugoReply.controllers;

import com.treino.HugoReply.Exporter.CityExporter;
import com.treino.HugoReply.dto.Request.CityRequestDTO;
import com.treino.HugoReply.dto.Response.CityResponseDTO;
import com.treino.HugoReply.services.CityService;
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

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=cities_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<CityResponseDTO> listCitys = service.findAll();

        CityExporter excelExporter = new CityExporter(listCitys);

        excelExporter.export(response);
    }

}
