/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import model.Formation;

/**
 *
 * @author MediaMonster
 */
public class CataloguePage {

    public void choose() {
        System.out.println("\nVEUILLEZ FAIRE UN CHOIX\n ");
        System.out.println(" 1- Rechercher une formation  ");
        System.out.println(" 2- Afficher toute les formations ");
        System.out.println(" 3- Retour ");

    }

    public void searchParam() {
        System.out.println("\nCHOISISSEZ VOTRE CRITERE DE RECHERCHE\n");
        System.out.println(" 1- Rechercher par nom  ");
        System.out.println("  2- Rechercher par prix ");
        System.out.println("  3- Retour ");
    }

    public void listFormation(List<Formation> f) {
        System.out.println("\nVOICI LA LISTE DES FORMATIONS : \n");
        for (Object form : f) {
            
            System.out.println(form.toString());
        };
        System.out.println("\n");
        

    }

    public void listFormateur(List f) {
        System.out.println("\nVOICI LA LISTE DES FORMATEURS : ");
        for (Object form : f) {
            System.out.println(form.toString());
        };
        System.out.println("\n");

    }
    
    public void listSession(List f) {
        System.out.println("\nVOICI LA LISTE DES SESSIONS : ");
        for (Object form : f) {
            
            System.out.println(form.toString());
        };
        System.out.println("\n");

    }

    public void searchByName() {
        System.out.println("Entrez une recherche : ");
    }

    public void searchByPrice() {
        System.out.println("Entrez le montant maximum : ");
    }
    
    public void payementSignale() {
        System.out.println("Votre payement est en attente de confirmation");
    }
    
    public void selectInscription(){
        System.out.println("Entrez l'id de l'inscription : ");
    }
    
    
    
    public void print(Object o){
        System.out.println(o.toString() + "\n");
    }

}
