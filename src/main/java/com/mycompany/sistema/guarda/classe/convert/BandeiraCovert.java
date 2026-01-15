/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema.guarda.classe.convert;

import com.mycompany.sistema.guarda.classe.Bandeira;
import com.mycompany.sistema.guarda.classe.perfil.Usuario;
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

/**
 *
 * @author 55329
 */
public class BandeiraCovert {
    public static Map<String, Object> formatarListaParaString(Bandeira ban) {
        Map<String, Object>ids = new HashMap<>();
        
        ids.put("Nome", ban.getNome());
        ids.put("Status", ban.getStatus());
        ids.put("Tipo", ban.getTipo());
        
        return ids;
    }
    public static List<String> StrigToDado(List<Bandeira> membros){
        List<String> bandeiras = new ArrayList<>();
                
        for(Bandeira user:membros){
            String list = user.getNome()+ ";" +
            user.getTipo()+ ";" +
            user.getStatus();

            bandeiras.add(list);
        }
        return bandeiras;
    }
    
    public static List<Bandeira> DadoToStrig(List<String> lista){
        List<Bandeira> users = new ArrayList<>();
        if(lista == null || lista.isEmpty()){
            return users;
        }
        if(!lista.isEmpty()){
            for(String conteudo:lista){
                // Pular linhas vazias ou nulas
                if(conteudo == null || conteudo.trim().isEmpty()){
                    continue;
                }
                Bandeira user = new Bandeira();
                System.err.println(conteudo);
                user.setNome(conteudo.split(";")[0]);
                user.setTipo(conteudo.split(";")[1]);
                user.setStatus(conteudo.split(";")[2]);

                users.add(user);

            }
            
        }
        return users;
    }
}
