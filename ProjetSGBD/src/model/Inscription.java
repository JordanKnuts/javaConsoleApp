/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author MediaMonster
 */
public class Inscription {
    String intitule;
    Date dateDebut;
    int estPaye, id;
    float prix;
    

    public Inscription(int id,String intitule, Date dateDebut,int estPaye, Float prix) {
        this.intitule = intitule;
        this.dateDebut = dateDebut;
        this.estPaye = estPaye;
        this.id = id;
        this.prix = prix;
    }
    
   

    public Inscription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
   
    public String getIntitule() {
        return intitule;
    }

    public Date getDate() {
        return dateDebut;
    }

    public int isEstPaye() {
        return estPaye;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setDate(Date date) {
        this.dateDebut = date;
    }

    public void setEstPaye(int estPaye) {
        this.estPaye = estPaye;
    }

    public float getPrix() {
        return prix;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.intitule);
        hash = 59 * hash + Objects.hashCode(this.dateDebut);
        hash = 59 * hash + this.estPaye;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Float.floatToIntBits(this.prix);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Inscription other = (Inscription) obj;
        if (this.estPaye != other.estPaye) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.intitule, other.intitule)) {
            return false;
        }
        if (!Objects.equals(this.dateDebut, other.dateDebut)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        String paye  = isEstPaye() > 0 ? "Est payé" : "A Payer";
        return getId() +" "+getIntitule() + " - Commence le : " + getDate()+" - " + paye + " - " +getPrix() + "€";
    }
    
    
    
    
}
