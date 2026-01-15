/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistema;

import com.google.cloud.firestore.CollectionReference;
import com.mycompany.beta.Arquivo;
import com.mycompany.beta.Dados;
import com.mycompany.beta.Util_Dados;
import com.mycompany.sistema.extra.firebase.Conexcion;
import static com.mycompany.sistema.extra.firebase.FirebaseStorageDownloader.downloadAltoFromFirebase;
import static com.mycompany.sistema.extra.firebase.FirebaseStorageDownloader.downloadImagesFromFirebase;
import com.mycompany.sistema.extra.firebase.firestore;
import com.mycompany.sistema.guarda.classe.convert.UsuarioCovert;
import com.mycompany.sistema.guarda.view.TelaInicial;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author 55329
 */
public class Principal {

    public static void main(String[] args) {
        Util_Dados.Escrever("Guarda", "hahajajaj");
        /*Conexcion.conectar();
        TelaInicial frame = new TelaInicial();
        frame.setVisible(true);
        /*try {
            downloadImagesFromFirebase();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
