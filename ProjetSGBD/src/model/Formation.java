/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import model.dao.AbstractDAOFactory;
import model.dao.AdminDAO;
import model.dao.CentreDAO;
import model.dao.StagiaireDAO;

/**
 *
 * @author MediaMonster
 */
public class Formation {
    private int idFormation;
    private String intitule;
    private int nbParticipantMax, nbParticipantMin, prix, duree;
    private List<Session> listSession;

    public Formation(int id, String intitule, int nbParticipantMax,int nbParticipantMin, int prix, int duree) {
        this.intitule = intitule;
        this.nbParticipantMax = nbParticipantMax;
        this.nbParticipantMin = nbParticipantMin;
        this.prix = prix;
        this.duree = duree;
        this.idFormation = id;
      
    }

    public Formation() {
    }

    public int getIdFormation() {
        return idFormation;
    }
    
    public String getIntitule() {
        return intitule;
    }

    public int getNbParticipantMax() {
        return nbParticipantMax;
    }
    
    public int getNbParticipantMin() {
        return nbParticipantMin;
    }

    public int getPrix() {
        return prix;
    }

    public int getDuree() {
        return duree;
    }

   public void addSession(Session s) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        centreDAO.addSession(s);
    }

    public void setIdFormation(Integer idFormation) {
        this.idFormation = idFormation;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setNbParticipantMax(int nbParticipantMax) {
        this.nbParticipantMax = nbParticipantMax;
    }
     public void setNbParticipantMin(int nbParticipantMin) {
        this.nbParticipantMin = nbParticipantMin;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
    
    

    public void modifyFormation() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        AdminDAO adminDAO = factory.createAdminDAO();
        adminDAO.modifyFormation(this);
    }
    public void addFormation( ) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        AdminDAO adminDAO = factory.createAdminDAO();
        adminDAO.addFormation( this);
    }
    
    public boolean deleteFormation(){
       AbstractDAOFactory factoy = AbstractDAOFactory.getFactory();
       AdminDAO adminDAO = factoy.createAdminDAO();
       return adminDAO.deleteFormation(this);
    }
    
    public List<Session> getSession(){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        StagiaireDAO participantDAO = factory.createStagiaireDAO();
        return participantDAO.getSession(this);
        //Liste de session avec ID de la session !
    }
    
    public List<Session> getListSession(){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        this.listSession = centreDAO.getListSession();
        return this.listSession;
    }
    
    public List<Formateur> getListFormateurForFormation(Date d,Date f){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.getListFormateurByFormation(this,d,f);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.idFormation;
        hash = 13 * hash + Objects.hashCode(this.intitule);
        hash = 13 * hash + this.nbParticipantMax;
        hash = 13 * hash + this.nbParticipantMin;
        hash = 13 * hash + this.prix;
        hash = 13 * hash + this.duree;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Formation other = (Formation) obj;
        if (this.idFormation != other.idFormation) {
            return false;
        }
        if (this.nbParticipantMax != other.nbParticipantMax) {
            return false;
        }
        if (this.nbParticipantMin != other.nbParticipantMin) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
        if (this.duree != other.duree) {
            return false;
        }
        if (!Objects.equals(this.intitule, other.intitule)) {
            return false;
        }
        return true;
    }
    
    
    
    

    @Override
    public String toString() {
        return " \n \n" + getIdFormation() + " - " + getIntitule() + ": " + getPrix() + "€";
    }

}
