/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.List;
import model.Formateur;
import model.Session;
import model.User;
import view.FormateurPage;

/**
 *
 * @author MediaMonster
 */
public class FormateurControl implements ControllerInterface {

    FormateurPage fp = new FormateurPage();
    int choiceInt;

    void toFormateurPage(User u) {
        
        fp.choose();
        sc.nextLine();
        choiceInt = 0;
        do {
            co.checkEntry("choose");
            switch (choiceInt) {
                case 1:
                    //affichier prestations
                    List<Session> s = model.getAdmin().getPrestationFormateur((Formateur) u);
                    if (!s.isEmpty()) {
                        for (Session form : s) {
                            ap.print(form.toString());
                        }

                    } else {
                        ep.noSessionFormateur();

                    }
                    toFormateurPage(u);
                    break;
                case 2:
                    co.startPage();
                    break;
                default:
                    ep.errorChoice();
                    sc.nextLine();

            }
        } while (!sc.hasNextInt() || choiceInt < 1 || choiceInt > 5);

    }

    
}
