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
import java.util.ArrayList;
import java.util.List;
import model.Formateur;
import model.Formation;
import model.Inscription;
import model.Local;
import model.Role;
import model.Session;
import model.Stagiaire;
import model.Status;
import model.dao.StagiaireDAO;

/**
 *
 * @author MediaMonster
 */
public class MySqlStagiaireDAO implements StagiaireDAO {

    private MySqlStagiaireDAO() {
    }

    private static MySqlStagiaireDAO instance;

    public static StagiaireDAO getInstance() {
        if (instance == null) {
            return new MySqlStagiaireDAO();
        }
        return instance;
    }

    @Override
    public Stagiaire subscribe(Stagiaire stagiaire) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Stagiaire p = null;
        String sql = "INSERT INTO `user`(`nom`, `prenom`, `adresse`, `telephone`, `mail`, `login`, `password`, `role`, `status`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,?, ?)";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, stagiaire.getNom());
            st.setString(2, stagiaire.getPrenom());
            st.setString(3, stagiaire.getAdresse());
            st.setString(4, stagiaire.getTelephone());
            st.setString(5, stagiaire.getEmail());
            st.setString(6, stagiaire.getLogin());
            st.setString(7, stagiaire.getPassword());
            st.setString(8, "1");
            st.setInt(9, stagiaire.getStatus().getIdStatus());

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
        return p;
    }

    @Override
    public void modify(Stagiaire sta) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        //String sqlExist = "SELECT id FROM user WHERE (login = ? OR mail = ?) AND id <> ?";
        String sql = "UPDATE user SET nom=?,prenom =?,adresse = ?,telephone = ?,mail=?, login = ?, password = ?, status = ? WHERE id = ? ";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, sta.getNom());
            st.setString(2, sta.getPrenom());
            st.setString(3, sta.getAdresse());
            st.setString(4, sta.getTelephone());
            st.setString(5, sta.getEmail());
            st.setString(6, sta.getLogin());
            st.setString(7, sta.getPassword());
            st.setInt(8, sta.getStatus().getIdStatus());
            st.setInt(9, sta.getID());

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
    public List<Inscription> getListInscription(int id) {

        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Inscription ins = null;
        List<Inscription> list = new ArrayList<>();
        String sql = "SELECT inscription.idInscription ,inscription.prix, inscription.idSession,session.dateDebut , session.idFormation,formation.intitulé,inscription.idUser,inscription.estPaye,inscription.payementSignale "
                + "FROM inscription,session,formation "
                + "WHERE inscription.idSession = session.idSession AND session.idFormation = formation.idFormation AND formation.disponible = TRUE AND idUser = ?";
        //= "SELECT formation.intitulé, `session`.dateDebut as date,inscription.estPaye FROM `user` JOIN inscription ON user.id = inscription.idUser JOIN session ON inscription.idSession = session.idSession JOIN formation ON formation.idFormation = session.idFormation WHERE user.id = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {

                ins = new Inscription(rs.getInt("idInscription"), rs.getString("intitulé"), rs.getDate("dateDebut"), rs.getInt("estPaye"),rs.getFloat("prix"));
                list.add(ins);
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
    public List<Session> getSession(Formation formation) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        List<Session> list = new ArrayList<>();
        String sql = "SELECT session.idSession,formation.idFormation,formation.intitulé,formation.nbParticipantMax,formation.nbParticipantMin,formation.prix,formation.duree,user.id,user.nom,user.prenom,user.adresse,user.telephone,user.mail,user.login,user.password,user.role,user.status, role.idRole,status.idStatus, role.nomRole , status.nomStatus,local.idLocal,local.nomLocal,session.dateDebut,session.dateFin FROM session,formation,user,role,local,status WHERE session.idFormation = formation.idFormation AND session.idFormateur = user.id AND session.idLocal = local.idLocal AND session.disponible = TRUE AND formation.idFormation = ? GROUP BY idSession  ";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, formation.getIdFormation());
            rs = st.executeQuery();
            while (rs.next()) {
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
    public boolean subscribeToSession(int idSession, Stagiaire s) {
        boolean b = false;
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sqlExist = " SELECT * FROM inscription WHERE idSession = ? AND idUser = ? ";
        String sqlReduction  = " SELECT reduction from status where status.idStatus = ? ";
        String sqlPrix = " SELECT prix FROM formation,session WHERE session.idFormation = formation.idFormation AND session.idSession = ? ";
        String sql = " INSERT INTO inscription (idSession,idUser,estPaye,payementSignale,prix) VALUES (?,?,?,?,?) ";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sqlExist);

            st.setInt(1, idSession);
            st.setInt(2, s.getID());
            rs = st.executeQuery();

            if (!rs.next()) {
                
                st = c.prepareStatement(sqlPrix);
                st.setInt(1, idSession);
                rs = st.executeQuery();
              
                rs.next();
                int p = rs.getInt(1);
                st = c.prepareStatement(sqlReduction);
                st.setInt(1, s.getStagiaireStatus().getIdStatus());
                rs = st.executeQuery();
                rs.next();
                double r = rs.getDouble(1);
                
                
                st = c.prepareStatement(sql);
                st.setInt(1, idSession);
                st.setInt(2, s.getID());
                st.setInt(3, 0);
                st.setInt(4, 0);
                st.setFloat(5, (float)(p*r));
                
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
    public void deleteInscription(Inscription i) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "DELETE FROM inscription WHERE idInscription = ?";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, i.getId());

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
    public void signalePayment(int i) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
            String sql = "UPDATE inscription SET payementSignale = TRUE WHERE idInscription = ? ";

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
