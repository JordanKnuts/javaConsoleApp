/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.dao.AbstractDAOFactory;
import model.dao.AdminDAO;
import model.dao.UserDAO;

/**
 *
 * @author MediaMonster
 */
public abstract class User {
     
   
    private String nom, prenom, telephone, adresse, email, login, password;
            Role role;
            Status status;
            
    private int IdUser;
    
    public User(){
    }

    public User(int id,String nom, String prenom, String adresse,String telephone, String email,String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.IdUser = id;
        this.password = password;

    }

    public User(String nom, String prenom, String telephone, String adresse, String email, String login, String password, Role role, Status status) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
        
        
    }
    
    

    public static User getConnect(String login) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        UserDAO userDAO = factory.createUserDAO();
        return userDAO.getConnected(login);
    }
    
    public  List<Formation> getListFormation(){
       return Centre.getListFormation();
    }
    
     public static List<Formation> getFormationByName(String formation){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        AdminDAO adminDAO = factory.createAdminDAO();
        return adminDAO.getFormationByName(formation);
    }
     public static List<Formation> getFormationByPrice(int priceMax){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        AdminDAO adminDAO = factory.createAdminDAO();
        return adminDAO.getFormationByPrice(priceMax);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }
    

    public int getID(){
        return IdUser;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    

    
    @Override
    public String toString() {
        return  "ID : "+ getID() +"\n" +"NOM : " + getNom() + "\n" + "PRENOM : " + getPrenom() + "\n"
                + "ADRESSE : " + getAdresse() + "\n" + "MAIL : " + getEmail() + "\n \n";

    }

}
