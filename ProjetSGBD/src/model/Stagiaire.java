/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.dao.AbstractDAOFactory;
import model.dao.CentreDAO;
import model.dao.StagiaireDAO;

/**
 *
 * @author MediaMonster
 */
public class Stagiaire extends User {

    private Status status;

    private List<Inscription> listInscription;
    
    public Stagiaire(int id, Role role, String nom, String prenom, String adresse, String telephone, String email, Status status,String password) {
        super(id,nom, prenom, adresse, telephone, email,password);
        this.status = status;
        

    }

    public Stagiaire() {
    }

    public Stagiaire subscribeStagiaire() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        StagiaireDAO participantDAO = factory.createStagiaireDAO();
        return participantDAO.subscribe(this);
    }

    public void modifyStagiaire() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        StagiaireDAO participantDAO = factory.createStagiaireDAO();
        participantDAO.modify(this);
    }

    public List<Inscription> getListInscriptionSt() {
         AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        StagiaireDAO stagiaireDAO = factory.createStagiaireDAO();
        return listInscription = stagiaireDAO.getListInscription(this.getID());
    }
    
     public boolean subscribeSession(int idSession) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        StagiaireDAO participantDAO = factory.createStagiaireDAO();
        return participantDAO.subscribeToSession(idSession, this);
    }
     
    public void deleteInscription(int idI){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        Inscription i = centreDAO.getInscriptionById(idI);
        StagiaireDAO stagiaireDAO = factory.createStagiaireDAO();
        stagiaireDAO.deleteInscription(i);
    }
     
    

    

    public Stagiaire getStagiaireById(int id) {
        return this;
    }

    public Status getStagiaireStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "STATUS : " + getStagiaireStatus() + super.toString();
    }

    public void signalePayement(int idInscsription) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        StagiaireDAO stagiaireDAO = factory.createStagiaireDAO();
        stagiaireDAO.signalePayment(idInscsription);
    }

}
