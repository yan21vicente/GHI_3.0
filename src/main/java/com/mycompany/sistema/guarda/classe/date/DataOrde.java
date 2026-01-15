package com.mycompany.sistema.guarda.classe.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataOrde {

    // Função para ordenar uma lista de strings de datas
    public static List<String> ordenarDatas(List<String> datas) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        DateTimeFormatter formatterComAno = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int anoPadrao = 2000;  // Ano padrão para o LocalDate

        // Converter strings para objetos LocalDate com ano fixo
        List<LocalDate> datasLocalDate = new ArrayList<>();
        for (String data : datas) {
            LocalDate localDate = LocalDate.parse(data + "/" + anoPadrao, formatterComAno);
            datasLocalDate.add(localDate);
        }

        // Ordenar a lista de LocalDate
        Collections.sort(datasLocalDate);

        // Converter de volta para strings no formato "dd/MM"
        List<String> datasOrdenadas = new ArrayList<>();
        for (LocalDate data : datasLocalDate) {
            datasOrdenadas.add(data.format(formatter));
        }

        return datasOrdenadas;
    }
}
