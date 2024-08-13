package com.sshyuny.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelService {
    
    public List<Library> makeLibrariesFromExcel(MultipartFile reqFile) throws IOException, FileNotFoundException {

        File tempFile = File.createTempFile("tempfile", ".tmp"); // 임시 저장 위치 
        log.info("임시 파일 위치 = ", tempFile.getAbsolutePath());
        reqFile.transferTo(tempFile);

        FileInputStream fileInputStream = new FileInputStream(tempFile);
        Workbook workbook = WorkbookFactory.create(fileInputStream);

        ExcelDataConverter<Library> excelData = new ExcelDataConverter<Library>(0, 1, workbook) {
            @Override
            public Library convertExcelDataToClass(Row row, int i) {
                return Library.builder()
                    .name(ExcelDataConverter.convertCellToStr(row.getCell(0)))
                    .region(ExcelDataConverter.convertCellToStr(row.getCell(1)))
                    .build();
            }
        };

        try {
            return excelData.makeExcelDataList();
        } finally {
            workbook.close();
            fileInputStream.close();
            tempFile.delete();
        }

    }
}
