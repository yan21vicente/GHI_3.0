/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema.guarda.classe.convert;

import com.mycompany.sistema.guarda.classe.Aluno;
import com.mycompany.sistema.guarda.classe.Medalha;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author 55329
 */
public class MedalhasConvert {
    public static List<String> StrigToDado(List<Medalha> membros){
        List<String> medalhas = new ArrayList<>();
                
        for(Medalha user:membros){
            String list = user.getDono() + ";" +
            user.getNome()+";"+
            user.getTipo()+";"+
            user.getId()+";"+
            user.getData();
            medalhas.add(list);
        }
        return medalhas;
    }
    public static List<Medalha> DadoToStrig(List<String> lista){
        List<Medalha> medalhas = new ArrayList<>();
        if(lista == null || lista.isEmpty()){
            return medalhas;
        }
        
        for(String conteudo:lista){
            // Pular linhas vazias ou nulas
            if(conteudo == null || conteudo.trim().isEmpty()){
                continue;
            }
            Medalha medalha = new Medalha(conteudo.split(";")[0], conteudo.split(";")[1],conteudo.split(";")[2]);
            medalha.setId(conteudo.split(";")[3]);
            medalha.setData(conteudo.split(";")[4]);
            medalhas.add(medalha);
        }
        return medalhas;
    }
}
