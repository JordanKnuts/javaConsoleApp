/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import static controler.ControllerInterface.sc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.Admin;
import model.Formateur;
import model.Formation;
import model.Inscription;
import model.Local;
import model.Session;
import model.Status;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import view.AdminPage;
import view.CataloguePage;
import view.InscriptionPage;
import view.StagiairePage;

/**
 *
 * @author MediaMonster
 */
public class AdminControl implements ControllerInterface {

    private StagiairePage sp = new StagiairePage();
    private AdminPage ap = new AdminPage();
    private CataloguePage c = new CataloguePage();
    private InscriptionPage ip = new InscriptionPage();

    private int choiceInt;

    public void displaySession() {
        List<Formation> l = model.getCentre().getListFormation();
        int t = 0;
        c.listFormation(l);
        do {
            ip.selectFormation();
            sc.nextLine();
            co.checkInt();
            t = sc.nextInt();

        } while (model.getCentre().getFormationById(t) == null);
        Formation fo = model.getCentre().getFormationById(t);

        if (!fo.getSession().isEmpty()) {
            ap.printListSession(fo);

        } else {
            ep.noSession();

        }
    }

    public void modifyFormation(Formation f) {
        sc.nextLine();

        String intitule = "";
        Formation form = null;
        boolean intituleExist = false;
        do {
            do {
                ap.modifyIntitule();
                intitule = sc.nextLine();
            } while (intitule == null || intitule.trim().isEmpty());
            if (model.getCentre().ifIntituleExist(intitule) != null) {
                form = model.getCentre().ifIntituleExist(intitule);
                if (form.getIdFormation() == f.getIdFormation()) {
                    intituleExist = true;
                }
            } else {
                intituleExist = true;
            }
            if (model.getCentre().ifIntituleExist(intitule) != null && !intituleExist) {
                ep.intituleExist();
            }
        } while (model.getCentre().ifIntituleExist(intitule) != null && !intituleExist);
        f.setIntitule(intitule);

        ap.modifynbParticipantMax();
        f.setNbParticipantMax(sc.nextInt());

        ap.modifynbParticipantMin();
        f.setNbParticipantMin(sc.nextInt());

        ap.modifyPrix();
        f.setPrix(sc.nextInt());

        ap.modifyDuree();
        f.setDuree(sc.nextInt());

        f.modifyFormation();
    }

    public void modifyFormateur(Formateur f) {
        sc.nextLine();

        ap.modifNom();
        f.setNom(sc.nextLine());

        ap.modifPrenom();
        f.setPrenom(sc.nextLine());

        ap.modifAdresse();
        f.setAdresse(sc.nextLine());

        ap.modifTel();
        f.setTelephone(sc.nextLine());

        String email = "";
        boolean emailExist = false;
        do {
            do {
                ap.modifMail();
                email = sc.nextLine();
            } while (!email.matches("^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
                    || email == null
                    || email.trim().isEmpty());
            if (model.getCentre().ifEmailExist(email) != null) {
                User user = model.getCentre().ifEmailExist(email);
                if (user.getID() == f.getIdFormateur()) {
                    emailExist = true;
                }
            } else {
                emailExist = true;
            }
            if (model.getCentre().ifEmailExist(email) != null && !emailExist) {
                ep.emailExist();
            }
        } while (model.getCentre().ifEmailExist(email) != null && !emailExist);
        f.setEmail(email);

        ap.modifLogin();
        f.setLogin(sc.nextLine());

        ap.modifPassword();
        f.setPassword(sc.nextLine());

        f.modifyFormateur();
    }

    private void modifySession() {

        do {

            List<Formation> l = model.getCentre().getListFormation();
            int t = 0;
            c.listFormation(l);
            do {
                ip.selectFormation();
                sc.nextLine();
                co.checkInt();
                t = sc.nextInt();

            } while (model.getCentre().getFormationById(t) == null);
            Formation fo = model.getCentre().getFormationById(t);
            Session s = new Session();
            if (!fo.getSession().isEmpty()) {
                ap.print(fo.getSession());
                int it = 0;
                do {
                    ap.selectSession();
                    co.checkInt();
                    it = sc.nextInt();
                } while (null == model.getCentre().getSessionById(it));

                s = model.getCentre().getSessionById(it);

            } else {
                ep.noSession();

            }
            ap.modifSession();
            sc.nextLine();

            choiceInt = sc.nextInt();

            switch (choiceInt) {
                case 1:

                    List<Formateur> lf = fo.getListFormateurForFormation(s.getDateDebut(), s.getDateFin());
                    if (lf.isEmpty()) {
                        ep.noFormateurLibre();
                        toAdminPage(model.getAdmin());
                    }
                    ap.print(lf);
                    ap.selectFormateurForSession();
                    s.setFormateur(model.getCentre().getFormateurById(sc.nextInt()));
                    s.updateSession();
                    sc.nextLine();

                case 2:
                    List<Local> lc = model.getCentre().getListLocauxLibre(s.getDateDebut(), s.getDateFin());
                    if (lc.isEmpty()) {
                        ep.noLocalLibre();
                        toAdminPage(model.getAdmin());
                    }
                    ap.print(model.getCentre().getListLocauxLibre(s.getDateDebut(), s.getDateFin()));
                    ap.local();
                    s.setLocal(model.getCentre().getLocalById(sc.nextInt()));
                    s.updateSession();
                    sc.nextLine();

            }

        } while (choiceInt < 1 || choiceInt > 2);

    }

    public void addFormateur() {
        Formateur f = new Formateur();
        sc.nextLine();

        sp.modifNom();
        f.setNom(sc.nextLine());

        sp.modifPrenom();
        f.setPrenom(sc.nextLine());

        sp.modifAdresse();
        f.setAdresse(sc.nextLine());

        sp.modifTel();
        f.setTelephone(sc.nextLine());
        String email = "";
        do {
            do {
                sp.modifMail();
                email = sc.nextLine();
            } while (!email.matches("^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
                    || email == null
                    || email.trim().isEmpty());
        } while (model.getCentre().ifEmailExist(email) != null);
        f.setEmail(sc.nextLine());

        sp.modifLogin();
        f.setLogin(sc.nextLine());

        sp.modifPassword();
        String hashPswrd = BCrypt.hashpw(sc.nextLine(), BCrypt.gensalt());
        f.setPassword(hashPswrd);

        f.addFormateur();
    }

    public void addFormation() {
        Formation f = new Formation();
        sc.nextLine();

        ap.addIntitule();
        f.setIntitule(sc.nextLine());

        ap.addNbParticipantMax();

        co.checkInt(50);
        int max = sc.nextInt();
        f.setNbParticipantMax(max);

        ap.addNbParticipantMin();

        co.checkInt(max);

        f.setNbParticipantMin(sc.nextInt());

        ap.addPrix();
        f.setPrix(sc.nextInt());

        ap.addDuree();
        f.setDuree(sc.nextInt());

        f.addFormation();
    }

    public Session addSession(Formation fo) {
        sc.nextLine();
        Session s = new Session();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String deb = null;
        Date debut = null;
        Date fin = null;

        ap.dateDebut();
        deb = sc.nextLine();

        try {
            debut = sdf.parse(deb);
            if (debut.before(new Date())) {
                addSession(fo);

            }
        } catch (ParseException ex) {

            ep.errorFormatDate();
            addSession(fo);
        }

        s.setDateDebut(debut);

        Calendar c = Calendar.getInstance();
        c.setTime(debut);
        c.add(Calendar.DAY_OF_MONTH, fo.getDuree());

        fin = c.getTime();
        s.setDateFin(fin);

        s.setFormation(fo);
        sc.nextLine();
        //SELECTIONNER FORMATEUR LIBRE
        if (fo.getListFormateurForFormation(debut, fin).isEmpty()) {
            ep.noFormateurFormation();
            CRUDSession();
        } else {
            ap.selectFormateurForSession();
            ap.print(fo.getListFormateurForFormation(debut, fin));
            s.setFormateur(model.getCentre().getFormateurById(sc.nextInt()));
            sc.nextLine();

            ap.print(model.getCentre().getListLocauxLibre(debut, fin));
            Local local = null;
            do {
                ap.local();
                local = model.getCentre().getLocalById(sc.nextInt());
            } while (local == null);
            s.setLocal(local);

        }

        return s;
    }

    public void ajoutStatus() {
        Status s = new Status();
        sc.nextLine();
        ap.nomStatus();
        s.setNomStatus(sc.nextLine());
        ap.reducStatus();
        co.checkInt(100);
        int red = sc.nextInt();
        s.setReduction(red);
        if (s.addStatus()) {
            ap.statusAjoute();
        } else {
            ep.nomStatus();
        }
        sc.nextLine();
    }

    public void ajoutLocal() {
        Local l = new Local();
        sc.nextLine();
        ap.nomLocal();
        l.setNom(sc.nextLine());
        if (l.addLocal()) {
            ap.localAjoute();
        } else {
            ep.localExist();
        }

    }

    public void CRUDFormation() {

        ap.gererFormation();

        do {
            checkEntry("gererFormation");

            choiceInt = sc.nextInt();

            switch (choiceInt) {
                case 1:
                    //afficher
                    List l = model.getCentre().getListFormation();
                    c.listFormation(l);
                    toAdminPage(model.getAdmin());
                    break;
                case 2:
                    //ajouter
                    addFormation();
                    toAdminPage(model.getAdmin());
                    break;
                case 3:
                    //modifier formation
                    List list = model.getCentre().getListFormation();
                    c.listFormation(list);
                    Formation form = null;
                    ap.selectFormationToModify();
                    co.checkInt();

                    form = model.getCentre().getFormationById(sc.nextInt());

                    do {
                        sc.nextLine();
                        ep.noFormationId();
                        c.listFormation(list);
                        co.checkInt();
                        ap.selectFormationToModify();
                        form = model.getCentre().getFormationById(sc.nextInt());

                    } while (form == null);
                    modifyFormation(form);
                    toAdminPage(model.getAdmin());
                    break;
                case 4:
                    //supprimer formation 
                    List listF = model.getCentre().getListFormation();
                    c.listFormation(listF);
                    ap.selectIdFormationToDelete();
                    co.checkInt();
                    Formation forma = model.getCentre().getFormationById(sc.nextInt());
                    if (!forma.deleteFormation()) {
                        ep.sessionEnCours();
                    }
                    toAdminPage(model.getAdmin());
                    break;
                case 5:
                    toAdminPage(model.getAdmin());
                    break;
                default:
                    ep.errorChoice();
                    sc.nextLine();

            }

        } while (choiceInt < 1 || choiceInt > 4);

    }

    public void CRUDSession() {

        ap.gererSession();

        do {

            checkEntry("gererSession");

            choiceInt = sc.nextInt();

            switch (choiceInt) {
                case 1:
                    //afficher OK

                    displaySession();
                    CRUDSession();
                    break;
                case 2:

                    //ajouter session
                    List<Formation> ll = model.getCentre().getListFormation();
                    c.listFormation(ll);
                    ap.selectFormationForSession();
                    sc.nextLine();
                    //DO WHILE
                    co.checkInt();
                    int tt = sc.nextInt();
                    if (ll.stream().anyMatch(f -> f.getIdFormation() == tt)) {
                        Formation formatt = model.getCentre().getFormationById(tt);
                        Session s = addSession(formatt);
                        formatt.addSession(s);
                    } else {
                        ep.noFormationId();
                    }
                    break;
                case 3:
                    //modifier Session
                    modifySession();
                    break;
                case 4:
                    //supprimer formation 
                    List lis = model.getCentre().getListFormation();
                    c.listFormation(lis);
                    ip.selectFormation();

                    sc.nextLine();
                    co.checkInt();
                    int tit = sc.nextInt();
                    Formation forma = model.getCentre().getFormationById(tit);
                    if (!forma.getSession().isEmpty()) {
                        ap.print(forma.getSession());
                        ip.selectSession();
                        co.checkInt();
                        int il = sc.nextInt();
                        Session sd = model.getCentre().getSessionById(il);
                        sd.deleteSession();
                    } else {
                        ep.noSession();
                    }
                    break;
                case 5:
                    //afficher participant
                    List listfo = model.getCentre().getListFormation();
                    c.listFormation(listfo);
                    int idF = 0;
                    do {
                        ip.selectFormation();
                        sc.nextLine();
                        co.checkInt();
                        idF = sc.nextInt();
                    } while (null == model.getCentre().getFormationById(idF));

                    Formation formation = model.getCentre().getFormationById(idF);
                    if (!formation.getSession().isEmpty()) {
                        ap.print(formation.getSession());
                        int it = 0;
                        do {
                            ap.selectSession();
                            co.checkInt();
                            it = sc.nextInt();
                        } while (null == model.getCentre().getSessionById(it));

                        Session s = model.getCentre().getSessionById(it);

                        ap.print(s.getListParticipants());

                    } else {
                        ep.noSession();
                    }
                    toAdminPage(model.getAdmin());
                    break;
                case 7:
                    toAdminPage(model.getAdmin());
                default:

                    ep.errorChoice();
                    sc.nextLine();
            }
        } while (choiceInt < 1 || choiceInt > 7);

    }

    public void CRUDFormateur() {

        ap.gererFormateur();

        do {
            checkEntry("gererFormateur");

            choiceInt = sc.nextInt();

            switch (choiceInt) {
                case 1:
                    //afficher
                    sc.nextLine();
                    List l = model.getFormateur().getListFormateur();
                    c.listFormateur(l);
                    break;

                case 2:
                    //ajouter
                    addFormateur();
                    break;
                case 3:
                    //modifier
                    List lf = model.getFormateur().getListFormateur();
                    c.listFormateur(lf);
                    ap.selectIdFormateurToModify();
                    Formateur fo = model.getCentre().getFormateurById(sc.nextInt());
                    do {
                        ep.noFormateurId();
                        c.listFormateur(lf);
                        ap.selectIdFormateurToModify();
                        fo = model.getCentre().getFormateurById(sc.nextInt());

                    } while (fo == null);
                    
                    modifyFormateur(fo);
                    break;
                case 4:
                    List lfor = model.getFormateur().getListFormateur();
                    c.listFormateur(lfor);
                    ap.selectIdFormateurToModify();
                    Formateur formateur = model.getCentre().getFormateurById(sc.nextInt());
                    List lisfo = model.getCentre().getListFormation();
                    c.listFormation(lisfo);
                    ap.selectFormationToModify();
                    Formation formation = model.getCentre().getFormationById(sc.nextInt());
                    List listEnseigne = formateur.getListFormationByFormateur();
                    if (listEnseigne.contains(formation)) {
                        String accord="";
                        sc.nextLine();
                        do {
                            ap.suppressionFormatonFormateur();
                            accord = sc.next();
                        } while (!"O".equals(accord.trim()) && !"N".equals(accord.trim()));

                        if (accord.equals("O")) {
                            formateur.deleteFormationFormateur(formation);
                            ap.formationSupprimeeFormateur(formateur);
                        }else{
                            ap.formationNonSupprimee(formateur);
                        }
                    } else {
                        formateur.ajouterFormationFormateur(formation);
                        ap.formationAjouteeFormateur(formateur);
                    }
                    break;
                              
                    
                case 5:
                    //supprimer

                    List lfo = model.getFormateur().getListFormateur();
                    c.listFormateur(lfo);
                    ap.selectIdFormateurToDelete();
                    Formateur forrma = null;
                    do {
                        ep.noFormateurId();
                        c.listFormateur(lfo);
                        ap.selectIdFormateurToDelete();
                        if (sc.hasNextInt()) {
                            forrma = model.getCentre().getFormateurById(sc.nextInt());
                        }
                        sc.nextLine();

                    } while (forrma == null);
                    if (forrma.deleteFormateur()) {
                        ap.sessionPrevueFormateur();
                    }
                    break;
                default:
                    ep.errorChoice();
                    sc.nextLine();
                    break;
            }
        } while (choiceInt < 1 || choiceInt > 4);

    }
    
        public void toAdminPage(User u) {

        //co.wash();
        ap.choose();
        sc.nextLine();
        do {
            checkEntry("choose");

            choiceInt = sc.nextInt();

            switch (choiceInt) {

                case 1:
                    //AFFICHAGE OK
                    List<Formation> l = model.getCentre().getListFormation();
                    int t = 0;
                    c.listFormation(l);
                    do {
                        ip.selectFormation();
                        sc.nextLine();
                        co.checkInt();
                        t = sc.nextInt();
                    } while (model.getCentre().getFormationById(t) == null);
                    Formation fo = model.getCentre().getFormationById(t);
                    if (!fo.getSession().isEmpty()) {
                        ap.formateurSession();
                        ap.print(fo.getSession());

                        int idS = 0;
                        do {
                            ap.idSession();
                            sc.nextLine();
                            co.checkInt();
                            idS = sc.nextInt();
                        } while (model.getCentre().getSessionById(idS) == null);
                        Session s = model.getCentre().getSessionById(idS);
                        Formateur form = model.getFormateur().getFormateurBySession(s);
                        cp.print(form);
                    } else {
                        ep.noSession();
                    }
                    toAdminPage(u);
                    break;

                case 2:
                    List lfo = model.getFormateur().getListFormateur();
                    c.listFormateur(lfo);
                    ap.idFormateur();
                    co.checkInt();
                    int idFormateur = sc.nextInt();
                    if (model.getFormateur().getListFormateur().stream().anyMatch(f -> f.getID() == idFormateur)) {
                        Formateur forma = model.getCentre().getFormateurById(idFormateur);
                        List<Session> se = model.getAdmin().getPrestationFormateur(forma);
                        if (!se.isEmpty()) {
                            c.listSession(se);
                        } else {
                            ep.noSessionForFormateur();
                        }

                    } else {
                        ep.noIdFormateur();
                    }

                    toAdminPage(u);
                    break;

                case 3:
                    CRUDFormation();
                    toAdminPage(u);
                    break;
                case 4:
                    CRUDFormateur();
                    toAdminPage(u);
                    break;

                case 5:
                    CRUDSession();
                    toAdminPage(u);
                    break;

                case 6:
                    ajoutStatus();
                    toAdminPage(u);
                    break;
                case 7:
                    ajoutLocal();
                    toAdminPage(u);
                    break;
                case 8:

                    List<Inscription> lins = model.getCentre().getPayementToConfirm();
                    Inscription ins = new Inscription();
                    int i = 0;

                    if (!lins.isEmpty()) {
                        ap.print(lins);
                        do {
                            ap.selectInscription();
                            co.checkInt();
                            i = sc.nextInt();
                            ins = model.getCentre().getInscriptionById(i);
                        } while (!lins.contains(ins));
                        model.getAdmin().confirmPayement(i);
                    } else {
                        ep.noPayementToConfirm();
                    }
                    break;

                case 9:
                    hp.quit();
                    System.exit(0);
                    break;
                default:
                    ep.errorChoice();
                    sc.nextLine();
                    toAdminPage(u);
                    break;
            }

        } while (!sc.hasNextInt() || choiceInt < 1 || choiceInt > 9);
    }

    public void checkEntry(String msg) {
        if (!sc.hasNextInt()) {
            do {
                ep.errorChoice();
                sc.nextLine();
                switch (msg) {
                    case "gererFormation":
                        ap.gererFormation();
                        break;
                    case "gererSession":
                        ap.gererSession();
                        break;
                    case "gererFormateur":
                        ap.gererFormateur();
                        break;
                    case "choose":
                        ap.choose();
                        break;
                    case "modifSession":
                        ap.modifSession();
                        break;

                }
            } while (!sc.hasNextInt());
        }

    }

}
