package com.treino.HugoReply.Exporter;



import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public interface Utils {

    default XSSFFont defaultXSSFFont(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
//        font.setBold(true);
        font.setFontHeight(11);
        font.setColor(IndexedColors.BLACK.index);

        return font;
    }

    default XSSFCellStyle defaultStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = defaultXSSFFont(workbook);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
//        style.setBorderTop(BorderStyle.THIN);
//        style.setBorderBottom(BorderStyle.THIN);
//        style.setBorderLeft(BorderStyle.THIN);
//        style.setBorderRight(BorderStyle.THIN);
//
//
//        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.index);
//        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.index);
//        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.index);
//        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.index);

        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return style;
    }

    default XSSFCellStyle cabecalhoPrincipal(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        font.setFontHeight(24);
        font.setBold(true);
        font.setFontName("Calibri");
        font.setColor(IndexedColors.BLACK.index);

        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }

    default void setBordersToMergedCells(XSSFSheet sheet) {
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        for (CellRangeAddress rangeAddress : mergedRegions) {
            RegionUtil.setBorderTop(BorderStyle.THIN, rangeAddress, sheet);
            RegionUtil.setBorderLeft(BorderStyle.THIN, rangeAddress, sheet);
            RegionUtil.setBorderRight(BorderStyle.THIN, rangeAddress, sheet);
            RegionUtil.setBorderBottom(BorderStyle.THIN, rangeAddress, sheet);
        }
    }

    default XSSFCellStyle cabecalhoData(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        font.setFontHeight(11);
        font.setFontName("Calibri");
        font.setColor(IndexedColors.BLACK.index);

        style.setFont(font);
        style.setAlignment(HorizontalAlignment.RIGHT);

        return style;
    }

    default XSSFCellStyle cabecalhoTabelaPrimeiraLinha(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        font.setFontHeight(14);
        font.setBold(true);
        font.setFontName("Calibri");
        font.setColor(IndexedColors.BLACK.index);

        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

//        style.setBorderTop(BorderStyle.THIN);
//        style.setBorderBottom(BorderStyle.THIN);
//        style.setBorderLeft(BorderStyle.THIN);
//        style.setBorderRight(BorderStyle.THIN);
//
//
//        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.index);
//        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.index);
//        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.index);
//        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.index);


        return style;
    }

    default XSSFCellStyle cabecalhoLinhasFilhas(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        font.setFontHeight(11);
        font.setBold(true);
        font.setFontName("Calibri");
        font.setColor(IndexedColors.BLACK.index);

        style.setWrapText(true);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
//
//
//        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.index);
//        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.index);
//        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.index);
//        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.index);


        return style;
    }

    default void createHeader(XSSFSheet sheet, XSSFWorkbook workbook, List<String> columns) {
        Row row1 = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        Row row3 = sheet.createRow(2);
        Row row4 = sheet.createRow(3);
        Row row5 = sheet.createRow(4);
            sheet.setColumnWidth(0, 3500);
//        Row cab = sheet.createRow(0);
        XSSFCellStyle styleData = cabecalhoData(workbook);
        XSSFCellStyle stylePrincipal = cabecalhoPrincipal(workbook);
        XSSFCellStyle stylePrimeiraLinha = cabecalhoTabelaPrimeiraLinha(workbook);
        XSSFCellStyle styleLinhasFilhas = cabecalhoLinhasFilhas(workbook);

        createCell(row1, sheet, 0, "out/21", styleData);

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0,25));
        createCell(row2, sheet, 0, "              SCORECARD IMPA | OCTUBRE 2021", stylePrincipal);

        sheet.addMergedRegion(new CellRangeAddress(2, 4, 0,0));
        createCell(row3, sheet, 0, "Importador", stylePrimeiraLinha);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1,2));
        createCell(row3, sheet, 1, "GLOBAL", stylePrimeiraLinha);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 3,12));
        createCell(row3, sheet, 3, "VENTAS", stylePrimeiraLinha);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 13,19));
        createCell(row3, sheet, 13, "POST VENTAS", stylePrimeiraLinha);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 20,25));
        createCell(row3, sheet, 20 , "CALIDAD", stylePrimeiraLinha);


        sheet.addMergedRegion(new CellRangeAddress(3, 4, 1,1));
        createCell(row4, sheet, 1, "Grade", styleLinhasFilhas);
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 2,2));
        createCell(row4, sheet, 2, "Rank", styleLinhasFilhas);

        sheet.addMergedRegion(new CellRangeAddress(3, 3, 3,4));
        createCell(row4, sheet, 3, "Ventas Mes", styleLinhasFilhas);
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 5,6));
        createCell(row4, sheet, 5, "Ventas YTD", styleLinhasFilhas);
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 7,7));
        createCell(row4, sheet, 7, "RANK % OBJ VENTAS YTD", styleLinhasFilhas);

        createCell(row4, sheet, 8, "PDM MES", styleLinhasFilhas);
        createCell(row4, sheet, 9, "PDM YTD", styleLinhasFilhas);
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 10,10));
        createCell(row4, sheet, 10, "RANK PDM YTD", styleLinhasFilhas);
        createCell(row4, sheet, 11, "EVO PDM YTD N-1", styleLinhasFilhas);
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 12,12));
        createCell(row4, sheet, 12, "RANK EVO PDM", styleLinhasFilhas);

        sheet.addMergedRegion(new CellRangeAddress(3, 3, 13,14));
        createCell(row4, sheet, 13, "COMPRAS PR MES (en kEUR)", styleLinhasFilhas);
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 15,16));
        createCell(row4, sheet, 15, "COMPRAS PR YTD (en kEUR)", styleLinhasFilhas);
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 17,17));
        createCell(row4, sheet, 17, "RANK COMPRAS PR YTD", styleLinhasFilhas);
        createCell(row4, sheet, 18, "CN/PARQUE PR YTD", styleLinhasFilhas);
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 19,19));
        createCell(row4, sheet, 19, "RANK CA/PARQUE PR YTD", styleLinhasFilhas);

        sheet.addMergedRegion(new CellRangeAddress(3, 3, 20,21));
        createCell(row4, sheet, 20, "RECO VN", styleLinhasFilhas);
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 22,22));
        createCell(row4, sheet, 22, "RANK RECO VN YTD", styleLinhasFilhas);
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 23,24));
        createCell(row4, sheet, 23, "RECO PV", styleLinhasFilhas);
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 25,25));
        createCell(row4, sheet, 25, "RANK RECO PV YTD", styleLinhasFilhas);

        createCell(row5, sheet, 3, "Vol.", styleLinhasFilhas);
        createCell(row5, sheet, 4, "% Cumpl", styleLinhasFilhas);
        createCell(row5, sheet, 5, "Vol.", styleLinhasFilhas);
        createCell(row5, sheet, 6, "% Cumpl", styleLinhasFilhas);
        createCell(row5, sheet, 8, "%", styleLinhasFilhas);
        createCell(row5, sheet, 9, "%", styleLinhasFilhas);
        createCell(row5, sheet, 11, "% Evo", styleLinhasFilhas);

        createCell(row5, sheet, 13, "Vol.", styleLinhasFilhas);
        createCell(row5, sheet, 14, "% Cumpl", styleLinhasFilhas);
        createCell(row5, sheet, 15, "Vol.", styleLinhasFilhas);
        createCell(row5, sheet, 16, "% Cumpl", styleLinhasFilhas);
        createCell(row5, sheet, 18, "Ratio", styleLinhasFilhas);

        createCell(row5, sheet, 20, "3MM", styleLinhasFilhas);
        createCell(row5, sheet, 21, "YTD", styleLinhasFilhas);
        createCell(row5, sheet, 23, "3MM", styleLinhasFilhas);
        createCell(row5, sheet, 24, "YTD", styleLinhasFilhas);

        setBordersToMergedCells(sheet);

//        createCell(row2, sheet, 2, "Rank", style);
//        XSSFCellStyle cellStyle = workbook.createCellStyle();
//        cellStyle.setAlignment(HorizontalAlignment.CENTER);

//        createCell(cab, sheet,0, "Dealerships", cellStyle);

//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,7));

//        int i = 0;
//        for (String column : columns)
//            createCell(row, sheet, i++, column, style);



        sheet.createFreezePane(0,5);
    }

    default void createCell(Row row, XSSFSheet sheet, int columnCount, Object value, XSSFCellStyle style) {

        Cell cell = row.createCell(columnCount);
        if (value instanceof Double) {
            cell.setCellValue((Double) value);
//            style.setDataFormat(workbook.createDataFormat()
//                    .getFormat(BuiltinFormats.getBuiltinFormat( 10 )));
        }
        else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        }
        else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
//        sheet.autoSizeColumn(columnCount);
        cell.setCellStyle(style);
    }

}
