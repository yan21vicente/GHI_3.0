package com.mycompany.sistema.extra.execel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExcelToNestedMap {

    public static Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // Formatar a data como "MMM dd" (exemplo: "Oct 03")
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd");
                    return dateFormat.format(cell.getDateCellValue());
                } else {
                    return cell.getNumericCellValue();
                }
            case FORMULA:
                FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                return getCellValue(evaluator.evaluateInCell(cell));
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case BLANK:
                return null;
            default:
                return null;
        }
    }

    public static Map<String, Map<String, Object>> extractDataFromExcel(String filePath) {
        Map<String, Map<String, Object>> dataMap = new LinkedHashMap<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            // Obter a primeira linha como cabeçalhos
            Row headerRow = sheet.getRow(0);
            int columnCount = headerRow.getLastCellNum();

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                if (row == null) continue;

                Cell nameCell = row.getCell(0);
                if (nameCell == null) continue;

                String personName = (String) getCellValue(nameCell);

                // Ignorar linhas sem nome
                if (personName == null || personName.trim().isEmpty()) continue;

                Map<String, Object> personData = new LinkedHashMap<>();

                for (int colIndex = 1; colIndex < columnCount; colIndex++) {
                    Cell headerCell = headerRow.getCell(colIndex);
                    Cell valueCell = row.getCell(colIndex);

                    String header = (String) getCellValue(headerCell);
                    Object value = getCellValue(valueCell);

                    // Ignorar células vazias (tanto chave quanto valor)
                    if (header != null && !header.trim().isEmpty() && value != null) {
                        personData.put(header, value);
                    }
                }

                // Adicionar ao mapa principal apenas se houver dados
                if (!personData.isEmpty()) {
                    dataMap.put(personName, personData);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataMap;
    }
}
