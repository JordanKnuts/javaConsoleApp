/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.Formation;
import model.Inscription;
import model.Session;
import model.Stagiaire;


/**
 *
 * @author MediaMonster
 */
public interface StagiaireDAO {
  
   Stagiaire subscribe(Stagiaire st);
   void modify(Stagiaire st);
   List<Inscription> getListInscription(int id);
   List<Session> getSession(Formation formation);
   boolean subscribeToSession(int idSession,Stagiaire s);
   void deleteInscription(Inscription i);
   void signalePayment(int i);
  
}
