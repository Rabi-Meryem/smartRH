package com.meryem.smarthr.service;

import com.meryem.smarthr.model.Contract;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ContractService {

    private final String FILE_PATH = "files/contracts.csv";
    private ExecutorService executor = Executors.newFixedThreadPool(3);

    // ----------- AJOUTER CONTRAT -----------
    public void ajouterContrat(Contract contract) {
        executor.submit(() -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                writer.write(contract.getId() + "," + contract.getEmployeeEmail() + "," +
                             contract.getType() + "," + contract.getStartDate() + "," +
                             contract.getEndDate() + "," + contract.getFileNamePDF());
                writer.newLine();
                System.out.println("Contrat ajouté pour : " + contract.getEmployeeEmail());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    // ----------- LIRE TOUS LES CONTRATS -----------
    public List<Contract> getAllContracts() {
        try {
            return Files.lines(Paths.get(FILE_PATH))
                        .map(Contract::fromCSV)
                        .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    // ----------- SUPPRIMER CONTRAT -----------
    public void supprimerContrat(String id) {
        executor.submit(() -> {
            try {
                List<Contract> updatedList = getAllContracts()
                        .stream()
                        .filter(c -> !c.getId().equals(id))
                        .collect(Collectors.toList());
                saveAll(updatedList);
                System.out.println("Contrat supprimé : " + id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    // ----------- SAUVEGARDER LISTE COMPLÈTE DANS LE FICHIER -----------
    private void saveAll(List<Contract> list) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Contract c : list) {
                writer.write(c.getId() + "," + c.getEmployeeEmail() + "," +
                             c.getType() + "," + c.getStartDate() + "," +
                             c.getEndDate() + "," + c.getFileNamePDF());
                writer.newLine();
            }
        }
    }
}
