/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;

/**
 *
 * @author MediaMonster
 */
public class FormateurPage {
    public void choose() {

        System.out.println("Veuillez faire un choix ");
        System.out.println(" 1- Afficher la liste no prestations a assurer");
        System.out.println(" 2- Deconnexion");

    }
    
    public void bonjour(){
        System.out.println("\n BONJOUR Formateur \n");
        
    }
    
    public void printPrestation(List l){
        for (Object form : l) {
            System.out.println(form.toString());
        };
    }
}
