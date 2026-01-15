/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.beta;

import com.mycompany.sistema.extra.firebase.firestore;
import com.mycompany.sistema.guarda.classe.convert.AlunosCovert;
import com.mycompany.sistema.guarda.classe.Aluno;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 55329
 */
public class Banco implements Dados{

    @Override
    public List<String> ler(String tipo) {
        List<String> dados = new ArrayList<>();
        try{
            
            List<String>campos = Util_Dados.getLIDs(tipo);
            dados = firestore.lerDadosColunaFormatados(tipo, campos);
        }catch (Exception e) {
            System.out.println("Erro ao ler dados: " + e.getMessage());
            return null;
        }
        return dados;
    }

    @Override
    public void escrever(List<String> dados,String tipo) {
        for (String aluno : dados) {
            List<String> d = Arrays.asList(aluno.split(";"));
            try{
                Map<String, Object> datos = Util_Dados.setMap(tipo, aluno);
                firestore.guardarOuAtualizar(tipo, d.get(0), datos);
            }catch (HeadlessException e){
                System.err.println("Error: "+e.getMessage());
                //JOptionPane.showMessageDialog(null, "Error al guar");
            }
            //JOptionPane.showMessageDialog(null, "Sucesso");
        }
    }
    
}
