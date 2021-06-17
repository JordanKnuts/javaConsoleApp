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
import model.Admin;
import model.Formateur;
import model.Role;
import model.Stagiaire;
import model.Status;
import model.User;
import model.dao.AbstractDAOFactory;
import model.dao.CentreDAO;
import model.dao.UserDAO;

/**
 *
 * @author MediaMonster
 */
public class MySqlUserDAO implements UserDAO {

    AbstractDAOFactory factory = AbstractDAOFactory.getFactory();

    private MySqlUserDAO() {
    }

    private static MySqlUserDAO instance;

    public static UserDAO getInstance() {
        if (instance == null) {
            return new MySqlUserDAO();
        }
        return instance;
    }

    @Override
    public User getConnected(String login) {

        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        User user = null;

        String sql = " SELECT u.id,u.nom,u.prenom,u.adresse,u.telephone,u.mail,u.login,u.password,u.role,u.status, r.idRole,s.idStatus , r.nomRole , s.nomStatus FROM user U,role R,status S WHERE u.role = r.idRole AND login = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, login);
            rs = st.executeQuery();

            if (rs.next()) {
                if (rs.getString("nomRole").equals("admin")) {
                    user = new Admin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"),
                            new Role(rs.getInt("idRole"), rs.getString("nomRole")),
                             rs.getString("password"));

                } else if (rs.getString("nomRole").equals("user")) {
                    user = new Stagiaire(rs.getInt("id"), new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"),
                            new Status(rs.getInt("idStatus"), rs.getString("nomStatus")),
                            rs.getString("password"));

                } else if (rs.getString("nomRole").equals("formateur")) {
                    user = new Formateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("mail"), new Role(rs.getInt("idRole"), rs.getString("nomRole")), rs.getString("password"));
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

}
