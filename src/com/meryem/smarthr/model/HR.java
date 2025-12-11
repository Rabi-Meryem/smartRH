package com.meryem.smarthr.model;

public class HR extends user {
    private String departmentManaged;

public HR (String nom, String prenom, String email, String password, String departement, String role, String departmentManaged){
    super (nom,prenom,email,password,departement,role);
    this.departmentManaged= departmentManaged ;
}
//Setters 
public void setDepartmentMnaged(String departmentManaged){
    this.departmentManaged=departmentManaged;
}
//getters
public String getDepartmentManaged(){
    return departmentManaged;
}
    
}
