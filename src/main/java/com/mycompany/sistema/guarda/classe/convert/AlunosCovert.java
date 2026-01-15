/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema.guarda.classe.convert;

import com.mycompany.sistema.guarda.classe.Aluno;
import com.mycompany.sistema.guarda.classe.Bandeira;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author 55329
 */
public class AlunosCovert {
    public static Map<String, Object> formatarListaParaString(Aluno aluno) {
        Map<String, Object>ids = new HashMap<>();
        
        ids.put("Nome", aluno.getNome());
        ids.put("Grupo", aluno.getGrupo());
        ids.put("Turma", aluno.getTurma());
        ids.put("Telefone", aluno.getTelefone());
        ids.put("Turno", aluno.getTurno());
        
        return ids;
    }
    public static List<String> StrigToDado(List<Aluno> membros){
        List<String> alunos = new ArrayList<>();
                
        for(Aluno user:membros){
            String list = user.getNome() + ";" +
            user.getGrupo()+";"+
            user.getTurma()+";"+
            user.getTelefone()+";"+
            user.getTurno();
            alunos.add(list);
        }
        return alunos;
    }
    public static List<Aluno> DadoToStrig(List<String> lista){
        List<Aluno> users = new ArrayList<>();
        if(lista == null || lista.isEmpty()){
            return users;
        }
        for(String conteudo:lista){
            // Pular linhas vazias ou nulas
            if(conteudo == null || conteudo.trim().isEmpty()){
                continue;
            }
            Aluno user = new Aluno();
            System.err.println(conteudo);
            
            user.setNome(conteudo.split(";")[0]);
            user.setGrupo(conteudo.split(";")[1]);
            user.setTurma(conteudo.split(";")[2]);
            user.setTelefone(conteudo.split(";")[3]);
            user.setTurno(conteudo.split(";")[4]);
            users.add(user);
        }
        return users;
    }
}
