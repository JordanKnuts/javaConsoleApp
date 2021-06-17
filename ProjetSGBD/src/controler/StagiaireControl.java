/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import static controler.ControllerInterface.sc;
import java.util.List;
import model.Formation;
import model.Inscription;
import model.Session;
import model.Stagiaire;
import model.Status;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author MediaMonster
 */
public class StagiaireControl implements ControllerInterface {

    private view.StagiairePage sp = new view.StagiairePage();
    private view.InscriptionPage ip = new view.InscriptionPage();
    private view.CataloguePage c = new view.CataloguePage();

    private int choiceInt;

    public void displayInscrption(User u) {
        List<Inscription> l = ((Stagiaire) u).getListInscriptionSt();
        if (l != null) {
            for (Inscription i : l) {
                ip.printInscription(i);
            }
        } else {
            ep.noInscription();
        }
    }

    public void displayInscrptionNP(User u) {
        List<Inscription> l = ((Stagiaire) u).getListInscriptionSt();
        if (l != null) {
            for (Inscription i : l) {
                if (i.isEstPaye() == 0) {
                    ip.printInscription(i);
                }
            }
        } else {
            ep.noInscription();
        }
    }

    public void deleteInscription(int idInscription) {

        model.getStagiaire().deleteInscription(idInscription);
    }

    public void modifyUser(Stagiaire s) {
        sc.nextLine();

        sp.modifNom();
        s.setNom(sc.nextLine());

        sp.modifPrenom();
        s.setPrenom(sc.nextLine());

        sp.modifAdresse();
        s.setAdresse(sc.nextLine());
        sp.modifTel();
        s.setTelephone(sc.nextLine());
        
        String email = "";
        boolean emailExist = false;
       do {
                do {
                    sp.modifMail();
                    email = sc.nextLine();
                } while (!email.matches("^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
                        || email == null
                        || email.trim().isEmpty());
                if (model.getCentre().ifEmailExist(email) != null) {
                    User user = model.getCentre().ifEmailExist(email);
                    if (user.getID() == s.getID()) {
                        emailExist = true;
                    }
                } else {
                    emailExist = true;
                }
                if(model.getCentre().ifEmailExist(email) != null && !emailExist){ep.emailExist();}
            } while (model.getCentre().ifEmailExist(email) != null && !emailExist);
            s.setEmail(email);

        s.setStatus(new Status(userC.getStatusId()));

        sc.nextLine();

        sp.modifLogin();
        s.setLogin(sc.nextLine());

        sp.modifPassword();
        String hashPswrd = BCrypt.hashpw(sc.nextLine(), BCrypt.gensalt());
        s.setPassword(hashPswrd);

        s.modifyStagiaire();

    }

    public void toStagiairePage(User u) {
        sp.choose();
        sc.nextLine();

        choiceInt = 0;
        do {
            co.checkEntry("choose");

            choiceInt = sc.nextInt();

            switch (choiceInt) {
                case 1:

                    modifyUser((Stagiaire) u);
                    toStagiairePage(u);
                case 2:
                    //inscription
                    List l = model.getCentre().getListFormation();
                    c.listFormation(l);
                    int t = 0;
                    do {
                        ip.selectFormation();
                        sc.nextLine();
                        co.checkInt();
                        t = sc.nextInt();
                    } while (model.getCentre().getFormationById(t) == null);

                    Formation form = model.getCentre().getFormationById(t);

                    if (!form.getSession().isEmpty()) {
                        sp.print(form.getSession());
                        int i = 0;
                        do {
                            ip.selectSession();
                            sc.nextLine();
                            co.checkInt();
                            i = sc.nextInt();

                        } while (model.getCentre().getSessionById(i) == null);
                        Session s = model.getCentre().getSessionById(i);

                        if (form.getNbParticipantMax() > s.getListInscription().size()) {
                            if (!((Stagiaire) u).subscribeSession(i)) {
                                ep.dejaInscrit();
                            }
                        } else {
                            ep.noPlace();
                            toStagiairePage(u);
                        }

                    } else {
                        ep.noSession();

                    }
                    toStagiairePage(u);
                case 3:
                    displayInscrption(u);
                    ip.selectAnnulation();
                    sc.nextLine();

                    deleteInscription(sc.nextInt());
                    ip.stay();
                    displayInscrption(u);
                    toStagiairePage(u);
                case 4:
                    displayInscrption(u);
                    toStagiairePage(u);
                case 5:
                    displayInscrptionNP(u);
                    c.selectInscription();
                    model.getStagiaire().signalePayement(sc.nextInt());
                    c.payementSignale();

                case 6:
                    System.exit(0);

                default:
                    ep.errorChoice();
                    sc.nextLine();

            }

        } while (!sc.hasNextInt()
                || choiceInt < 1 || choiceInt > 5);

    }

}
