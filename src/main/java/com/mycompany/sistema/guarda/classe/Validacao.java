package com.mycompany.sistema.guarda.classe;

import static com.mycompany.sistema.EnderecoService.buscarEnderecoPorCep;
import com.mycompany.sistema.guarda.classe.perfil.Usuario;
import com.mycompany.sistema.guarda.classe.Medalha;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.json.JSONObject;


/**
  * @author Gilberto Toledo
 */
public class Validacao {
    public static boolean verificarConselho(Usuario user){
        for (Medalha medalha : user.getMedalhas()) {
            if("Conselho".equals(medalha.getNome())){
                return true;
            }
        }
        return false;
    }
    public static int verfNivel(String nivel){
        if("".equals(nivel)){
            return 0;
        }
        return Integer.parseInt(nivel);
    }
    public static String verificar(String dado){
        if("".equals(dado)){
            return "***";
        }else{
            return dado;
        }
    }
    public static String verificarIO(String dado) throws IOException {
        if (dado == null) {
            throw new IOException("O dado não pode ser nulo");
        }
        return dado;
    }
    public static int Idade(String data){
        int idade = 0;
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String hoje = dataAtual.format(formatter);
        
            int diaAtual,mesAtual,anoAtual;
            diaAtual = Integer.parseInt(hoje.split("/")[0]);
            mesAtual = Integer.parseInt(hoje.split("/")[1]);
            anoAtual = Integer.parseInt(hoje.split("/")[2]);

            int dia,mes,ano;
            dia = Integer.parseInt(data.split("/")[0]);
            mes = Integer.parseInt(data.split("/")[1]);
            ano = Integer.parseInt(data.split("/")[2]);
            
            if(anoAtual>ano){
                if(mesAtual>mes){
                    idade = anoAtual - ano;
                }else if(mesAtual<mes){
                    idade = anoAtual - ano - 1;
                }else{
                    if(diaAtual>dia){
                        idade = anoAtual - ano - 1;
                    }else{
                        idade = anoAtual - ano;
                    }
                }
            }
            return idade;
    }
    public static boolean validarNascimento(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int idade = Validacao.Idade(data);
        if(idade<0 || idade>100)
            return false;
        try {
            LocalDate.parse(data, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public static boolean validarCPF(String cpf) {
        // Remove pontos e traços
        cpf = cpf.replaceAll("\\D", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador >= 10) {
            primeiroDigitoVerificador = 0;
        }

        // Verifica o primeiro dígito verificador
        if (primeiroDigitoVerificador != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador >= 10) {
            segundoDigitoVerificador = 0;
        }

        // Verifica o segundo dígito verificador
        return segundoDigitoVerificador == Character.getNumericValue(cpf.charAt(10));
    }
    
    
    public boolean validarEndereco(Usuario user){
        try {
            JSONObject enderecoJson = buscarEnderecoPorCep(user.getCep());

            String logradouro = enderecoJson.getString("logradouro");
            String bairro = enderecoJson.getString("bairro");
            String cidade = enderecoJson.getString("localidade");
            String estado = enderecoJson.getString("uf");

            if(user.getRua().equals(logradouro) && user.getBairro().equals(bairro) && user.getCidade().equals(cidade) && user.getEstado().equals(bairro) )
                return true;
        } catch (Exception e) {
            System.out.println("Erro ao buscar o endereço: " + e.getMessage());
        }
        return false;
    }
    
    public boolean validarIgualidade(String txt1,String txt2){
        return (txt1.equals(txt2));
    }
}

