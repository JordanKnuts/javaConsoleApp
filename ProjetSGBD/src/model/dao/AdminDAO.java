/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.Formateur;
import model.Formation;
import model.Inscription;
import model.Session;

/**
 *
 * @author MediaMonster
 */
public interface AdminDAO {
    
    List<Formation> getFormationByName(String formation);
    List<Formation> getFormationByPrice(int maxPrice);
    Formateur getFormateurForSession(Session s);
    List<Session> getPrestationFormateur(Formateur f);
    void modifyFormation(Formation f);
    void addFormation( Formation f);
    boolean deleteFormation(Formation f);
    List<Formateur> getListFormateur();
    void modifyFormateur(Formateur f);
    void addFormateur(Formateur f);
    boolean deleteFormateur(Formateur f);
    void modifySession(Session s);
    void deleteSession(Session s);
    void confirmPayement(int i );
    
    
    
    
   
}
