/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;

/**
 *
 * @author MediaMonster
 */
public class HomePage {

    

    public void choose() {
        
        System.out.println("Veuillez faire un choix ");
        System.out.println(" 1- Authentification");
        System.out.println(" 2- Inscription");
        System.out.println(" 3- Rechercher une formation");
        System.out.println(" 4- Quitter");
    }
    
    public void quit(){
        System.out.println("Au revoir  !");
    }
    
}
