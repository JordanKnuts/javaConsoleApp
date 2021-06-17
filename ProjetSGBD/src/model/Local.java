/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.dao.AbstractDAOFactory;
import model.dao.CentreDAO;

/**
 *
 * @author MediaMonster
 */
public class Local {
    private  String nom;
    private  int idLocal;

    public Local(int idLocal,String nom) {
        this.nom = nom;
        this.idLocal = idLocal;
    }

    public Local() {
    }

    

    public String getNom() {
        return nom;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    

    @Override
    public String toString() {
        return "ID:"+ getIdLocal() + "\nNOM " + getNom();
    }

    public boolean addLocal() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.addLocal(this);
    }
    
    
    
    
    
    
    
    
}
