/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Formation;
import model.Session;
import model.Admin;
import model.Centre;
import model.Formateur;


/**
 *
 * @author MediaMonster
 */
public class AdminPage {

   
    public void choose() {

        

        System.out.println("\nVEUILLEZ FAIRE UN CHOIX\n");
        System.out.println(" 1- Afficher le formateur d'une session  ");
        System.out.println(" 2- Afficher la/les prestation(s) d'un formateur");
        System.out.println(" 3- Gerer les formations ");
        System.out.println(" 4- Gerer les formateurs");
        System.out.println(" 5- Gerer les sessions");
        System.out.println(" 6- Ajouter un status");
        System.out.println(" 7- Ajouter un local");
        System.out.println(" 8- Confirmer payement (" + Centre.getPayementToConfirm().size()+")");
        System.out.println(" 9- Quitter");

        
    }
    
    public void bonjour(){
        System.out.println("\n BONJOUR ADMINISTRATEUR \n");
    }

    public void gererFormation() {
        System.out.println("\nGERER FORMATION \n");
        System.out.println(" 1- Afficher les formations");
        System.out.println(" 2- Ajouter une formation");
        System.out.println(" 3- Modifier une formation");
        System.out.println(" 4- Supprimer une formation");
        System.out.println(" 5- Retour");
    }
    
    public void gererSession() {
        System.out.println("\nGERER SESSION \n");
        System.out.println(" 1- Afficher les sessions");
        System.out.println(" 2- Ajouter une session");
        System.out.println(" 3- Modifier une session");
        System.out.println(" 4- Supprimer une session");
        System.out.println(" 5- Afficher les stagiaires inscrit a une session");
        System.out.println(" 6- Retour");
    }

    public void gererFormateur() {
        System.out.println("\nGERER FORMATEUR \n");
        System.out.println(" 1- Afficher les formateurs");
        System.out.println(" 2- Ajouter un formateur");
        System.out.println(" 3- Modifier les informations d'un formateur");
        System.out.println(" 4- Modifier les formations données par un formateur");
        System.out.println(" 5- Supprimer un formateur");
        System.out.println(" 6- Retour");
    }
    
    public void modifSession(){
        System.out.println("\nQUE SOUHAITEZ-VOUS MODIFIER ?\n");
        System.out.println(" 1- Modifier le formateur");
        System.out.println(" 2- Modifier le local");
    }
    
    public void modifFormationDonnee(){
        System.out.println("\n QUE SOUHAITEZ VOUS FAIRE ?\n");
        System.out.println(" 1- Ajouter une formation");
        System.out.println(" 2- Supprimer une formation");
    }
    
   
    
    public void idSession(){
        System.out.println("Entrez l'id de la session : ");
    }
    
     public void idFormateur(){
         System.out.println("Entrez l'id du formateur : ");
    }
     
      public void idFormation(){
          System.out.println("Entrez l'id de la formation : ");
    }

    public void selectFormationToModify() {
        System.out.println("Entrez l'id de la formation : ");
    }

    public void selectIdFormationToDelete() {
        System.out.println("Entrez l'id de la formation a supprimer : ");
    }

    public void selectIdFormateurToModify() {
        System.out.println("Entrez l'id du formateur : ");
    }

    public void selectIdFormateurToDelete() {
        System.out.println("Entrez l'id du formateur a supprimer : ");
    }
    
    public void selectFormateurForSession(){
        System.out.println("Selectionez l'id du formateur qui donnera cette session  ");
            
    }

    public void modifyIntitule() {
        System.out.println("Entrez le nouvel intitulé de la formation : ");
    }

    public void modifynbParticipantMax() {
        System.out.println("Entrez le nouveau nombre de participant maximum à la formation : ");
    }
    
     public void modifynbParticipantMin() {
        System.out.println("Entrez le nouveau nombre de participant minimum à la formation : ");
    }

    public void modifyPrix() {
        System.out.println("Entrez le nouveau prix de la formation : ");
    }

    public void modifyDuree() {
        System.out.println("Entrez la nouvelle durée de la formation : ");
    }

    public void addIntitule() {
        System.out.println("Entrez l'intitulé de la formation : ");
    }

    public void addNbParticipantMax() {
        System.out.println("Entrez le nombre de participant maximum à la formation : ");
    }
    
    public void addNbParticipantMin() {
        System.out.println("Entrez le nombre de participant minimum à la formation : ");
    }

    public void addPrix() {
        System.out.println("Entrez le prix de la formation : ");
    }

    public void addDuree() {
        System.out.println("Entrez la durée en jours de la formation : ");
    }

    public void modifNom() {
        System.out.println("Entrez un nouveau nom : ");
    }
    
    public void nomStatus(){
        System.out.println("Entrez le nom du nouveau status : ");
    }
    public void reducStatus(){
        System.out.println("Entrez la reduction en pourcent : ");
    }

    public void modifPrenom() {
        System.out.println("Entrez un nouveau prenom : ");
    }

    public void modifAdresse() {
        System.out.println("Entrez un nouveau adresse : ");
    }

    public void modifTel() {
        System.out.println("Entrez un nouveau numero de tel : ");
    }

    public void modifLogin() {
        System.out.println("Entrez un nouveau login : ");
    }

    public void modifPassword() {
        System.out.println("Entrez le nouveau mot de passe : ");
    }

    public void modifMail() {
        System.out.println("Entrez une nouvelle adresse mail : ");
    }
    
     public void selectSessionToModify() {
        System.out.println("Entrez le numéro de la session que vous voulez modifer : ");
    }
     public void selectSession() {
        System.out.println("Entrez le numéro de la session : ");
    }
     
    public void selectFormationForSession(){
        System.out.println("Entrez le numéro de la formation pour laquelle vous souhaitez céer une session");
    }
    
    public void dateDebut(){
        System.out.println("Entrez la date de debut de la formation au format dd/MM/yyyy");
    }
    
    public void local(){
        System.out.println("Selectionez l'id du local ");
            
    }
    

    public void print(Object o) {
        System.out.println(o);
    }

    public void sessionPrevueFormateur() {
        System.out.println("Impossible de le supprimer car il y a des sessions prévue avec ce formateur");
    }

    public void printListSession(Formation fo) {
        for(Session sess : fo.getSession()){
                System.out.println(fo.getIntitule() + "  " +sess + " Places restantes : " + (fo.getNbParticipantMax() - sess.getListInscription().size()));
            }
    }

    public void nomLocal() {
        System.out.println("Entrez le nom du nouveau local : ");
    }

    public void statusAjoute() {
        System.out.println("Le status a bien été ajouté ");
    }

    public void localAjoute() {
        System.out.println("Le local a bien été ajouté ");
    }

    public void selectInscription() {
        System.out.println("Entrez l'id de l'inscription pour laquelle vous souhaitez confirmer le payement ");
    }

    public void formationAjouteeFormateur(Formateur f) {
        System.out.println("La formation a bien été ajoutée a la liste des formation donnée par " + f.getNom() + " " +f.getPrenom());
    }

    public void formationSupprimeeFormateur(Formateur f) {
        System.out.println("La formation a bien été supprimée de la liste des formation donnée par " + f.getNom() + " " +f.getPrenom());
    }

    public void formationNonSupprimee(Formateur f) {
        System.out.println("La formation n'a pas été supprimée de la liste des formation donnée par " + f.getNom() + " " +f.getPrenom());
    }
    
    public void suppressionFormatonFormateur() {
        System.out.println("Souhaiter vous supprimer cette formation des formations données par ce formateur ?  O/N");
    }

    public void formateurSession() {
        System.out.println("Le formateur donnant cette session est : ");
    }
    
}
    

   
    
    
    



