/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.dao.AbstractDAOFactory;
import model.dao.AdminDAO;
import model.dao.CentreDAO;

/**
 *
 * @author MediaMonster
 */
public class Formateur extends User {

    private Role role;

    public Formateur(int id, String nom, String prenom, String adresse, String telephone, String email, String login, String password, Role role, Status status) {
        super(id, nom, prenom, adresse, telephone, email, password);
        this.role = role;
        this.status = new Status("enseignant");
    }

    public Formateur(int id, String nom, String prenom, String adresse, String telephone, String email, Role role, String password) {
        super(id, nom, prenom, adresse, telephone, email, password);
        this.role = role;
    }

    public Formateur() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getIdFormateur() {
        return getID();
    }

    public Formateur getFormateur() {
        return this;
    }

    public Formateur getFormateurBySession(Session s) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        AdminDAO adminDAO = factory.createAdminDAO();
        return adminDAO.getFormateurForSession(s);
    }

    public void modifyFormateur() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        AdminDAO adminDAO = factory.createAdminDAO();
        adminDAO.modifyFormateur(this);
    }

    public void addFormateur() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        AdminDAO adminDAO = factory.createAdminDAO();
        adminDAO.addFormateur(this);
    }

    public boolean deleteFormateur() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        AdminDAO adminDAO = factory.createAdminDAO();
        return adminDAO.deleteFormateur(this);
    }

    public List<Formateur> getListFormateur() { //CENTRE QUI A LA LISTE
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        AdminDAO adminDAO = factory.createAdminDAO();
        return adminDAO.getListFormateur();
    }

    public List<Formation> getListFormationByFormateur() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.getListFormationByFormateur(this);

    }

    public void deleteFormationFormateur(Formation formation) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        centreDAO.deleteFormationFormateur(this, formation);

    }
    
     public void ajouterFormationFormateur(Formation formation) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        centreDAO.ajouterFormationFormateur(this, formation);
    }

    @Override
    public String toString() {
        return super.toString();
    }

   

}
