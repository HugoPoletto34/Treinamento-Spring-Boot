package com.treino.HugoReply.Exporter;

import com.treino.HugoReply.dto.ModuleExporterDTO;
import com.treino.HugoReply.dto.Response.CityResponseDTO;
import com.treino.HugoReply.dto.Response.DealershipResponseDTO;
import com.treino.HugoReply.repositories.CityRepository;
import com.treino.HugoReply.repositories.DealershipRepository;
import com.treino.HugoReply.services.CityService;
import com.treino.HugoReply.services.DealershipService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GlobalExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private ModuleExporterDTO moduleExporterDTO;

    public GlobalExporter(ModuleExporterDTO moduleExporterDTO) {
        this.moduleExporterDTO = moduleExporterDTO;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Global");

        Row cab = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(cab, 0, "Concessionaria", style);
        createCell(cab, 7, "Cidade", style);

        Row cab2 = sheet.createRow(1);
        createCell(cab2, 0, "Dealership ID", style);
        createCell(cab2, 1, "Name", style);
        createCell(cab2, 2, "CNPJ", style);
        createCell(cab2, 3, "E-Mail", style);
        createCell(cab2, 4, "Telefone", style);
        createCell(cab2, 5, "Vendas", style);
        createCell(cab2, 6, "Vendas (%)", style);
        createCell(cab2, 7, "City ID", style);
        createCell(cab2, 8, "City Name", style);
        createCell(cab2, 9, "UF", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Double) {
            cell.setCellValue((Double) value);
//            style.setDataFormat(workbook.createDataFormat()
//                    .getFormat(BuiltinFormats.getBuiltinFormat( 10 )));
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 2;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);


        for (DealershipResponseDTO concessionaria : moduleExporterDTO.getDealershipList()) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, concessionaria.getId(), style);
            createCell(row, columnCount++, concessionaria.getNome(), style);
            createCell(row, columnCount++, concessionaria.getCnpj(), style);
            createCell(row, columnCount++, concessionaria.getEmail(), style);
            createCell(row, columnCount++, concessionaria.getTelefone(), style);
            createCell(row, columnCount++, concessionaria.getValorVendas(), style);
            createCell(row, columnCount++, concessionaria.getPorcentagemValorVendas(), style);
        }
        rowCount = 2;
        for (CityResponseDTO importador : moduleExporterDTO.getCityList()) {
            Row row = sheet.getRow(rowCount++);
            int columnCount = 7;

            createCell(row, columnCount++, importador.getId(), style);
            createCell(row, columnCount++, importador.getNome(), style);
            createCell(row, columnCount++, importador.getUf().name(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
