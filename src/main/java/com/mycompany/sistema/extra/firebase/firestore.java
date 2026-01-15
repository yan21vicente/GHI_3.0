package com.mycompany.sistema.extra.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.cloud.firestore.Precondition;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class firestore {
    static Firestore db;

    static {
        db = FirestoreClient.getFirestore();
    }

    public static boolean guardar(String coleccion, String documento, Map<String, Object> data) {
        try {
            DocumentReference docRef = db.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Guardado corretamente: " + result.get().getUpdateTime());
            return true;
        } catch (Exception e) {
            System.out.println("Error ao guardar: " + e.getMessage());
        }
        return false;
    }

    public static boolean atualizar(String coleccion, String documento, Map<String, Object> data) {
        try {
            DocumentReference docRef = db.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.update(data);
            System.out.println("Atualizado corretamente: " + result.get().getUpdateTime());
            return true;
        } catch (Exception e) {
            System.out.println("Error ao atualizar: " + e.getMessage());
        }
        return false;
    }

    public static boolean eliminar(String coleccion, String documento) {
        try {
            DocumentReference docRef = db.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.delete(Precondition.NONE);
            System.out.println("Eliminado corretamente: " + result.get().getUpdateTime());
            return true;
        } catch (Exception e) {
            System.out.println("Error ao eliminar: " + e.getMessage());
        }
        return false;
    }
    
    public static List<String> lerDadosColunaFormatados(String colecao, List<String> ids) {
        List<String> resultado = new ArrayList<>();
        CollectionReference collection = db.collection(colecao);
        
        // Obtenha todos os documentos da coleção
        ApiFuture<QuerySnapshot> future = collection.get();
        
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (DocumentSnapshot document : documents) {
                List<String> datos = new ArrayList<>();
                for(String campo:ids){
                    if (document.contains(campo)) {
                        Object value = document.get(campo);
                        if (value != null) {
                            // Converta o valor para string e adicione ao resultado
                            datos.add(value.toString());
                        }else{
                            datos.add("  ");
                        }
                    } else {
                        System.out.println("Campo não encontrado em: " + document.getId());
                    }
                }
                resultado.add(formatarListaParaString(datos));
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Erro ao ler documentos: " + e.getMessage());
        }

        return resultado;
    }

    public static String formatarListaParaString(List<String> lista) {
        // Concatena os elementos da lista em uma string separada por ";"
        return String.join(";", lista) + ";";
    }

    public static Object collection(String bandeiras) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static boolean guardarOuAtualizar(String coleccion, String documento, Map<String, Object> data) {
        //Carregamento tela 09/09/2024
        
        try {
            DocumentReference docRef = db.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.set(data, SetOptions.merge());
            System.out.println("Guardado ou atualizado corretamente: " + result.get().getUpdateTime());
            return true;
        } catch (Exception e) {
            System.out.println("Error ao guardar ou atualizar: " + e.getMessage());
        }
        return false;
    }
    
    public static boolean limparColecao(String colecao) {
        try {
            CollectionReference collectionRef = db.collection(colecao);
            ApiFuture<QuerySnapshot> future = collectionRef.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for (DocumentSnapshot document : documents) {
                document.getReference().delete();
            }

            System.out.println("Coleção " + colecao + " limpa com sucesso.");
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao limpar coleção: " + e.getMessage());
            return false;
        }
    }

    public static List<String> lerDados(String colecao, String documento, List<String> ids) {
        List<String> datos = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore(); // Obtenha a instância do Firestore

        try {
            DocumentReference docRef = db.collection(colecao).document(documento);
            DocumentSnapshot document = docRef.get().get(); // Recupera o documento

            for (String campo : ids) {
                if (document.contains(campo)) {
                    Object value = document.get(campo);
                    if (value != null) {
                        // Converta o valor para string e adicione ao resultado
                        datos.add(value.toString());
                    } else {
                        datos.add(""); // Adiciona uma string vazia se o valor for null
                    }
                } else {
                    System.out.println("Campo não encontrado em: " + document.getId());
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Erro ao ler documentos: " + e.getMessage());
        } catch (FirestoreException e) {
            System.err.println("Erro no Firestore: " + e.getMessage());
        }

        return datos;
}
}
