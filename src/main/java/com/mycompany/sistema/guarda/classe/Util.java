package com.mycompany.sistema.guarda.classe;


import com.mycompany.sistema.guarda.classe.perfil.Usuario;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
  * @author Gilberto Toledo
 */
public class Util {
    public static String Ler(String Caminho){
        String conteudo = "";
        try {
            FileReader arq = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha="";
            try {
                linha = lerArq.readLine();
                while(linha!=null){
                    conteudo += linha+"\n";
                    linha = lerArq.readLine();
                }
                arq.close();
                return conteudo;
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return "";
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return "";
        }
    }
    
    public static List<String> Lerlist(String Caminho){
        List<String> conteudo = new ArrayList();
        try {
            FileReader arq = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha="";
            try {
                linha = lerArq.readLine();
                while(linha!=null){
                    conteudo.add(linha);
                    linha = lerArq.readLine();
                }
                arq.close();
                return conteudo;
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
                return conteudo;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
            return conteudo;
        }
    }
    
    public static boolean Escrever(String Caminho,String Texto){
        try {
            FileWriter arq = new FileWriter(Caminho);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println(Texto);
            gravarArq.close();
            return true;
        }catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static List<Usuario> CadUsuario(List<String> lista){
        List<Usuario> users = new ArrayList<>();
        for(String conteudo:lista){
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
            
            users.add(user);
        }
        
        return users;
    }
}
