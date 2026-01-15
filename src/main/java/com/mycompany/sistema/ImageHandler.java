package com.mycompany.sistema;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageHandler {

    // Função que carrega ou cria a imagem e a define em um JLabel
    public static void setImageToLabel(JLabel label, String imageName) {
        String imagePath = "src/main/java/imagens/" + imageName;
        File imageFile = new File(imagePath);

        BufferedImage image;

        // Verifica se a imagem existe
        if (imageFile.exists()) {
            try {
                // Carrega a imagem da pasta
                image = ImageIO.read(imageFile);
            } catch (IOException e) {
                System.err.println("Erro ao ler a imagem: " + e.getMessage());
                return;
            }
        } else {
            // Cria uma imagem padrão se não existir
            image = createDefaultImage();
            
            try {
                // Salva a imagem criada na pasta
                ImageIO.write(image, "png", imageFile);
            } catch (IOException e) {
                System.err.println("Erro ao salvar a imagem: " + e.getMessage());
                return;
            }
        }

        // Redimensiona a imagem para o tamanho do JLabel
        Image scaledImage = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);

        // Define a imagem redimensionada no JLabel
        label.setIcon(new ImageIcon(scaledImage));
    }
    
    public static void setAlto(JLabel label, String imageName) {
        String imagePath = "src/main/java/imagens/" + imageName;
        File imageFile = new File(imagePath);

        BufferedImage image;

        // Verifica se a imagem existe
        if (imageFile.exists()) {
            try {
                // Carrega a imagem da pasta
                image = ImageIO.read(imageFile);
            } catch (IOException e) {
                System.err.println("Erro ao ler a imagem: " + e.getMessage());
                return;
            }
        } else {
            // Cria uma imagem padrão se não existir
            image = createDefaultImage();
            
            try {
                // Salva a imagem criada na pasta
                ImageIO.write(image, "png", imageFile);
            } catch (IOException e) {
                System.err.println("Erro ao salvar a imagem: " + e.getMessage());
                return;
            }
        }

        // Redimensiona a imagem para o tamanho do JLabel
        Image scaledImage = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);

        // Define a imagem redimensionada no JLabel
        label.setIcon(new ImageIcon(scaledImage));
    }
    
    // Função que cria uma imagem padrão
    private static BufferedImage createDefaultImage() {
        int width = 200;
        int height = 200;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        // Desenha uma imagem simples (por exemplo, um fundo cinza com texto "Imagem Padrão")
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLACK);
        g.drawString("Imagem Padrão", 50, 100);

        g.dispose();
        return image;
    }
}
