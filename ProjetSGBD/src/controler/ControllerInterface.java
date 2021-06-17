/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.Scanner;
import model.Model;
import view.AdminPage;
import view.AuthentificationPage;
import view.CataloguePage;
import view.ErrorPage;
import view.FormateurPage;
import view.HomePage;
import view.StagiairePage;

/**
 *
 * @author MediaMonster
 */
public interface ControllerInterface {

    Scanner sc = new Scanner(System.in);
    Model model = new Model();
    Controller co = new Controller();
    AdminControl adminC = new AdminControl();
    FormationControl formationC = new FormationControl();
    StagiaireControl stagiaireC = new StagiaireControl();
    UserControl userC = new UserControl();
    FormateurControl formateurC = new FormateurControl();
    ErrorPage ep = new ErrorPage();
    HomePage hp = new HomePage();
    AdminPage ap = new AdminPage();
    StagiairePage sp = new StagiairePage();
    FormateurPage fp = new FormateurPage();
    CataloguePage cp = new CataloguePage();
    AuthentificationPage autp = new AuthentificationPage();


}
