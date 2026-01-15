/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.beta;

import com.mycompany.sistema.guarda.classe.Aluno;
import java.util.List;

/**
 *
 * @author 55329
 */
public interface Dados {
    List<String> ler(String tipo);
    void escrever(List<String> dados,String tipo);
}
