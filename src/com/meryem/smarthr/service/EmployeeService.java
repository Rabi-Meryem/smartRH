package com.meryem.smarthr.service;

import com.meryem.smarthr.model.employe;
import java.io.*;
import java.util.*;

public class EmployeeService {
    private List<employe> employees = new ArrayList<>();
    private final String FILE_PATH = "employees.txt"; // fichier pour sauvegarder les employés
}

public void saveEmployee(employe emp) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true)); // 'true' = ajoute à la fin
    writer.write(emp.getNom() + ";" + emp.getPrenom() + ";" + emp.getEmail() + ";" + emp.getDepartement() + ";" + emp.getRole() + ";" + emp.getPassword());
    writer.newLine();
    writer.close();
}
