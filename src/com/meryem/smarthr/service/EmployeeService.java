/*package com.meryem.smarthr.service;

import com.meryem.smarthr.model.employe;
import java.io.*;
import java.util.*;

public class EmployeeService {
    private List<employe> employees = new ArrayList<>();
    private final String FILE_PATH = "employees.csv"; // fichier pour sauvegarder les employés
}

public void saveEmployee(employe emp) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true)); // 'true' = ajoute à la fin
    writer.write(emp.getNom() + ";" + emp.getPrenom() + ";" + emp.getEmail() + ";" + emp.getDepartement() + ";" + emp.getRole() + ";" + emp.getPassword());
    writer.newLine();
    writer.close();
}
/* */
package com.meryem.smarthr.service;
import com.meryem.smarthr.model.Employe;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;




public class EmployeeService {

    private final String FILE_PATH = "files/employees.csv";

    // ThreadPool pour exécuter les opérations lourdes //ExecutorService et une interface java sert à gérer plusieurs threads sans les créer toi-même.
    //executors st une classe utilitaire dans Java. Elle contient des méthodes statiques pour créer des groupes de threads (thread pools).
   
    private ExecutorService executor = Executors.newFixedThreadPool(3);

    // ----------- AJOUTER EMPLOYÉ (Thread + écriture fichier) ----------
  public void ajouterEmploye(Employe emp) {
    executor.submit(() -> {
        // try-with-resources pour fermer automatiquement le BufferedWriter
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(emp.toString()); // écrire l'objet Employe sous forme de texte
            bw.newLine(); // ajouter une nouvelle ligne après l'employé
            System.out.println("Employé ajouté : " + emp.getNom()); // notification console
        } catch (IOException e) {
            e.printStackTrace(); // affiche l'erreur si l'écriture échoue
        }
    });
}

    // ----------- LIRE TOUS LES EMPLOYÉS (Streams) ----------
    public List<Employe> getAllEmployees() {
        try {
            return Files.lines(Paths.get(FILE_PATH)) // lit les ligne du fichier et le renvoie sous forme de string 
                    .map(Employe::fromCSV)// convertit les ligne en objet 
                    .collect(Collectors.toList());// apres terminer en stocke la sortie dans une liste

// Si le fichier n’existe pas ou qu’il y a un problème de lecture, on ne plante pas le programme.La méthode retourne une liste vide au lieu de tout casser.
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
// modifier un employer 
    public void modifierEmploye(int id, String nom, String prenom, String email, String password, String departement, String position) {
    executor.submit(() -> {
        try {
            List<Employe> list = getAllEmployees();

            List<Employe> updatedList = list.stream()
                    .map(emp -> emp.getId() == id ?
                            new Employe(emp.getNom(), emp.getPrenom(), emp.getEmail(), emp.getPassword(), emp.getDepartement(), emp.getPosition())
                            : emp)
                    .collect(Collectors.toList());

            saveAll(updatedList);

            System.out.println("Modification effectuée !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
}

    // ----------- SUPPRIMER EMPLOYÉ (Streams) ----------
    public void supprimerEmploye(int id) {
        executor.submit(() -> {
            try {
                List<Employe> updatedList = getAllEmployees()
                        .stream()
                        .filter(emp -> emp.getId() != id)
                        .collect(Collectors.toList());

                saveAll(updatedList);

                System.out.println("Employé supprimé !");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // ----------- AFFECTER POSTE / DÉPARTEMENT -----------
   /*  public void affecterPoste(int id, String poste, String departement) {
        modifierEmploye(id, poste, departement);
    }
*/
    // ----------- SAUVEGARDER LISTE COMPLÈTE DANS LE FICHIER -----------
    private void saveAll(List<Employe> list) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        for (Employe e : list) {
            writer.write(e.toString());
            writer.newLine();
        }
        writer.close();
    }
}
