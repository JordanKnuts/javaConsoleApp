/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Formateur;
import model.Formation;
import model.Inscription;
import model.Local;
import model.Role;
import model.Session;
import model.Status;
import model.dao.AbstractDAOFactory;
import model.dao.AdminDAO;

/**
 *
 * @author MediaMonster
 */
public class MySqlAdminDAO implements AdminDAO {

    AbstractDAOFactory factory = AbstractDAOFactory.getFactory();

    private MySqlAdminDAO() {
    }

    private static MySqlAdminDAO instance;

    static {
        instance = new MySqlAdminDAO();
    }

    public static AdminDAO getInstance() {
        return instance;
    }

    @Override
    public List<Formation> getFormationByName(String formation) {
        List<Formation> list = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT formation.idFormation , formation.intitulé, formation.prix,formation.duree,formation.nbParticipantMax,formation.nbParticipantMin FROM formation WHERE formation.disponible = TRUE AND intitulé LIKE  ?  ";
        try {
            list = new ArrayList<Formation>();
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, "%" + formation + "%");
            rs = st.executeQuery();
            while (rs.next()) {
                Formation form = new Formation(rs.getInt("idFormation"), rs.getString("intitulé"), rs.getInt("nbParticipantMax"), rs.getInt("nbParticipantMin"), rs.getInt("prix"), rs.getInt("duree"));

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
    public List<Formation> getFormationByPrice(int maxPrice) {
        List<Formation> list = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT formation.idFormation , formation.intitulé, formation.prix,formation.duree,formation.nbParticipantMax,formation.nbParticipantMin FROM formation  WHERE formation.disponible = TRUE AND prix < ?";
        try {
            list = new ArrayList<Formation>();
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, maxPrice);
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
    public Formateur getFormateurForSession(Session s) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Formateur formateur = null;

        String sql = "SELECT u.id,u.nom,u.prenom,u.adresse,u.telephone,u.mail,u.login,u.password,u.role,u.status, r.idRole,s.idStatus , r.nomRole , s.nomStatus FROM user U,role R,status S WHERE u.role = r.idRole AND u.status = s.idStatus AND id = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, s.getFormateur().getID());
            rs = st.executeQuery();
            if (rs.next()) {
                formateur = new Formateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"), rs.getString("login"), rs.getString("password"),
                        new Role(rs.getInt("idRole"), rs.getString("nomRole")),
                        new Status(rs.getInt("idStatus"), rs.getString("nomStatus")));
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
        return formateur;
    }

    @Override
    public List<Session> getPrestationFormateur(Formateur f) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Session> listSess = new ArrayList();
        String sql = "SELECT session.idSession,"
                + "formation.idFormation,formation.intitulé,formation.nbParticipantMax,formation.nbParticipantMin,formation.prix,formation.duree,"
                + "user.id,user.nom,user.prenom,user.adresse,user.telephone,user.mail,user.login,user.password,user.role,user.status,"
                + " role.idRole,status.idStatus, role.nomRole , "
                + "status.nomStatus,local.idLocal,local.nomLocal,"
                + "session.dateDebut,session.dateFin FROM session,formation,user,role,local,status "
                + "WHERE session.idFormation = formation.idFormation AND session.idFormateur = user.id "
                + "AND session.idLocal = local.idLocal AND idFormateur = ? GROUP BY idSession";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, f.getID());
            rs = st.executeQuery();
            while (rs.next()) {
                Session sess = new Session(rs.getInt("idSession"),
                        new Formateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"), rs.getString("login"), rs.getString("password"), new Role(rs.getInt("idRole"), rs.getString("nomRole")), new Status(rs.getInt("idStatus"), rs.getString("nomStatus"))),
                        new Local(rs.getInt("idLocal"), rs.getString("nomLocal")),
                        rs.getDate("DateDebut"), rs.getDate("DateFin"));
                listSess.add(sess);
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

        return listSess;
    }

    @Override
    public void modifyFormation(Formation f) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "UPDATE formation SET intitulé = ? , nbParticipantMax = ?, nbParticipantMin = ? , prix = ? , duree = ? WHERE IdFormation = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, f.getIntitule());
            st.setInt(2, f.getNbParticipantMax());
            st.setInt(3, f.getNbParticipantMin());
            st.setInt(4, f.getPrix());
            st.setInt(5, f.getDuree());
            st.setInt(6, f.getIdFormation());

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
    public void addFormation(Formation f) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "INSERT INTO `formation`(`intitulé`, `nbParticipantMax`,`nbParticipantMin`, `prix`, `duree`) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, f.getIntitule());
            st.setInt(2, f.getNbParticipantMax());
            st.setInt(3, f.getNbParticipantMin());
            st.setInt(4, f.getPrix());
            st.setInt(5, f.getDuree());

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
    public boolean deleteFormation(Formation f) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean r = false;

        String sql = "SELECT * FROM formation WHERE idFormation = ? AND idFormation NOT IN(SELECT idFormation FROM session)";

        String sql1 = "SELECT idSession FROM `session` WHERE `idFormation` = ? AND DATEDIFF(session.dateDebut,CURRENT_DATE)<0";

        String sql2 = "UPDATE formation SET disponible = 0 WHERE idFormation = ? ";

        //String sql3 = "UPDATE session SET disponible = 0 WHERE session.idSession = ? ";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, f.getIdFormation());

            rs = st.executeQuery();

            if (rs.next()) {
                st = c.prepareStatement(sql2);
                st.setInt(1, f.getIdFormation());
                st.executeUpdate();

            } else {

                st = c.prepareStatement(sql1);
                st.setInt(1, f.getIdFormation());
                rs = st.executeQuery();
                
                
                
                while (rs.next()) {
                    r = true;
                    st = c.prepareStatement(sql2);
                    st.setInt(1, f.getIdFormation());
                    st.executeUpdate();
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
        return r;
    }

    @Override
    public List<Formateur> getListFormateur() {
        List<Formateur> list = null;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT u.id,u.nom,u.prenom,u.adresse,u.telephone,u.mail,u.login,u.password,u.role,u.status, r.idRole,s.idStatus , r.nomRole , s.nomStatus FROM user U,role R,status S WHERE u.role = r.idRole AND u.status = s.idStatus AND r.nomRole = ?";
        try {
            list = new ArrayList<Formateur>();
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, "formateur");
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
    public void modifyFormateur(Formateur f) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "UPDATE user SET nom = ? , prenom = ? , adresse = ? , telephone = ? , mail = ? , login = ? , password = ?  WHERE id = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, f.getNom());
            st.setString(2, f.getPrenom());
            st.setString(3, f.getAdresse());
            st.setString(4, f.getTelephone());
            st.setString(5, f.getEmail());
            st.setString(6, f.getLogin());
            st.setString(7, f.getPassword());
            st.setInt(8, f.getIdFormateur());

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
    public void addFormateur(Formateur f) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "INSERT INTO `user`(`nom`, `prenom`, `adresse`, `telephone`, `mail`, `login`, `password`, `role`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,?)";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, f.getNom());
            st.setString(2, f.getPrenom());
            st.setString(3, f.getAdresse());
            st.setString(4, f.getTelephone());
            st.setString(5, f.getEmail());
            st.setString(6, f.getLogin());
            st.setString(7, f.getPassword());
            st.setInt(8, 3);

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
    public void deleteSession(Session s) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "UPDATE session SET disponible = FALSE WHERE idSession = ? ";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, s.getIdSession());

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
    public boolean deleteFormateur(Formateur f) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean sessPrevue = true;
        String sqlSessPrevue = "SELECT idFormateur FROM session WHERE (session.dateDebut>CURDATE() OR session.dateFin>CURDATE()) AND idFormateur = ?";
        String sql = "DELETE FROM enseigne WHERE idFormateur = ?";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();

            st = c.prepareStatement(sqlSessPrevue);
            st.setInt(1, f.getIdFormateur());
            rs = st.executeQuery();
            if (!rs.next()) {
                sessPrevue = false;
                st = c.prepareStatement(sql);
                st.setInt(1, f.getIdFormateur());
                st.executeUpdate();

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
        return sessPrevue;
    }

    @Override
    public void modifySession(Session s) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "UPDATE session SET  idFormateur = ? , idLocal = ? , dateDebut = ? , dateFin = ?   WHERE idSession = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, s.getFormateur().getIdFormateur());
            st.setInt(2, s.getLocal().getIdLocal());
            java.util.Date dateD = s.getDateDebut();
            java.util.Date dateF = s.getDateFin();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateDeb = dateFormat.format(dateD);
            String dateFin = dateFormat.format(dateF);
            st.setDate(3, java.sql.Date.valueOf(dateDeb));
            st.setDate(4, java.sql.Date.valueOf(dateFin));
            st.setInt(5, s.getIdSession());

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
    public void confirmPayement(int i) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "UPDATE inscription SET estPaye = TRUE WHERE idInscription = ? ";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, i);

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
