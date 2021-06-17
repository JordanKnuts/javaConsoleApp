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
public class AuthentificationPage {

    public void login() {
        System.out.println("\nLogin : ");
    }

    public void password() {
        System.out.println("Mot de passe : ");
    }
    
    public void error(){
        System.out.println("Login ou mot de passe incorrect !");
    }
    
    public void inscriptionOK(){
        System.out.println("Bravo ! Vous êtes inscrit ! ");
    }
}
