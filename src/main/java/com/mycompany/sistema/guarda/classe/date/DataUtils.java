package com.mycompany.sistema.guarda.classe.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataUtils {

    public static List<String> obterDiasDaSemana(LocalDate inicio, LocalDate fim, DayOfWeek diaDaSemana) {
        List<String> diasDaSemanaFormatados = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

        LocalDate dataAtual = inicio;
        
        // Encontrar o primeiro dia da semana no intervalo
        while (dataAtual.getDayOfWeek() != diaDaSemana) {
            dataAtual = dataAtual.plusDays(1);
        }
        
        // Adicionar todos os dias da semana até o final do intervalo
        while (!dataAtual.isAfter(fim)) {
            diasDaSemanaFormatados.add(dataAtual.format(formatter));
            dataAtual = dataAtual.plusWeeks(1);
        }
        
        return diasDaSemanaFormatados;
    }
}
