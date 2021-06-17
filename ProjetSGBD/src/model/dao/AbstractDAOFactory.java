/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.AdminDAO;


/**
 *
 * @author MediaMonster
 */
public abstract class AbstractDAOFactory {
    
    private static AbstractDAOFactory factory;
    
    //Fonction d'appel de cette classe abstraite
    public static AbstractDAOFactory getFactory() {
        return factory;
    }

    public static void setFactory(AbstractDAOFactory factory) {
        AbstractDAOFactory.factory = factory;
    }
    
    public abstract AdminDAO createAdminDAO();
    
    public abstract UserDAO createUserDAO();
    
    public abstract StagiaireDAO createStagiaireDAO();
    
    public  abstract CentreDAO createCentreDAO();
    
   
}
