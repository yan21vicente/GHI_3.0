package com.mycompany.sistema;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class EnderecoService {

    public static JSONObject buscarEnderecoPorCep(String cep) throws Exception {
        String apiUrl = "https://viacep.com.br/ws/" + cep + "/json/";

        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        // Converte o resultado para um objeto JSON
        JSONObject enderecoJson = new JSONObject(content.toString());

        // Verifica se o CEP é válido
        if (enderecoJson.has("erro")) {
            throw new Exception("CEP inválido!");
        }

        return enderecoJson;
    }

    public static void preencherEndereco(String cep) {
        try {
            JSONObject enderecoJson = buscarEnderecoPorCep(cep);

            String logradouro = enderecoJson.getString("logradouro");
            String bairro = enderecoJson.getString("bairro");
            String cidade = enderecoJson.getString("localidade");
            String estado = enderecoJson.getString("uf");

            System.out.println("Endereço: " + logradouro);
            System.out.println("Bairro: " + bairro);
            System.out.println("Cidade: " + cidade);
            System.out.println("Estado: " + estado);
        } catch (Exception e) {
            System.out.println("Erro ao buscar o endereço: " + e.getMessage());
        }
    }
    
    
}
