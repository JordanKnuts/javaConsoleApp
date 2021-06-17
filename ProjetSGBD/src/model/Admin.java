/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.dao.AbstractDAOFactory;
import model.dao.AdminDAO;

/**
 *
 * @author MediaMonster
 */
public class Admin extends User {
    
    Role role;
    
    public Admin() {
    }
    
    public Admin(int id, String nom, String prenom, String adresse, String telephone, String email, Role role, String password) {
        super(id, nom, prenom, adresse, telephone, email, password);
        this.role = role;
        
    }
    
    public Role getRoleAdmin() {
        return role;
    }
    
    public List<Session> getPrestationFormateur(Formateur f) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        AdminDAO adminDAO = factory.createAdminDAO();
        return adminDAO.getPrestationFormateur(f);
    }
    
    public void confirmPayement(int i) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        AdminDAO adminDAO = factory.createAdminDAO();
        adminDAO.confirmPayement(i);
    }    
    
    @Override
    public String toString() {
        return "ROLE : " + getRole() + "\n" + super.toString();
    }
    
}
