/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author MediaMonster
 */
public class StagiairePage {

    public void choose() {
        System.out.println("Veuillez faire un choix ");
        System.out.println(" 1- Modifier informations personelles");
        System.out.println(" 2- Inscription à la session d'une formation");
        System.out.println(" 3- Annuler inscription");
        System.out.println(" 4- Consulter liste des inscriptions");
        System.out.println(" 5- Effectuer un payement");
        System.out.println(" 6- Deconnexion");
    }
    
    public void print(Object o) {
        System.out.println(o);
    }
    
    public void name(){
        System.out.println("ENTREZ LES INFORMATIONS SUIVANTE : \n");
        System.out.println("NOM : ");
    }
    
    public void bonjour(){
        System.out.println("\n BONJOUR STAGIAIRE \n");
    }
    
    public void firstname(){
        System.out.println("PRENOM : ");
    }
    
    public void address(){
        System.out.println("ADRESSE : ");
    }
    
    public void mail(){
        System.out.println("MAIL : ");
    }
    public void status(){
        System.out.println("STATUS : ");
    }
    public void password(){
        System.out.println("MOT DE PASSE : ");
    }

    public void modifNom(){
        System.out.println("Entrez un nouveau nom : ");
    }
    
    public void modifPrenom(){
        System.out.println("Entrez un nouveau prenom : ");
    }
    
    public void modifAdresse(){
        System.out.println("Entrez un nouveau adresse : ");
    }
    
    public void modifTel(){
        System.out.println("Entrez un nouveau numero de tel : ");
    }
    
    public void modifLogin(){
        System.out.println("Entrez un nouveau login : ");
    }

    
    public void modifMail(){
        System.out.println("Entrez votre nouvelle adresse mail : ");
    }
    
    public void modifStatus(){
        System.out.println("Selectionnez le numero de votre status : ");
    }
    
    public void modifPassword(){
        System.out.println("Entrez votre nouveau mot de passe : ");
    }
    
}
