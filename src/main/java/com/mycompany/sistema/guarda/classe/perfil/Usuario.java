/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema.guarda.classe.perfil;

import com.mycompany.sistema.guarda.classe.perfil.Perfil;
import com.mycompany.sistema.guarda.classe.Medalha;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 55329
 */
public class Usuario extends Perfil {
    private String id;
    private String aluno_status;
    
    private String turma;
    private String turno;
    
    public Usuario() {
        super();
    }
    
    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getAluno_status() {
        return aluno_status;
    }

    public void setAluno_status(String aluno_status) {
        this.aluno_status = aluno_status;
    }
    
    public Usuario(String login,String senha) {
        super(login,senha);
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    //login;
    private int nivel;
    private String patente;
    private List<Medalha> medalhas = new ArrayList<>();
    
    //pessoas
    private String nome;
    private String nacimento;
    private String rg;
    private String sexo;
    private String telefone;
    private String divisao;
    private String uf;
    private String cpf;
    private String emissor;
    
    //endereço
    private String n;
    private String rua;
    private String bairro;
    private String cep;
    private String cidade;
    private String complemento;
    private String estado;

    public List<Medalha> getMedalhas() {
        return medalhas;
    }

    public void setMedalhas(List<Medalha> medalhas) {
        this.medalhas = medalhas;
    }
    
    public void addMedalhas(Medalha medalha) {
        medalhas.add(medalha);
    }
    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getNacimento() {
        return nacimento;
    }

    public void setNacimento(String dado) {
        this.nacimento = dado;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDivi() {
        return divisao;
    }

    public void setDivi(String grupo) {
        this.divisao = grupo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }
    
    public void verificarDivisao(){
        if("Homem".equals(this.sexo))
            this.setDivi("Nascional");
        else
            this.setDivi("Estadual");
    }
    
    public void verificarpatente(){
        switch (nivel) {
            case 0:
                setPatente("Recruta");
                break;
            case 1:
                setPatente("Soldado");
                break;
            case 2:
                setPatente("Cabo");
                break;
            case 3:
                setPatente("3 Sargento");
                break;
            case 4:
                setPatente("2 Sargento");
                break;
            case 5:
                setPatente("1 Sargento");
                break;
            case 6:
                setPatente("Sub Tenente");
                break;
            case 7:
                setPatente("2 Tenente");
                break;
            case 8:
                setPatente("1 Tenente");
                break;
            case 9:
                setPatente("Capitão");
                break;
            case 10:
                setPatente("Major");
                break;
            case 11:
                setPatente("Tenente Coronel");
                break;
            case 12:
                setPatente("Coronel");
                break;
            
        }
    }
    
    @Override
    public boolean[] alt() {
        boolean[] alt = new boolean[5];
        if(nivel <= 1){
            alt[0] = true;
            alt[1] = false;
            alt[2] = false;
            alt[3] = false;
            alt[4] = true;
        }else if(nivel <= 2){
            alt[0] = true;
            alt[1] = true;
            alt[2] = false;
            alt[3] = false;
            alt[4] = true;
        }else{
            alt[0] = true;
            alt[1] = true;
            alt[2] = true;
            alt[3] = false;
            for (Medalha medalha : medalhas) {
                if("Conselho".equals(medalha.getNome()))
                    alt[3] = true;
                
            }
            alt[4] = true;
        }
        
        /*
        for (Medalha medalha : getMedalhas()) {
            if("Conselho".equals(medalha.getNome())){
                alt[4] = true;
            }
        }
        */
        return alt;
    }
}
