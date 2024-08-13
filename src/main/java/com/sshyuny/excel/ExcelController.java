package com.sshyuny.excel;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ExcelController {

    private final ExcelService excelService;
    
    // [MAC] curl -X POST -F "file=@/Users/sohyun/Documents/test/test.png" http://localhost:8080/excel/converter --verbose
    // [WINDOW] curl -X POST -F "file=@C:\\Users\\user\\Desktop\\test.xlsx" http://localhost:8080/excel/converter --verbose
    @PostMapping("/excel/converter")
    public String postExcelConverter(@RequestParam("file") MultipartFile file) throws IOException {

        excelService.makeLibrariesFromExcel(file);

        return "done";
    }
}
