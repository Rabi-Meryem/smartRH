package com.meryem.smarthr.model;

public class user {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String departement;
    private String role;

public user (String nom , String prenom , String email , String password, String departement ,String role){
    this.nom=nom;
    this.prenom=prenom;
    this.email=email;
    this.password=password;
    this.departement =departement ;
    this.role=role;
} 
    // getters 
    public String getnom() {
    return nom;
}
    public String getprenom(){
        return prenom;
    }
    public String getemail(){
        return email;
    }
    public String getpassword(){
        return password;
    }
    public String getrole(){
        return role;
    }
    public String getdepartement(){
        return departement ;
    }

   //Setters 
   public void setnom(String nom){
    this.nom =nom;
   }
   public void setprenom(String prenom){
    this.prenom=prenom;
   }
   public void setemail(String email){
    this.email =email;
   }
   public void setrole (String role){
    this.role = role;
   }
   public void setdepartement (String departement){
    this.departement =departement;
   }
}
