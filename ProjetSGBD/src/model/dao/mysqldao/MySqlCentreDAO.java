/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mysqldao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.Formateur;
import model.Formation;
import model.Inscription;
import model.Local;
import model.Role;
import model.Session;
import model.Stagiaire;
import model.Status;
import model.User;
import model.dao.CentreDAO;

/**
 *
 * @author MediaMonster
 */
public class MySqlCentreDAO implements CentreDAO {

    private MySqlCentreDAO() {
    }

    private static MySqlCentreDAO instance;

    static {
        instance = new MySqlCentreDAO();
    }

    public static CentreDAO getInstance() {
        return instance;
    }

    @Override
    public List<Status> getListStatus() {
        List<Status> list = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT idStatus, nomStatus FROM status";
        try {
            list = new ArrayList<Status>();
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Status stat = new Status(rs.getInt(1), rs.getString(2));
                list.add(stat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<Formation> getListFormation() {
        List<Formation> list = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT formation.idFormation , formation.intitulé, formation.prix,formation.duree,formation.nbParticipantMax,formation.nbParticipantMin FROM formation ";
        try {
            list = new ArrayList<Formation>();
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Formation form = new Formation(rs.getInt("idFormation"), rs.getString("intitulé"), rs.getInt("nbParticipantMax"), rs.getInt("nbParticipantMin"), rs.getInt("prix"), rs.getInt("duree"));
                form.setIdFormation(rs.getInt("idFormation"));
                list.add(form);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<Formation> getListFormationByFormateur(Formateur f) {
        List<Formation> list = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT formation.idFormation , formation.intitulé, formation.prix,formation.duree,formation.nbParticipantMax,formation.nbParticipantMin FROM formation,enseigne WHERE enseigne.idFormateur = ? AND enseigne.idFormation = formation.idFormation ";
        try {
            list = new ArrayList<Formation>();
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, f.getID());
            rs = st.executeQuery();
            while (rs.next()) {
                Formation form = new Formation(rs.getInt("idFormation"), rs.getString("intitulé"), rs.getInt("nbParticipantMax"), rs.getInt("nbParticipantMin"), rs.getInt("prix"), rs.getInt("duree"));
                form.setIdFormation(rs.getInt("idFormation"));
                list.add(form);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<Formateur> getListFormateurByFormation(Formation f, Date d, Date fi) {
        List<Formateur> list = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select user.id,user.nom,user.prenom,user.adresse,user.telephone,user.mail,user.login,user.password,user.role,user.status, role.idRole,status.idStatus , role.nomRole , status.nomStatus,enseigne.idFormation,enseigne.idFormateur FROM enseigne, user ,role ,status WHERE role.idRole = user.role AND status.idStatus = user.status AND enseigne.idFormateur = user.id AND enseigne.idFormation = ? AND user.id NOT IN (SELECT session.idFormateur from session WHERE session.dateDebut > ? AND session.dateFin < ? OR session.dateDebut < ? AND session.dateFin > ?)";
        try {
            list = new ArrayList<Formateur>();
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, f.getIdFormation());
            st.setDate(2, new java.sql.Date(fi.getTime()));
            st.setDate(3, new java.sql.Date(d.getTime()));
            st.setDate(4, new java.sql.Date(fi.getTime()));
            st.setDate(5, new java.sql.Date(d.getTime()));

            rs = st.executeQuery();
            while (rs.next()) {
                Formateur form = new Formateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"), rs.getString("login"), rs.getString("password"), new Role(rs.getInt("idRole"), rs.getString("nomRole")), new Status(rs.getInt("idStatus"), rs.getString("nomStatus")));
                list.add(form);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<Session> getListSession() {
        List<Session> list = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT session.idSession,formation.idFormation,formation.intitulé,formation.nbParticipantMax,formation.nbParticipantMin,formation.prix,formation.duree,user.id,user.nom,user.prenom,user.adresse,user.telephone,user.mail,user.login,user.password,user.role,user.status, role.idRole,status.idStatus, role.nomRole , status.nomStatus,local.idLocal,local.nomLocal,session.dateDebut,session.dateFin FROM session,formation,user,role,local,status WHERE session.idFormation = formation.idFormation AND session.idFormateur = user.id AND session.idLocal = local.idLocal GROUP BY idSession";
        // String sql = "SELECT  u.id , u.nom , u.prenom, u.adresse , u.telephone, u.mail, u.role , r.nomRole, s.idLocal,s.idSession, l.nomLocal, s.dateDebut , s.dateFin FROM user u,role r,local l, session s WHERE s.idFormateur = u.id AND u.role = r.idRole AND s.idLocal = l.idLocal";
        //String sql = "SELECT u.id , u.nom , u.prenom, u.adresse , u.telephone, u.mail, u.role , r.nomRole, s.idLocal,s.idSession, l.nomLocal, s.dateDebut , s.dateFin FROM session s, user u, role r, local l WHERE  s.idFormateur = u.id AND u.role = r.idRole AND s.idLocal = l.idLocal";
        try {
            list = new ArrayList<Session>();
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                //Formateur formateur, Local local, Date dateDebut, Date dateFin
                //(int id, String nom, String prenom,String adresse,String telephone,  String email,Role role) 
                //Local(String nom)
                Session sess = new Session(rs.getInt("idSession"),
                        new Formateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"), rs.getString("login"), rs.getString("password"), new Role(rs.getInt("idRole"), rs.getString("nomRole")), new Status(rs.getInt("idStatus"), rs.getString("nomStatus"))),
                        new Local(rs.getInt("idLocal"), rs.getString("nomLocal")),
                        rs.getDate("DateDebut"), rs.getDate("DateFin"));
                list.add(sess);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Local getLocalById(int idLocal) {
        Local local = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT idLocal,nomLocal FROM local WHERE idLocal = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, idLocal);
            rs = st.executeQuery();

            if (rs.next()) {
                local = new Local(rs.getInt("idLocal"), rs.getString("nomLocal"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return local;
    }

    @Override
    public Formation getFormationById(int idFormation) {
        Formation formation = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT formation.idFormation , formation.intitulé, formation.prix,formation.duree,formation.nbParticipantMax,formation.nbParticipantMin FROM formation WHERE idFormation = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, idFormation);
            rs = st.executeQuery();

            if (rs.next()) {
                formation = new Formation(rs.getInt("idFormation"), rs.getString("intitulé"), rs.getInt("nbParticipantMax"), rs.getInt("nbParticipantMin"), rs.getInt("prix"), rs.getInt("duree"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return formation;
    }

    @Override
    public User getUserById(int idUser) {
        User user = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT u.id,u.nom,u.prenom,u.adresse,u.telephone,u.mail,u.login,u.password,u.role,u.status, r.idRole,s.idStatus , r.nomRole , s.nomStatus FROM user U,role R,status S WHERE u.role = r.idRole AND u.status = s.idStatus AND id = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, idUser);
            rs = st.executeQuery();

            if (rs.next()) {
                switch (rs.getInt("role")) {
                    case 1:
                        user = new Admin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"), new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getString("password"));
                        break;
                    case 2:
                        user = new Stagiaire(rs.getInt("id"), new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"),
                                new Status(rs.getInt("idStatus"), rs.getString("nomStatus")),
                                rs.getString("password"));
                        break;
                    case 3:
                        user = new Formateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"), new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getString("password"));
                        break;
                    default:
                        break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public Session getSessionById(int idSession) {
        Session session = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT session.idSession,formation.idFormation,formation.intitulé,formation.nbParticipantMax,formation.nbParticipantMin,formation.prix,formation.duree,user.id,user.nom,user.prenom,user.adresse,user.telephone,user.mail,user.login,user.password,user.role,user.status, role.idRole,status.idStatus, role.nomRole , status.nomStatus,local.idLocal,local.nomLocal,session.dateDebut,session.dateFin FROM session,formation,user,role,local,status WHERE session.idFormation = formation.idFormation AND session.idFormateur = user.id AND session.disponible = TRUE AND session.idLocal = local.idLocal AND idSession = ? GROUP BY session.idSession";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, idSession);
            rs = st.executeQuery();

            if (rs.next()) {
                session = new Session(rs.getInt("idSession"), new Formateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"), rs.getString("login"), rs.getString("password"), new Role(rs.getInt("idRole"), rs.getString("nomRole")), new Status(rs.getInt("idStatus"), rs.getString("nomStatus"))),
                        new Local(rs.getInt("idLocal"), rs.getString("nomLocal")),
                        rs.getDate("DateDebut"), rs.getDate("DateFin"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return session;
    }

    @Override
    public Inscription getInscriptionById(int idInscription) {
        Inscription i = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT inscription.idInscription , inscription.prix, inscription.idSession,session.dateDebut , session.idFormation,formation.intitulé,inscription.idUser,inscription.estPaye,inscription.payementSignale "
                + "FROM inscription,session,formation "
                + "WHERE inscription.idSession = session.idSession AND session.idFormation = formation.idFormation AND idInscription = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, idInscription);
            rs = st.executeQuery();

            if (rs.next()) {
                i = new Inscription(rs.getInt("idInscription"), rs.getString("intitulé"), rs.getDate("dateDebut"), rs.getInt("estPaye"), rs.getFloat("prix"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @Override
    public List<Local> getListLocauxLibre(Date d, Date f) {
        List list = new ArrayList<Local>();
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sqlDateLibre = "SELECT local.idLocal , local.nomLocal FROM local WHERE local.idLocal NOT IN(select session.idLocal from session,local where local.idLocal = session.idLocal AND session.dateDebut < ? AND session.dateFin > ? OR session.dateDebut > ? AND session.dateFin < ?)";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sqlDateLibre);
            st.setDate(1, new java.sql.Date(f.getTime()));
            st.setDate(2, new java.sql.Date(d.getTime()));
            st.setDate(3, new java.sql.Date(f.getTime()));
            st.setDate(4, new java.sql.Date(d.getTime()));
            rs = st.executeQuery();
            while (rs.next()) {
                Local local = new Local(rs.getInt("idLocal"), rs.getString("nomLocal"));
                list.add(local);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void addSession(Session s) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "INSERT INTO `session`(`idFormation`,`idFormateur`, `idLocal`, `dateDebut`, `dateFin`) "
                + "VALUES (?, ?, ?, ?,?)";

        try {
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, s.getFormation().getIdFormation());

            st.setInt(2, s.getFormateur().getIdFormateur());
            st.setInt(3, s.getLocal().getIdLocal());
            java.util.Date dateD = s.getDateDebut();
            java.util.Date dateF = s.getDateFin();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String dateDeb = dateFormat.format(dateD);
            String dateFin = dateFormat.format(dateF);
            st.setDate(4, java.sql.Date.valueOf(dateDeb));
            st.setDate(5, java.sql.Date.valueOf(dateFin));

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Inscription> getListInscriptionSession(Session s) {
        List<Inscription> i = new ArrayList<>();
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT inscription.idInscription ,inscription.prix, inscription.idSession,session.dateDebut , session.idFormation,formation.intitulé,inscription.idUser,inscription.estPaye,inscription.payementSignale "
                + "FROM inscription,session,formation "
                + "WHERE inscription.idSession = session.idSession AND session.idFormation = formation.idFormation AND inscription.idSession = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, s.getIdSession());
            rs = st.executeQuery();

            while (rs.next()) {
                Inscription in = new Inscription(rs.getInt("idInscription"), rs.getString("intitulé"), rs.getDate("dateDebut"), rs.getInt("estPaye"), rs.getFloat("prix"));
                i.add(in);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;

    }

    @Override
    public void cleanDB() {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = " call ps_cleanDB() ";

        try {
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
           
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean addStatus(Status s) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean b = false;

        String sqlExist = " SELECT nomStatus FROM status WHERE nomStatus = ?";
        String sql = "INSERT INTO status( status.nomStatus, status.reduction) VALUES (?,?)";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();

            st = c.prepareStatement(sqlExist);
            st.setString(1, s.getNomStatus());
            rs = st.executeQuery();

            if (!rs.next()) {
                st = c.prepareStatement(sql);
                st.setString(1, s.getNomStatus());
                st.setDouble(2, 1 - s.getReduction() / 100);
                st.executeUpdate();
                b = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    @Override
    public List<String> getListInscriptionBySession(Session s) {
        List<String> i = new ArrayList<>();
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT user.nom , user.prenom,status.nomStatus, inscription.prix FROM user,status,inscription WHERE user.status = status.idStatus AND user.id = inscription.idUser AND inscription.idSession = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, s.getIdSession());
            rs = st.executeQuery();

            while (rs.next()) {
                String in = rs.getString("nom") + " " + rs.getString("prenom") + " " + rs.getString("nomStatus") + " " + rs.getDouble("prix");
                i.add(in);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @Override
    public void updateSession(Session s) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = " UPDATE session SET session.idFormateur = ? , session.idLocal = ? WHERE idSession = ?";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);

            st.setInt(1, s.getFormateur().getIdFormateur());
            st.setInt(2, s.getLocal().getIdLocal());
            st.setInt(3, s.getIdSession());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean addLocal(Local l) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean b = false;
        String sqlExist = "SELECT nomLocal FROM local WHERE nomLocal = ?";
        String sql = "INSERT INTO local(local.nomLocal) VALUES (?)";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();

            st = c.prepareStatement(sqlExist);
            st.setString(1, l.getNom());
            rs = st.executeQuery();

            if (!rs.next()) {
                st = c.prepareStatement(sql);
                st.setString(1, l.getNom());

                st.executeUpdate();
                b = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    @Override
    public List<Inscription> getPayementToConfirm() {
        List<Inscription> i = new ArrayList<>();
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT inscription.idInscription ,inscription.prix, inscription.idSession,session.dateDebut , session.idFormation,formation.intitulé,inscription.idUser,inscription.estPaye,inscription.payementSignale "
                + "FROM inscription,session,formation "
                + "WHERE inscription.idSession = session.idSession AND session.idFormation = formation.idFormation AND inscription.estPaye = 0 AND inscription.payementSignale = 1";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Inscription in = new Inscription(rs.getInt("idInscription"), rs.getString("intitulé"), rs.getDate("dateDebut"), rs.getInt("estPaye"), rs.getFloat("prix"));
                i.add(in);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @Override
    public List<Stagiaire> getListParticipant(Session s) {
        List<Stagiaire> ls = new ArrayList<>();
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT u.id,u.nom,u.prenom,u.adresse,u.telephone,u.mail,u.login,u.password,u.role,u.status, r.idRole,s.idStatus , r.nomRole , s.nomStatus FROM user U,role R,status S,session,inscription WHERE u.role = r.idRole AND inscription.idSession = session.idSession AND inscription.idUser = u.id AND session.idSession = ? GROUP BY id";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, s.getIdSession());
            rs = st.executeQuery();

            while (rs.next()) {
                Stagiaire sta = new Stagiaire(rs.getInt("id"), new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"),
                        new Status(rs.getInt("idStatus"), rs.getString("nomStatus")),
                        rs.getString("password"));
                ls.add(sta);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ls;
    }

    @Override
    public User ifEmailExist(String email) {
        User user = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT u.id,u.nom,u.prenom,u.adresse,u.telephone,u.mail,u.login,u.password,u.role,u.status, r.idRole,s.idStatus , r.nomRole , s.nomStatus FROM user U,role R,status S WHERE u.role = r.idRole AND ( u.status = s.idStatus OR ISNULL(status)) AND u.mail = ? GROUP BY 1";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();

            if (rs.next()) {
                switch (rs.getInt("role")) {
                    case 1:
                        user = new Admin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"), new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getString("password"));
                        break;
                    case 2:
                        user = new Stagiaire(rs.getInt("id"), new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"),
                                new Status(rs.getInt("idStatus"), rs.getString("nomStatus")),
                                rs.getString("password"));
                        break;
                    case 3:
                        user = new Formateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"), new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getString("password"));
                        break;
                    default:
                        break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public Formation ifIntituleExist(String intitule) {
        Formation f = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "SELECT formation.intitulé FROM formation WHERE formation.intitulé = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, intitule);
            rs = st.executeQuery();

            if (rs.next()) {
               f = new Formation(rs.getInt("idFormation"), rs.getString("intitulé"), rs.getInt("nbParticipantMax"), rs.getInt("nbParticipantMin"), rs.getInt("prix"), rs.getInt("duree"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return f;
    }

    @Override
    public void deleteFormationFormateur(Formateur formateur, Formation formation) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "DELETE FROM enseigne WHERE idFormation = ? AND idFormateur = ? ";
        
         try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, formation.getIdFormation());
            st.setInt(2, formateur.getIdFormateur());
            st.executeUpdate();

            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void ajouterFormationFormateur(Formateur formateur, Formation formation) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "INSERT INTO enseigne(idFormation,idFormateur) VALUES(?,?) ";
        
         try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, formation.getIdFormation());
            st.setInt(2, formateur.getIdFormateur());
            st.executeUpdate();

            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
