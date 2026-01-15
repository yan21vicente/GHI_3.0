/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema.guarda.classe.convert;

import com.mycompany.beta.Arquivo;
import com.mycompany.beta.Dados;
import com.mycompany.beta.Medalha_Dado;
import com.mycompany.beta.Util_Dados;
import com.mycompany.sistema.guarda.classe.perfil.Usuario;
import com.mycompany.sistema.guarda.classe.Medalha;
import com.mycompany.sistema.guarda.classe.Validacao;
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
public class UsuarioCovert {
    public static Map<String, Object> formatarListaParaString(Usuario user) {
        Map<String, Object>ids = new HashMap<>();
        
        ids.put("Login", user.getLogin());
        ids.put("Senha", user.getSenha());
        ids.put("Nivel", user.getNivel());
        ids.put("Nome", user.getNome());
        ids.put("Nascimento", user.getNacimento());
        ids.put("Rg", user.getRg());
        ids.put("Sexo", user.getSexo());
        ids.put("Telefone", user.getTelefone());
        ids.put("Uf", user.getUf());
        ids.put("Cpf", user.getCpf());
        ids.put("Emissor", user.getEmissor());
        ids.put("Bairro", user.getBairro());
        ids.put("Rua", user.getRua());
        ids.put("N", user.getN());
        ids.put("Cep", user.getCep());
        ids.put("Cidade", user.getCidade());
        ids.put("Complemento", user.getComplemento());
        ids.put("Estado", user.getEstado());
        ids.put("Patente", user.getPatente());
        ids.put("Divisao", user.getDivi());
        ids.put("Aluno Ativo", user.getAluno_status());
        ids.put("Turma", user.getTurma());
        ids.put("Turno", user.getTurno());

        return ids;
    }
    public static List<String> StrigToDado(List<Usuario> membros){
        List<String> alunos = new ArrayList<>();
                
        for(Usuario user:membros){
            String list = user.getLogin()+ ";" +
            user.getSenha()+ ";" +
            user.getNivel()+ ";" +
            user.getNome() + ";" +
            user.getNacimento() + ";" +
            user.getRg()+ ";" +
            user.getSexo()+ ";" +
            user.getTelefone()+ ";" +
            user.getUf()+ ";" +
            user.getCpf()+ ";" +
            user.getEmissor()+ ";" +
            user.getBairro()+ ";" +
            user.getRua()+ ";" +
            user.getN()+ ";" +
            user.getCep()+ ";" +
            user.getCidade()+ ";" +
            user.getComplemento()+ ";" +
            user.getEstado()+ ";" +
            user.getPatente()+";" +
            user.getDivi()+";" +
            user.getAluno_status()+";" +
            user.getTurma()+ ";" +
            user.getTurno()+";";

            alunos.add(list);
            
            //Medalha_Als.beckap(Medalha_Als.CadMedalhaDados(user.getMedalhas()));
        }
        return alunos;
    }
    
    public static List<Usuario> DadoToStrig(List<String> lista){
        Dados dado = new Arquivo();
        List<Usuario> users = new ArrayList<>();
        //List<Medalha> medalhas = Medalha_Dado.ler();
        List<Medalha> medalhas = MedalhasConvert.DadoToStrig(dado.ler("Medalhas"));
        if(lista == null || lista.isEmpty()){
            return users;
        }
        for(String conteudo:lista){
            // Pular linhas vazias ou nulas
            if(conteudo == null || conteudo.trim().isEmpty()){
                continue;
            }
            
            Usuario user = new Usuario();
            user.setLogin(conteudo.split(";")[0]);
            user.setSenha(conteudo.split(";")[1]);
            user.setNivel(Integer.parseInt(conteudo.split(";")[2]));
            user.setNome(conteudo.split(";")[3]);
            user.setNacimento(conteudo.split(";")[4]);
            user.setRg(conteudo.split(";")[5]);
            user.setSexo(conteudo.split(";")[6]);
            user.setTelefone(conteudo.split(";")[7]);
            user.setUf(conteudo.split(";")[8]);
            user.setCpf(conteudo.split(";")[9]);
            user.setEmissor(conteudo.split(";")[10]);

            //endereço

            user.setBairro(conteudo.split(";")[11]);
            user.setRua(conteudo.split(";")[12]);
            user.setN(conteudo.split(";")[13]);
            user.setCep(conteudo.split(";")[14]);
            user.setCidade(conteudo.split(";")[15]);
            user.setComplemento(conteudo.split(";")[16]);
            user.setEstado(conteudo.split(";")[17]);
            user.setPatente(conteudo.split(";")[18]);
            user.setDivi(conteudo.split(";")[19]);
            user.setAluno_status(conteudo.split(";")[20]);
            if("Sim".equals(user.getAluno_status()))
                user.setTurma(conteudo.split(";")[21]);
            //user.setTurno(conteudo.split(";")[22]);
            users.add(user);
            
            for (Medalha medalha : medalhas){
                if(user.getNome().equals(medalha.getDono()))
                    user.addMedalhas(medalha);
            }
        }
        return users;
    }
    
    public static void atualizar(Usuario user){
        Dados dado = new Arquivo();
        List<Usuario> users = DadoToStrig(dado.ler("Guarda"));
        
        for (int i = 0; i < users.size(); i++) {
            Usuario atual = users.get(i);
            if (atual.equals(user)) {
                users.set(i, user);
            }
        }
        
        dado.escrever(StrigToDado(users), "Guarda");
    }
    
    
    /*public static String formatarListaParaString(Usuario user) {
        String list = user.getLogin()+ ";" +
            user.getSenha()+ ";" +
            user.getNivel()+ ";" +
            user.getNome() + ";" +
            user.getNacimento() + ";" +
            user.getRg()+ ";" +
            user.getSexo()+ ";" +
            user.getDivi()+";"+
            user.getTelefone()+ ";" +
            user.getUf()+ ";" +
            user.getCpf()+ ";" +
            user.getEmissor()+ ";" +
            user.getBairro()+ ";" +
            user.getRua()+ ";" +
            user.getN()+ ";" +
            user.getCep()+ ";" +
            user.getCidade()+ ";" +
            user.getComplemento()+ ";" +
            user.getEstado()+ ";" +
            user.getPatente()+";" +
            user.getAluno_status()+";" +
            user.getTurma()+ ";" +
            user.getTurno()+";";
        return list;
    }*/
}
