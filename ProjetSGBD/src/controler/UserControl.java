/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Stagiaire;
import model.Status;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import view.AuthentificationPage;
import view.InscriptionPage;
import view.StagiairePage;

/**
 *
 * @author MediaMonster
 */
public class UserControl implements ControllerInterface {

    private User u;
    private StagiairePage sp = new StagiairePage();
    private AuthentificationPage autp = new AuthentificationPage();
    private InscriptionPage ip = new InscriptionPage();

    private Map<Integer, String> mapStatus = new HashMap<>();

    private List<Status> listStatus = model.getCentre().getListStatus();

    private int nbSt = 0;

    public User authentificationUser() {
        String password = " ";
        do {
            autp.login();
            String login = sc.next();
            autp.password();
            password = sc.next();

            u = model.getUser().getConnect(login);
            if (null == u || BCrypt.checkpw(password, u.getPassword()) == false) {
                autp.error();
                sc.nextLine();
            }
        } while (null == u || !BCrypt.checkpw(password, u.getPassword()));

        return u;

    }

    public void mapStat() {
        for (Status s : listStatus) {
            mapStatus.put(s.getIdStatus(), s.getNomStatus());
        }
    }

    public int getStatusId() {

        ip.printList(listStatus);
        sp.modifStatus();
        mapStat();
        if (sc.hasNextInt()) {
            nbSt = sc.nextInt();
            if (mapStatus.containsKey(nbSt)) {

                return nbSt;
            } else {
                sc.nextLine();
                getStatusId();
            }
        } else {
            sc.nextLine();
            getStatusId();
        }

        return nbSt;
    }

    public Stagiaire inscriptionUser() {
        Stagiaire stagiaire = new Stagiaire();
        sp.modifNom();
        stagiaire.setNom(sc.nextLine());
        sp.modifPrenom();
        stagiaire.setPrenom(sc.nextLine());
        sp.modifAdresse();
        stagiaire.setAdresse(sc.nextLine());
        sp.modifTel();
        stagiaire.setTelephone(sc.nextLine());
        
        
        
        String email="";
        do {
            do {
                sp.modifMail();
                email = sc.nextLine();
            } while (!email.matches("^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
                    || email == null
                    || email.trim().isEmpty());       
        } while (model.getCentre().ifEmailExist(email) != null);
        stagiaire.setEmail(sc.nextLine());
        
        stagiaire.setStatus(new Status(getStatusId()));
        sc.nextLine();
        sp.modifLogin();
        stagiaire.setLogin(sc.nextLine());
        sp.modifPassword();
        String hashPswrd = BCrypt.hashpw(sc.nextLine(), BCrypt.gensalt());
        stagiaire.setPassword(hashPswrd);
        return stagiaire.subscribeStagiaire();
        
    }

    public User getUser() {
        return u;
    }

}
