/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author MediaMonster
 */
public class Model {

    Admin admin = new Admin();
    Stagiaire st = new Stagiaire();
    Centre centre = new Centre();
    Formateur formateur = new Formateur();
    Formation formation = new Formation();
    Inscription inscription = new Inscription();
    Session session = new Session();
    User user;
    
    public Model() {
    }

    public Admin getAdmin() {
        return admin;
    }

    public Stagiaire getStagiaire() {
        return st;
    }

    public Centre getCentre() {
        return centre;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public Formation getFormation() {
        return formation;
    }

    public Inscription getInscription() {
        return inscription;
    }

    public Session getSession() {
        return session;
    }
    
    public User getUser(){
        return user;
     }
    
    



}
