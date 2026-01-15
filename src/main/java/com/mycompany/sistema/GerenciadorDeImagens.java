package com.mycompany.sistema;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JFileChooser;

public class GerenciadorDeImagens {

    public static void moverERenomearImagem(File destino, String novoNome) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione uma imagem para mover");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();

            // Extrair a extensão do arquivo original
            String extensao = "";
            int i = arquivoSelecionado.getName().lastIndexOf('.');
            if (i > 0) {
                extensao = arquivoSelecionado.getName().substring(i);
            }

            // Construa o novo caminho com o nome especificado e a extensão original
            File novoArquivo = new File(destino, novoNome + extensao);

            try {
                // Move e renomeia o arquivo
                Files.move(arquivoSelecionado.toPath(), novoArquivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Imagem movida e renomeada com sucesso: " + novoArquivo.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Erro ao mover e renomear a imagem: " + e.getMessage());
            }
        }
    }
}

