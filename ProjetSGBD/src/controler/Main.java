/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;


import model.dao.AbstractDAOFactory;
import model.dao.mysqldao.MySqlDAOFactory;

/**
 *
 * @author MediaMonster
 */
public class Main {
    
    public static void main(String[] args) {
        Controller c = new Controller();
        AbstractDAOFactory.setFactory(MySqlDAOFactory.getInstance());
        c.startPage();
    }


}
