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
public class ErrorPage {

    public void errorChoice() {
        System.out.println("Veuillez entrez un chiffre valable s'il vous plait \n");
    }

    public void noSession() {
        System.out.println("Désolé mais il n'y a pas de session prévue pour cette formation \n");
    }
    
    public void noSessionFormateur(){
        System.out.println("Désolé mais vous n'avez aucune session de formation prévue \n");
    }

    public void noInscription() {
        System.out.println("Désolé mais il n'y a aucune inscription enregistrée \n");
    }

    public void noFormationId() {
        System.out.println("Désolé mais il n'y a pas de formation avec cet id \n");
    }

    public void noFormationName() {
        System.out.println("Désolé mais il n'y a pas de formation avec ce nom \n");
    }
    public void noFormationPriice() {
        System.out.println("Désolé mais il n'y a pas de formation a ce prix \n");
    }

    public void noFormateurId() {
        System.out.println("Désolé mais il n'y a pas de formateur avec cet id \n");
    }

    public void errorFormatDate() {
        System.out.println("Erreur avec le format de la date \n");
    }

    public void errorDateFin() {
        System.out.println("Erreur avec la date de fin \n");
    }
    
    public void nbParticipant(){
        System.out.println("Entrez un nombre valide s'il vous plait \n");
    }
    
    public void noPlace(){
        System.out.println("Il n'y a plus de place pour cette formation \n");
    }
    public void noIdSession(){
        System.out.println("Il n'y a pas de session avec cet ID \n");
    }
    public void noIdFormation(){
        System.out.println("Il n'y a pas de formation avec cet ID \n");
    }
    public void noIdFormateur(){
        System.out.println("Il n'y a pas de formateur avec cet ID \n");
        
    }
    public void dejaInscrit(){
        System.out.println("Vous êtes déjà inscrit à cette session \n");
        
    }
    public void noSessionForFormateur(){
        System.out.println("Ce formateur n'as aucune session programmée \n");
        
    }
    public void noFormateurFormation(){
        System.out.println("Vous n'avez aucun formateur pour cette formation \n");
        
    }

    public void noFormateurLibre() {
        System.out.println("Il n'y a pas de formateur libre \n");
    }

    public void noLocalLibre() {
        System.out.println("Il n'y a pas de local libre \n");
    }

    public void nomStatus() {
        System.out.println("Un status avec ce nom existe déjà \n");
    }

    public void localExist() {
        System.out.println("Un local avec ce nom existe déjà \n");
    }

    public void noPayementToConfirm() {
        System.out.println("Il n'y a pas de payement a confirmer \n");
    }

    public void sessionEnCours() {
        System.out.println("\n Impossible de supprimer cette formation car une session de cette formation en cours \n");
    }
    
    public void emailExist(){
        System.out.println("\n Un utilisateur avec cette adresse mail existe deja ! \n");
    }

    public void intituleExist() {
        System.out.println("\n Une formation avec cet intitulé existe déjà \n");
    }

    

    
    
}
