package com.treino.HugoReply.Exporter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.treino.HugoReply.dto.Response.DealershipResponseDTO;
import com.treino.HugoReply.entities.Dealership;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.*;

public class DealershipExporter implements Utils{
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<DealershipResponseDTO> listDealerships;

    public DealershipExporter(List<DealershipResponseDTO> listDealerships) {
        this.listDealerships = listDealerships;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Dealerships");
        List<String> columns = new ArrayList<>();
        columns.add("Dealership ID");
        columns.add("Name");
        columns.add("CNPJ");
        columns.add("E-mail");
        columns.add("Telephone");
        columns.add("Sales");
        columns.add("Percent sales");
        columns.add("City");

        createHeader(sheet, workbook, columns);
//
//        sheet = workbook.createSheet("Dealerships");
//
//        Row row = sheet.createRow(1);
//        Row cab = sheet.createRow(0);
//
//        CellStyle style = workbook.createCellStyle();
//        XSSFFont font = workbook.createFont();
//        font.setBold(true);
//        font.setFontHeight(16);
//        font.setColor(IndexedColors.WHITE.index);
//        style.setFont(font);
//        style.setFillForegroundColor(IndexedColors.ORANGE.index);
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//
//        CellStyle cellStyle = workbook.createCellStyle();
//        cellStyle.setAlignment(HorizontalAlignment.CENTER);
//
//        createCell(cab, sheet, 0, "Dealerships", cellStyle);
//
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,7));


//        sheet.createFreezePane(0,1);

    }


    private void writeDataLines() {
        int rowCount = 2;

        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (DealershipResponseDTO concessionaria : listDealerships) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, sheet, columnCount++, concessionaria.getId(), style);
            createCell(row, sheet, columnCount++, concessionaria.getNome(), style);
            createCell(row, sheet, columnCount++, concessionaria.getCnpj(), style);
            createCell(row, sheet, columnCount++, concessionaria.getEmail(), style);
            createCell(row, sheet, columnCount++, concessionaria.getTelefone(), style);
            createCell(row, sheet, columnCount++, concessionaria.getValorVendas(), style);
            createCell(row, sheet, columnCount++, concessionaria.getPorcentagemValorVendas(), style);
            createCell(row, sheet, columnCount++, concessionaria.getCidade().getNome(), style);

        }

        applyIcons(rowCount);
    }

    private void applyIcons(int numRows) {
        List<String> my_range=new ArrayList<String>();
        my_range.add("F2:F" + numRows);

        CTConditionalFormatting iconSet= sheet.getCTWorksheet().addNewConditionalFormatting();
        iconSet.setSqref(my_range);

        CTCfRule myCFRule=iconSet.addNewCfRule();
        myCFRule.setType(STCfType.ICON_SET);
        myCFRule.setPriority(1);

        CTIconSet myIconSet=myCFRule.addNewIconSet();
        myIconSet.setIconSet(STIconSetType.Enum.forInt(STIconSetType.INT_X_4_TRAFFIC_LIGHTS));

        CTCfvo iConValues1=myIconSet.addNewCfvo();
        CTCfvo iConValues2=myIconSet.addNewCfvo();
        CTCfvo iConValues3=myIconSet.addNewCfvo();
        CTCfvo iConValues4=myIconSet.addNewCfvo();
        iConValues1.setType(STCfvoType.NUM);
        iConValues2.setType(STCfvoType.NUM);
        iConValues3.setType(STCfvoType.NUM);
        iConValues4.setType(STCfvoType.NUM);
        iConValues1.setVal("0");
        iConValues2.setVal("25");
        iConValues3.setVal("50");
        iConValues4.setVal("75");
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
//        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
