package org.example.ais.services;

import lombok.val;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.ais.models.Loan;
import org.example.ais.projections.LoanProjection;
import org.example.ais.repositorys.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Lazy
@Service
public class LoanService {
    private final static String PREFFIX = "Loan";
    private final LoanRepository loanRepository;


    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }


    public byte[] exportToExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(PREFFIX);

        // Заполняем шапку таблицы
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Description");
        row.createCell(3).setCellValue("InterestRate");
        row.createCell(4).setCellValue("Term");

        // Заполняем данные из базы данных
        int rowNum = 1;
        List<Loan> loans = loanRepository.findAll();
        for (val entity : loans) {
            Row dataRow = sheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue(entity.getId());
            dataRow.createCell(1).setCellValue(entity.getName());
            dataRow.createCell(2).setCellValue(entity.getDescription());


            Cell currencyCell = dataRow.createCell(3);
            currencyCell.setCellValue(entity.getInterestRate());

            DataFormat format = workbook.createDataFormat();

            // Устанавливаем формат ячейки для валютных значений
            CellStyle currencyStyle = workbook.createCellStyle();
            currencyStyle.setDataFormat(format.getFormat("[$RU-ru] # ##0.00"));
            currencyCell.setCellStyle(currencyStyle);


            // Форматируем ячейки для процентных значений и валютных значений
            Cell percentageCell = dataRow.createCell(4);
            percentageCell.setCellValue(entity.getTerm());

            // Устанавливаем формат ячейки для процентных значений
            CellStyle percentageStyle = workbook.createCellStyle();
            percentageStyle.setDataFormat(format.getFormat("0.00%"));
            percentageCell.setCellStyle(percentageStyle);



        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }

    public void save(Loan loanRow) {
        loanRepository.save(loanRow);
    }

    public void delete(Long id) {
        loanRepository.deleteById(id);
    }

    public List<Loan> findAll() {
        return loanRepository.findAll(Sort.by(Sort.Direction.ASC, Loan.COLUMN_NAME));
    }


    public List<LoanProjection> findProjectionByStartWith(
            String patternName, Double patternInterestRate, Double patternTerm
    ) {
        return loanRepository.findProjectionByNameStartingWithAndInterestRateStartingWithAndTermStartingWith(
                patternName, patternInterestRate, patternTerm, Sort.by(Sort.Direction.ASC, Loan.COLUMN_NAME)
        );
    }

    public List<LoanProjection> findProjectionByStartWith(
            String patternName, Double patternInterestRate, Double patternTerm, Sort sort
    ) {
        return loanRepository.findProjectionByNameStartingWithAndInterestRateStartingWithAndTermStartingWith(
                patternName, patternInterestRate, patternTerm, sort
        );
    }
}
