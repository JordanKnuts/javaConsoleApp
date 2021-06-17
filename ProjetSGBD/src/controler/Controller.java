/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.awt.AWTException;
import java.awt.Robot;
import model.Admin;
import model.Formateur;
import model.Stagiaire;
import model.User;
import static controler.ControllerInterface.*;

/**
 *
 * @author MediaMonster
 */
public class Controller {//implements ControllerInterface {

    public void wash() {
        try {
            Robot pressbot = new Robot();
            pressbot.keyPress(17); // Holds CTRL key.
            pressbot.keyPress(76); // Holds L key.
            pressbot.keyRelease(17); // Releases CTRL key.
            pressbot.keyRelease(76); // Releases L key.
        } catch (AWTException ex) {
            System.out.println("\n\n\n\n\n\n");
        }
    }

    public void checkEntry(String msg) {
        if (!sc.hasNextInt()) {
            do {
                ep.errorChoice();
                sc.nextLine();
                switch(msg){
                    case "choose":
                        hp.choose();
                        break;

                }
            } while (!sc.hasNextInt());
        }
    }
    
    public void checkInt(int i) {
        if (!sc.hasNextInt()||sc.nextInt()>i) {
            do {
                ep.errorChoice();
                sc.nextLine();
            } while (!sc.hasNextInt()||sc.nextInt()>i);
        }
    }
    public void checkInt() {
        if (!sc.hasNextInt()) {
            do {
                ep.errorChoice();
                sc.nextLine();
            } while (!sc.hasNextInt());
        }
    }

    private int choiceInt;

    public void startPage() {
        cleanDB();
        
        User u = null;
        hp.choose();
        do {
           checkEntry("choose");

            choiceInt = sc.nextInt();

            switch (choiceInt) {
                case 1:

                    u = userC.authentificationUser();
                    if (u instanceof Stagiaire) {
                        wash();
                        sp.bonjour();
                        stagiaireC.toStagiairePage(u);
                    } else if (u instanceof Admin) {
                        wash();
                        ap.bonjour();
                        adminC.toAdminPage(u);
                    } else if (u instanceof Formateur) {
                        wash();
                        fp.bonjour();
                        formateurC.toFormateurPage(u);
                    }
                    break;
                case 2:
                    sc.nextLine();
                    wash();
                    userC.inscriptionUser();
                    
                    autp.inscriptionOK();
                    sp.bonjour();
                    stagiaireC.toStagiairePage(u);
                case 3:
                    formationC.afficherListeFormation();
                    if (u instanceof Stagiaire) {
                        stagiaireC.toStagiairePage(u);
                    } else if (u instanceof Admin) {
                        adminC.toAdminPage(u);
                    } else if (u instanceof Formateur) {
                        formateurC.toFormateurPage(u);
                    }
                    break;
                case 4:
                   
                    hp.quit();
                    
                    break;
                default:
                    ep.errorChoice();
                    sc.nextLine();
                    break;
            }

        } while (choiceInt < 1 || choiceInt > 4);
    }

    private void cleanDB() {
        model.getCentre().cleanDB();
    }

}
