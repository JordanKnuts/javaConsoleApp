/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MediaMonster
 */
public class Role {
    private Integer idRole;
    private String nomRole ;

    public Role(Integer id,String nomRole) {
        this.idRole = id;
        this.nomRole = nomRole;
    }

    public Role() {
    }

    public Integer getId() {
        return idRole;
    }

    public void setId(Integer id) {
        this.idRole = id;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String intituleRole) {
        this.nomRole = intituleRole;
    }
    
     
    
    
}
