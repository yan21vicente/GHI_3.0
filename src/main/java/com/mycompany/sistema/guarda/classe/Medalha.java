/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema.guarda.classe;

import com.google.api.client.util.Data;
import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author 55329
 */
public class Medalha {
    private String dono;

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }
    
    private String nome;
    private String id;
    private String tipo;
    private String data;

    public Medalha(String dono, String nome, String id, String tipo, String data) {
        this.dono = dono;
        this.nome = nome;
        this.id = id;
        this.tipo = tipo;
        this.data = data;
    }

    public Medalha(String dono, String nome, String tipo) {
        this.dono = dono;
        this.nome = nome;
        this.id = gerarIdAleatorio(); // Gera um ID aleatório
        this.tipo = tipo;
        this.data = getDataAtual(); // Obtém a data atual
    }

    private String gerarIdAleatorio() {
        Random random = new Random();
        int id = random.nextInt(10000); // Gera um número aleatório entre 0 e 9999
        return String.valueOf(id); // Converte o número para String
    }

    private String getDataAtual() {
        LocalDate hoje = LocalDate.now();
        return hoje.toString(); // Retorna a data no formato "YYYY-MM-DD"
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    
    
}
