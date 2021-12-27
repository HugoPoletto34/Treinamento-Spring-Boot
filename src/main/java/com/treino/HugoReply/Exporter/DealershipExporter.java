package com.treino.HugoReply.Exporter;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.treino.HugoReply.dto.Response.DealershipResponseDTO;
import com.treino.HugoReply.entities.Dealership;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DealershipExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<DealershipResponseDTO> listDealerships;

    public DealershipExporter(List<DealershipResponseDTO> listDealerships) {
        this.listDealerships = listDealerships;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Dealerships");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        font.setColor(IndexedColors.WHITE.index);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.ORANGE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        createCell(row, 0, "Dealership ID", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "CNPJ", style);
        createCell(row, 3, "E-mail", style);
        createCell(row, 4, "Telephone", style);
        createCell(row, 5, "City", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (DealershipResponseDTO importador : listDealerships) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, importador.getId(), style);
            createCell(row, columnCount++, importador.getNome(), style);
            createCell(row, columnCount++, importador.getCnpj(), style);
            createCell(row, columnCount++, importador.getEmail(), style);
            createCell(row, columnCount++, importador.getTelefone(), style);
            createCell(row, columnCount++, importador.getCidade().getNome(), style);

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
