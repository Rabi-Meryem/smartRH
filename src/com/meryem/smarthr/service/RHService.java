package com.meryem.smarthr.service;

import com.meryem.smarthr.model.HR;
import java.io.*;
import java.util.*;

public class RHService {
    private List<HR> rhList = new ArrayList<>();
    private final String FILE_PATH = "rh.csv"; // fichier pour sauvegarder les RH

    // Ajouter un RH
    public void saveRH(HR rh) throws IOException {
        // Ajouter à la liste en mémoire
        rhList.add(rh);

        // Écrire dans le fichier
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true)); // 'true' = ajoute à la fin
        writer.write(rh.getNom() + ";" + rh.getPrenom() + ";" + rh.getEmail() + ";" +
                     rh.getPassword() + ";" + rh.getRole() + ";" + rh.getDepartement());
        writer.newLine();
        writer.close();
    }

    // Lire tous les RH depuis le fichier
    public List<HR> loadRH() throws IOException {
        rhList.clear();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            if (parts.length == 6) {
                HR rh = new HR(parts[0], parts[1], parts[2], parts[3], parts[5], parts[4], parts[5]);
                rhList.add(rh);
            }
        }
        reader.close();
        return rhList;
    }
}
