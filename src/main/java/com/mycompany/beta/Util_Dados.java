/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.beta;

import com.mycompany.sistema.guarda.classe.Medalha;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.Normalizer;
/**
 *
 * @author 55329
 */
public class Util_Dados {
    public static boolean Escrever(String tipo,String Texto){
        String Caminho = "src\\\\main\\\\resources\\\\"+tipo+".txt";
        try {
            FileWriter arq = new FileWriter(Caminho);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println(Texto);
            gravarArq.close();
            return true;
        }catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static List<String> getLIDs(String tipo){
        List<String>ids = new ArrayList<>();
        switch (tipo) {
            case "Alunos":
                ids.add("Nome");
                ids.add("Grupo");
                ids.add("Turma");
                ids.add("Telefone");
                ids.add("Turno");
                break;
            case "Guarda":
                
                ids.add("Login");
                ids.add("Senha");
                ids.add("Nivel");
                ids.add("Nome");
                ids.add("Nascimento");
                ids.add("Rg");
                ids.add("Sexo");
                ids.add("Telefone");
                ids.add("Uf");
                ids.add("Cpf");
                ids.add("Emissor");
                ids.add("Bairro");
                ids.add("Rua");
                ids.add("N");
                ids.add("Cep");
                ids.add("Cidade");
                ids.add("Complemento");
                ids.add("Estado");
                ids.add("Patente");
                ids.add("Divisao");
                ids.add("Aluno Ativo");
                ids.add("Turma");
                ids.add("Turno");
                break;
            case "Bandeiras":
                ids.add("Nome");
                ids.add("Tipo");
                ids.add("Status");
                break;
            
        }
        return ids;
    }
    public static Map<String, Object> setMap(String tipo,String dado){
        Map<String, Object>ids = new HashMap<>();
        
            switch (tipo) {
                case "Alunos":
                    ids.put("Grupo", dado.split(";")[1]);
                    ids.put("Nome", dado.split(";")[0]);
                    ids.put("Turma", dado.split(";")[2]);
                    ids.put("Telefone", dado.split(";")[3]);
                    ids.put("Turno", dado.split(";")[4]);
                    break;
                case "Guarda":

                    ids.put("Login", dado.split(";")[0]);
                    ids.put("Senha", dado.split(";")[1]);
                    ids.put("Nivel", dado.split(";")[2]);
                    ids.put("Nome", dado.split(";")[3]);
                    ids.put("Nascimento", dado.split(";")[4]);
                    ids.put("Rg", dado.split(";")[5]);
                    ids.put("Sexo", dado.split(";")[6]);
                    ids.put("Telefone", dado.split(";")[7]);
                    ids.put("Uf", dado.split(";")[8]);
                    ids.put("Cpf", dado.split(";")[9]);
                    ids.put("Emissor", dado.split(";")[10]);
                    ids.put("Bairro", dado.split(";")[11]);
                    ids.put("Rua", dado.split(";")[12]);
                    ids.put("N", dado.split(";")[13]);
                    ids.put("Cep", dado.split(";")[14]);
                    ids.put("Cidade", dado.split(";")[15]);
                    ids.put("Complemento", dado.split(";")[16]);
                    ids.put("Estado", dado.split(";")[17]);
                    ids.put("Patente", dado.split(";")[18]);
                    ids.put("Divisao", dado.split(";")[19]);

                    ids.put("Aluno Ativo", dado.split(";")[20]);
                    ids.put("Turma", dado.split(";")[21]);
                    ids.put("Turno", dado.split(";")[22]);
                    break;
                case "Bandeiras":
                    ids.put("Nome", dado.split(";")[0]);
                    ids.put("Tipo", dado.split(";")[1]);
                    ids.put("Status", dado.split(";")[2]);
                    break;

            }
        
        return ids;
    }
    public static String cleanAcentos(String texto) {
        if (texto == null) {
            return null;
        }
        
        // Normaliza a string para decompor caracteres acentuados
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        
        // Remove os diacríticos (acentos)
        return textoNormalizado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
}
