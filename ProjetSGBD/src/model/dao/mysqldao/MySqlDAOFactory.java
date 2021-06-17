/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mysqldao;


import model.dao.AbstractDAOFactory;
import model.dao.AdminDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.CentreDAO;
import model.dao.FormationDAO;
import model.dao.UserDAO;
import model.dao.StagiaireDAO;

/**
 *
 * @author MediaMonster
 */
public class MySqlDAOFactory extends AbstractDAOFactory {

    private static MySqlDAOFactory instance ;
    

    private MySqlDAOFactory(){};
        
    public static MySqlDAOFactory getInstance(){
        if(null == instance )
            instance = new MySqlDAOFactory();
        return instance;
    }
    
     public Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             c = DriverManager.getConnection("jdbc:mysql://localhost:3306/centre_formation?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "carmelo", "carmelo");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySqlDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return c;
    }

    @Override
    public AdminDAO createAdminDAO() {
       return MySqlAdminDAO.getInstance();
    }

    @Override
    public UserDAO createUserDAO() {
        return MySqlUserDAO.getInstance();
    }

    @Override
    public StagiaireDAO createStagiaireDAO() {
        return MySqlStagiaireDAO.getInstance();
    }

    @Override
    public CentreDAO createCentreDAO() {
        return MySqlCentreDAO.getInstance();
    }
    
    
    

    


    
    
    
}
