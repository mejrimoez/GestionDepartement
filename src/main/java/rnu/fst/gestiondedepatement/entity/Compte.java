/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author moez
 */
@Entity
@Table(name = "Compte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compte.findAll", query = "SELECT c FROM Compte c"),
    @NamedQuery(name = "Compte.findByCin", query = "SELECT c FROM Compte c WHERE c.cin = :cin"),
    @NamedQuery(name = "Compte.findByMotdepass", query = "SELECT c FROM Compte c WHERE c.motdepass = :motdepass"),
    @NamedQuery(name = "Compte.findByCredentials", query = "SELECT c FROM Compte c WHERE c.cin = :cin and c.motdepass = :motdepass"),
    @NamedQuery(name = "Compte.findByLabel", query = "SELECT c FROM Compte c WHERE c.label = :label"),
    @NamedQuery(name = "Compte.findByRole", query = "SELECT c FROM Compte c WHERE c.role = :role"),
    @NamedQuery(name = "Compte.findByDateconnection", query = "SELECT c FROM Compte c WHERE c.dateconnection = :dateconnection")})
public class Compte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "cin")
    private String cin;
    @Size(max = 254)
    @Column(name = "motdepass")
    private String motdepass;
    @Size(max = 254)
    @Column(name = "label")
    private String label;
    @Size(max = 254)
    @Column(name = "role")
    private String role;
    @Column(name = "dateconnection")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateconnection;

    public Compte() {
    }

    public Compte(String cin) {
        this.cin = cin;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getMotdepass() {
        return motdepass;
    }

    public void setMotdepass(String motdepass) {
        this.motdepass = motdepass;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDateconnection() {
        return dateconnection;
    }

    public void setDateconnection(Date dateconnection) {
        this.dateconnection = dateconnection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cin != null ? cin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.cin == null && other.cin != null) || (this.cin != null && !this.cin.equals(other.cin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return label;
    }
    
}
