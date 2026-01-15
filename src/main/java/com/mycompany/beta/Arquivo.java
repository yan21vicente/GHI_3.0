/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.beta;

import com.mycompany.sistema.guarda.classe.convert.AlunosCovert;
import com.mycompany.sistema.guarda.classe.Aluno;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 55329
 */
public class Arquivo implements Dados {

    @Override
    public List<String> ler(String tipo) {
        List<String> conteudos = new ArrayList<>();
        String caminho = tipo + ".txt";
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(caminho);
             BufferedReader lerArq = new BufferedReader(new InputStreamReader(is))) {

            String linha = lerArq.readLine();
            while (linha != null) {
                linha = Util_Dados.cleanAcentos(linha);
                conteudos.add(linha);
                linha = lerArq.readLine();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado! " + caminho);
            Util_Dados.Escrever(tipo, "");
        } catch (IOException ex) {
            System.out.println("Erro: Não foi possível ler o arquivo!");
        } catch (NullPointerException ex) {
            System.out.println("Erro: Caminho inválido ou arquivo não encontrado! " + caminho);
            Util_Dados.Escrever(tipo, "");
        }

        return conteudos;
    }


    @Override
    public void escrever(List<String> dados,String tipo) {
        String conteudo = new String();
        conteudo = dados.get(0);
        for (int i = 1; i < dados.size(); i++) {
            String list = dados.get(i);
            conteudo = conteudo + "\n" + list;
        }
        Util_Dados.Escrever(tipo, conteudo);
    }
    
    public void atualizar(){
        
    }
}
