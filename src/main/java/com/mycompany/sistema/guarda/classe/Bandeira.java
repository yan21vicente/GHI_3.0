/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema.guarda.classe;

/**
 *
 * @author 55329
 */
public class Bandeira {
    private String nome;
    private String tipo;
    private String status;

    public Bandeira(String nome, String tipo, String status) {
        this.nome = nome;
        this.tipo = tipo;
        this.status = status;
    }

    public Bandeira() {
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String estado) {
        this.status = estado;
    }
    
}
