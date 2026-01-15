package com.mycompany.sistema.extra.execel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ExcelGeneratos {

    public static void criarArquivoExcelComTabela(String filePath, List<String> nomesAlunos, List<String> diasEnsaios) {
        // Criar um novo workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Presença de Ensaios");

        // Criar a primeira linha com os cabeçalhos dos dias de ensaio
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nome");
        for (int i = 0; i < diasEnsaios.size(); i++) {
            headerRow.createCell(i + 1).setCellValue(diasEnsaios.get(i));
        }

        // Adicionar os nomes dos alunos nas linhas e deixar as células em branco para presença/ausência
        for (int i = 0; i < nomesAlunos.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(nomesAlunos.get(i));
            for (int j = 1; j <= diasEnsaios.size(); j++) {
                row.createCell(j).setCellValue(""); // Inicialmente vazio, pode ser "P" para presente ou "F" para falta
            }
        }

        // Ajustar a largura das colunas para caber o conteúdo
        for (int i = 0; i <= diasEnsaios.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Escrever o workbook em um arquivo
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Arquivo Excel criado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo Excel: " + e.getMessage());
        }
    }

    public static void exportarTabelaParaExcel(JTable tabela, String filePath) {
        // Criar um novo workbook e uma planilha
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Dados da Tabela");

        // Obter o modelo da tabela
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();

        // Criar a linha de cabeçalho
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < model.getColumnCount(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(model.getColumnName(col)); // Adicionar o nome da coluna
        }

        // Adicionar os dados da tabela
        for (int row = 0; row < model.getRowCount(); row++) {
            Row sheetRow = sheet.createRow(row + 1); // +1 para pular a linha de cabeçalho
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cell = sheetRow.createCell(col);
                Object value = model.getValueAt(row, col);
                if (value != null) {
                    cell.setCellValue(value.toString()); // Adicionar o valor da célula
                } else {
                    cell.setCellValue(""); // Célula vazia se o valor for nulo
                }
            }
        }

        // Ajustar a largura das colunas para caber o conteúdo
        for (int col = 0; col < model.getColumnCount(); col++) {
            sheet.autoSizeColumn(col);
        }

        // Escrever o workbook em um arquivo
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Arquivo Excel criado com sucesso em: " + filePath);
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo Excel: " + e.getMessage());
        }
    }
}

