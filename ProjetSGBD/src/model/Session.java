/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.List;
import model.dao.AbstractDAOFactory;
import model.dao.AdminDAO;
import model.dao.CentreDAO;


/**
 *
 * @author MediaMonster
 */
public class Session {
    private int idSession;
    private Date dateDebut, dateFin;
    private Local local;
    private Formateur formateur = new Formateur();
    private Formation formation = new Formation();
    private List<Inscription> listInscription;

 
    
    public Session(int idSession, Formateur formateur, Local local, Date dateDebut, Date dateFin) {
        this.formateur = formateur;
        this.local = local;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idSession = idSession;
    }
    

    public Session() {
    }


    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public Local getLocal() {
        return local;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }
    
    public Formateur getFormateur() {
        return formateur;
    }

    public void deleteSession() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        AdminDAO adminDAO = factory.createAdminDAO();
        adminDAO.deleteSession(this);
    }

    public List<Inscription> getListInscription() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        listInscription = centreDAO.getListInscriptionSession(this);
        return listInscription;
    }
    public List<String> getListInscriptionBySession() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.getListInscriptionBySession(this);
        
    }
     public void updateSession() {
       AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        centreDAO.updateSession(this);
    }
     
     public List<Stagiaire> getListParticipants() {
         AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.getListParticipant(this);
    }

    
    
    
    
    @Override
    public String toString() {
        return "\nID :"+this.idSession +"\nDebut : " + getDateDebut() + "  - Fin : " + getDateFin() +"\nLocal : "+getLocal();
    }

    

   
}
