package org.example.ais.services;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.ais.models.Loan;
import org.example.ais.repositorys.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Lazy
@Service
public abstract class LoanService implements LoanRepository {

    private final static String PREFFIX = "Loan";
    private final LoanRepository loanRepository;


    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }




    public ByteArrayOutputStream exportToExcel() throws IOException {
        List<Loan> loanList = loanRepository.findAll();

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet(PREFFIX);

        // Создание шапки таблицы
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Position");



        int rowNum = 1;
        for (Loan loan : loanList) {
            XSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(loan.getName());
            row.createCell(1).setCellValue(loan.getInterestRate());
            row.createCell(2).setCellValue(loan.getTerm());
        }

        // Установка заголовка ответа и тип содержимого
        response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Создание ByteArrayOutputStream для хранения файла Excel
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();


        return outputStream;
    }

}
