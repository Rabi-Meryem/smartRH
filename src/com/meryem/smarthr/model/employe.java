package com.meryem.smarthr.model;

public class employe extends user{
    private String position;
    
public employe (String nom, String prenom, String email, String password, String departement,String position){
    super(nom,prenom,email,password,departement,role);
    this.position=position;
}
public String getPosition(){
    return position;
}
public void setPosition(String position){
    this.position=position;
}

    
}
