/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema.guarda.classe;

import com.mycompany.sistema.guarda.classe.perfil.Usuario;

/**
 *
 * @author 55329
 */
public class Aluno extends Usuario{
    private String grupo;

    public Aluno(String grupo, String turma, String turno) {
        this.grupo = grupo;
        this.setTurma(turma);
        this.setTurno(turno);
    }
    
    
    
    public Aluno() {
    }
    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
