package com.meryem.smarthr.service;

import com.meryem.smarthr.model.Contract;
import java.io.*;
import java.util.*;

public class ContractService {
    private List<Contract> contracts = new ArrayList<>();
    private final String FILE_PATH = "contracts.txt"; // fichier pour sauvegarder les contrats

    // Ajouter un contrat
    public void saveContract(Contract contract) throws IOException {
        contracts.add(contract);

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
        writer.write(contract.getId() + ";" + contract.getEmployeeEmail() + ";" + contract.getType() + ";" +
                     contract.getStartDate() + ";" + contract.getEndDate() + ";" + contract.getFileNamePDF());
        writer.newLine();
        writer.close();
    }

    // Lire tous les contrats depuis le fichier
    public List<Contract> loadContracts() throws IOException {
        contracts.clear();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            if (parts.length == 6) {
                Contract contract = new Contract(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                contracts.add(contract);
            }
        }
        reader.close();
        return contracts;
    }
}
