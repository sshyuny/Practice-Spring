package com.sshyuny.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public abstract class ExcelDataConverter<T> {

    private int sheetIdx;
    private int startRowIdx;
    private Workbook workbook;

    public ExcelDataConverter(int sheetIdx, int startRowIdx, Workbook workbook) {
        this.sheetIdx = sheetIdx;
        this.startRowIdx = startRowIdx;
        this.workbook = workbook;
    }

    List<T> makeExcelDataList() {

        Sheet sheet = workbook.getSheetAt(sheetIdx);
        int lastRowNum = sheet.getLastRowNum();
        List<T> excelDataList = new ArrayList<>();

        for (int i = startRowIdx; i < lastRowNum + 1; i++) {
            Row row = sheet.getRow(i);
            T excelData = convertExcelDataToClass(row, i);
            excelDataList.add(excelData);
        }

        return excelDataList;
    }

    public static String convertCellToStr(Cell cell) {

        if (cell == null) {
            return "err";
        }

        switch (cell.getCellType()) {
            case STRING:
                return String.valueOf(cell.getStringCellValue());
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BLANK:
                return "err";
            default:
                return "err";
        }
    }

    public abstract T convertExcelDataToClass(Row row, int i);

}
