/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.List;
import java.util.Scanner;
import model.Admin;
import view.CataloguePage;

/**
 *
 * @author MediaMonster
 */
public class FormationControl implements ControllerInterface {

    CataloguePage c = new CataloguePage();

    int choiceInt;

    public void afficherListeFormation() {
        co.wash();
        c.choose();
       
        do {
            co.checkEntry("choose");

            choiceInt = sc.nextInt();

            switch (choiceInt) {
                case 1:
                    c.searchParam();
                    switch (sc.nextInt()) {
                        case 1:
                            c.searchByName();
                            String formation = sc.next();
                            List f = Admin.getFormationByName(formation);
                            do {
                                ep.noFormateurId();
                                c.searchByName();
                                formation = sc.next();
                                f = Admin.getFormationByName(formation);

                            } while (f == null);
                            c.listFormation(f);
                            afficherListeFormation();
                        case 2:
                            c.searchByPrice();
                            int price = sc.nextInt();
                            List l = Admin.getFormationByPrice(price);
                            do {
                                c.searchByPrice();
                                price = sc.nextInt();
                                l = Admin.getFormationByPrice(price);
                            } while (l.isEmpty());
                            c.listFormation(l);
                            afficherListeFormation();
                        case 3:
                            co.startPage();
                    }

                case 2:

                    List l = model.getCentre().getListFormation();
                    System.out.println(l);
                    afficherListeFormation();
                case 3:
                    co.startPage();

            }
        } while (choiceInt < 1 || choiceInt > 4);
    }

    
}
