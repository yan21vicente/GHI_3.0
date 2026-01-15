package com.mycompany.sistema.extra.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FirebaseStorageUploader {

    private static final String PROJECT_ID = "sistema-guarda";
    private static final String BUCKET_NAME = PROJECT_ID + ".appspot.com";
    private static final String LOCAL_FOLDER = "src/main/java/imagens/"; // Pasta local 'imagem'
    private static final String FIREBASE_FOLDER = "Sistema/imagem"; // Pasta no Firebase Storage

    public static void uploadImagesToFirebase() throws IOException {
        // Autenticando usando credenciais do arquivo JSON
        FileInputStream serviceAccount = new FileInputStream("Imagens-firebase.json");

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId(PROJECT_ID)
                .build()
                .getService();

        // Caminho da pasta local
        Path folderPath = Paths.get(LOCAL_FOLDER);

        // Iterando sobre todos os arquivos na pasta local
        Files.walk(folderPath).filter(Files::isRegularFile).forEach(filePath -> {
            try {
                String fileName = filePath.getFileName().toString();
                String destinationPath = FIREBASE_FOLDER + "/" + fileName;

                // Criando BlobId e BlobInfo para o arquivo
                BlobId blobId = BlobId.of(BUCKET_NAME, destinationPath);
                BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

                // Upload do arquivo para o Firebase Storage
                storage.create(blobInfo, Files.readAllBytes(filePath));
                //System.out.println("Uploaded: " + fileName + " to " + destinationPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
