/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.dao.AbstractDAOFactory;
import model.dao.CentreDAO;

/**
 *
 * @author MediaMonster
 */
public class Status {

    private Integer idStatus;
    private String nomStatus;
    private double reduction;

    public Status(Integer idStatus, String nomStatus) {
        this.idStatus = idStatus;
        this.nomStatus = nomStatus;
    }

    public Status(String nomStatus) {
        this.nomStatus = nomStatus;
    }
    
    public Status(Integer idStatus){
        this.idStatus=idStatus;
    }

    public Status(String nomStatus, double reduction) {
        this.nomStatus = nomStatus;
        this.reduction = reduction;
    }
    
    

    public Status() {
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getNomStatus() {
        return nomStatus;
    }

    public void setNomStatus(String nomStatus) {
        this.nomStatus = nomStatus;
    }

    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) {
        this.reduction = reduction;
    }
    
    public boolean addStatus(){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        CentreDAO centreDAO = factory.createCentreDAO();
        return centreDAO.addStatus(this);
    }
    
    
    @Override
    public String toString() {
        return "\n"+getIdStatus()+" : "+ getNomStatus()+"\n";
    }
    
    

}
