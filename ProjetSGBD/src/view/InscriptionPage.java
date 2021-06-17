/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import model.Inscription;

/**
 *
 * @author MediaMonster
 */
public class InscriptionPage {

    public void printInscription(Inscription i) {
        if (i != null) {
            System.out.println(i);
        } else {
            System.out.println("Pas d'inscription enregistrée");
        }

    }

    public void printList(List l) {
        if (!l.isEmpty()) {
            for (Object s : l) {
                System.out.println(s.toString());
            }
        } else {
            System.out.println("La liste est vide");
        }
    }

    public void selectAnnulation() {
        System.out.println("Entrez l'id formation que vous souhaitez annuler : ");
    }

    public void selectFormation() {
        System.out.println("Entrez l'id de la formation : ");
    }

    public void selectSession() {
        System.out.println("Entrez le numéro de la session a laquelle vous voulez vous inscrire : ");
    }

    public void stay() {
        System.out.println("Vous êtes encore inscrit aux formations suivantes : ");
    }

}
