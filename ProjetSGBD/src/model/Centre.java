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
public class Centre {

    public Centre() {
    }

    //Afficher Liste
    public static List<Formation> getListFormation() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.getListFormation();
    }

    public List<Status> getListStatus() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.getListStatus();
    }

    public List<Local> getListLocauxLibre(Date debut, Date fin) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.getListLocauxLibre(debut, fin);
    }

    public Formation getFormationById(int id) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.getFormationById(id);
    }

    public Formateur getFormateurById(int idFormateur) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return (Formateur) centreDAO.getUserById(idFormateur);
    }

    public Session getSessionById(int idSession) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.getSessionById(idSession);
    }

    public Local getLocalById(int idLocal) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.getLocalById(idLocal);
    }

    public void cleanDB() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        centreDAO.cleanDB();
    }

    public static List<Inscription> getPayementToConfirm() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.getPayementToConfirm();
    }

    public Inscription getInscriptionById(int i) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.getInscriptionById(i);
    }

    public User ifEmailExist(String email) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.ifEmailExist(email);
    }

    public Formation ifIntituleExist(String intitule) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.ifIntituleExist(intitule);
    }

}
