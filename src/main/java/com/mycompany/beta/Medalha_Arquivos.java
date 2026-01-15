/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.beta;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import static com.mycompany.sistema.extra.firebase.Conexcion.db;
import com.mycompany.sistema.extra.firebase.firestore;
import com.mycompany.sistema.guarda.classe.perfil.Usuario;
import com.mycompany.sistema.guarda.classe.Medalha;
import com.mycompany.sistema.guarda.classe.convert.MedalhasConvert;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author 55329
 */
public class Medalha_Arquivos {
    
    private static Map<String, Object> medalhaToMap(Medalha medalha) {
        Map<String, Object> medalhaData = new HashMap<>();
        medalhaData.put("Nome", medalha.getNome());
        medalhaData.put("Tipo", medalha.getTipo());
        medalhaData.put("Id", medalha.getId());
        medalhaData.put("Data", medalha.getData());
        medalhaData.put("Dono", medalha.getDono());
        return medalhaData;
    }
    
    public static  List<Medalha> ler() {
        List<String> dados = new ArrayList<>();
        try{
            List<String>campos = new ArrayList<>();
            campos.add("Dono");
            campos.add("Nome");
            campos.add("Id");
            campos.add("Tipo");
            campos.add("Data");
            dados = firestore.lerDadosColunaFormatados("Medalhas", campos);
        }catch (Exception e) {
            System.out.println("Erro ao ler dados: " + e.getMessage());
            return null;
        }
        return MedalhasConvert.DadoToStrig(dados);
    }

    public static void escrever(List<Medalha> medalhas) {
        for (Medalha medalha : medalhas) {
            try{
                firestore.guardarOuAtualizar("Medalhas",medalha.getDono() , medalhaToMap(medalha));
            }catch (HeadlessException e){
                System.err.println("Error: "+e.getMessage());
                //JOptionPane.showMessageDialog(null, "Error al guar");
            }
            //JOptionPane.showMessageDialog(null, "Sucesso");
        }
    }
    
    public static void addMedalha(Medalha medalha) {
        try{
            firestore.guardarOuAtualizar("Medalhas",medalha.getId() , medalhaToMap(medalha));
        }catch (HeadlessException e){
            System.err.println("Error: "+e.getMessage());
            //JOptionPane.showMessageDialog(null, "Error al guar");
        }
        //JOptionPane.showMessageDialog(null, "Sucesso");
    }
}
