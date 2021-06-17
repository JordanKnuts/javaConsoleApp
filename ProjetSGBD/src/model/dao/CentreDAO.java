/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.Date;
import java.util.List;
import model.Formateur;
import model.Formation;
import model.Inscription;
import model.Local;
import model.Role;
import model.Session;
import model.Stagiaire;
import model.Status;
import model.User;

/**
 *
 * @author MediaMonster
 */
public interface CentreDAO {

    List<Status> getListStatus();

    List<Session> getListSession();

    List<Formation> getListFormation();

    List<Local> getListLocauxLibre(Date debut, Date fin);
    // Status getStatusById(int idStatus);

    Local getLocalById(int Local);
    //  Role getRoleById(int idRole);

    Formation getFormationById(int idFormation);

    User getUserById(int idUser);

    Session getSessionById(int idSession);

    Inscription getInscriptionById(int idInscription);

    List<Formation> getListFormationByFormateur(Formateur f);

    List<Formateur> getListFormateurByFormation(Formation f, Date deb, Date fin);

    void addSession(Session s);

    List<Inscription> getListInscriptionSession(Session s);

    void cleanDB();

    boolean addStatus(Status s);

    List<String> getListInscriptionBySession(Session aThis);

    void updateSession(Session s);

    boolean addLocal(Local l);
    
    List<Inscription> getPayementToConfirm();

    List<Stagiaire> getListParticipant(Session s);

    User ifEmailExist(String email);

    Formation ifIntituleExist(String intitule);

    void deleteFormationFormateur(Formateur formateur, Formation formation);

    void ajouterFormationFormateur(Formateur formateur, Formation formation);
    

    
   
    
    
    
    

}
