package com.mycompany.sistema.extra.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Bucket;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FirebaseStorageDownloader {

    private static final String PROJECT_ID = "sistema-guarda";
    private static final String BUCKET_NAME = PROJECT_ID + ".appspot.com";
    private static final String FIREBASE_FOLDER = "Sistema/imagem"; // Pasta no Firebase Storage
    private static final String FIREBASE_ALTO = "Alunos/Autorizacoes"; // Pasta no Firebase Storage
    private static final String LOCAL_FOLDER = "src/main/java/imagens/"; // Pasta local para salvar as imagens
    private static final String LOCAL_ALTO = "src/main/java/imagens/Autorizacoes/";
    public static void downloadImagesFromFirebase() throws IOException {
        // Autenticando usando credenciais do arquivo JSON
        FileInputStream serviceAccount = new FileInputStream("Imagens-firebase.json");

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId(PROJECT_ID)
                .build()
                .getService();

        // Obtendo o bucket
        Bucket bucket = storage.get(BUCKET_NAME);

        // Verifica se a pasta local existe, se não, cria
        Path localFolderPath = Paths.get(LOCAL_FOLDER);
        if (!Files.exists(localFolderPath)) {
            Files.createDirectory(localFolderPath);
        }

        // Listando objetos na pasta Firebase Storage
        for (Blob blob : bucket.list(Storage.BlobListOption.prefix(FIREBASE_FOLDER + "/")).iterateAll()) {
            String fileName = blob.getName().substring(FIREBASE_FOLDER.length() + 1);
            Path localFilePath = localFolderPath.resolve(fileName);

            try (FileOutputStream outputStream = new FileOutputStream(localFilePath.toFile())) {
                blob.downloadTo(outputStream);
                //System.out.println("Downloaded: " + fileName + " to " + localFilePath.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void downloadAltoFromFirebase() throws IOException {
        // Autenticando usando credenciais do arquivo JSON
        FileInputStream serviceAccount = new FileInputStream("sistema-guarda-firebase.json");

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId(PROJECT_ID)
                .build()
                .getService();

        // Obtendo o bucket
        Bucket bucket = storage.get(BUCKET_NAME);

        // Verifica se a pasta local existe, se não, cria
        Path localFolderPath = Paths.get(LOCAL_ALTO);
        if (!Files.exists(localFolderPath)) {
            Files.createDirectory(localFolderPath);
        }

        // Listando objetos na pasta Firebase Storage
        for (Blob blob : bucket.list(Storage.BlobListOption.prefix(FIREBASE_ALTO + "/")).iterateAll()) {
            String fileName = blob.getName().substring(FIREBASE_ALTO.length() + 1);
            Path localFilePath = localFolderPath.resolve(fileName);

            try (FileOutputStream outputStream = new FileOutputStream(localFilePath.toFile())) {
                blob.downloadTo(outputStream);
                System.out.println("Downloaded: " + fileName + " to " + localFilePath.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
