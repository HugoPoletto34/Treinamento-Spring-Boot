package com.treino.HugoReply.controllers;

import com.treino.HugoReply.Exporter.CityExporter;
import com.treino.HugoReply.Exporter.DealershipExporter;
import com.treino.HugoReply.Exporter.GlobalExporter;
import com.treino.HugoReply.dto.ModuleExporterDTO;
import com.treino.HugoReply.services.CityService;
import com.treino.HugoReply.services.DealershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value="/export")
public class ExporterController {

    @Autowired
    private CityService cityService;

    @Autowired
    private DealershipService dealershipService;

    @GetMapping("/dealership")
    public void exportDealership(HttpServletResponse response, @RequestBody ModuleExporterDTO moduleExporterDTO) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=dealerships_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

//        moduleExporterDTO.setDealershipList(dealershipService.findAll());

        DealershipExporter excelExporter = new DealershipExporter(moduleExporterDTO.getDealershipList());

        excelExporter.export(response);
    }

    @GetMapping("/city")
    public void exportCity(HttpServletResponse response, @RequestBody ModuleExporterDTO moduleExporterDTO) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=cities_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

//        moduleExporterDTO.setCityList(cityService.findAll());

        CityExporter excelExporter = new CityExporter(moduleExporterDTO.getCityList());

        excelExporter.export(response);
    }

    @GetMapping("/global")
    public void exportAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=global" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ModuleExporterDTO moduleExporterDTO = new ModuleExporterDTO();

        moduleExporterDTO.setCityList(cityService.findAll());
        moduleExporterDTO.setDealershipList(dealershipService.findAll());

        GlobalExporter excelExporter = new GlobalExporter(moduleExporterDTO);

        excelExporter.export(response);
    }
}
