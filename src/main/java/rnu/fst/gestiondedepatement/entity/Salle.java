/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author moez
 */
@Entity
@Table(name = "Salle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salle.findAll", query = "SELECT s FROM Salle s"),
    @NamedQuery(name = "Salle.findByCodesalle", query = "SELECT s FROM Salle s WHERE s.codesalle = :codesalle"),
    @NamedQuery(name = "Salle.findBySalle", query = "SELECT s FROM Salle s WHERE s.salle = :salle"),
    @NamedQuery(name = "Salle.findByCapacite", query = "SELECT s FROM Salle s WHERE s.capacite = :capacite")})
public class Salle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codesalle")
    private Integer codesalle;
    @Size(max = 254)
    @Column(name = "salle")
    private String salle;
    @Column(name = "capacite")
    private Integer capacite;
    @OneToMany(mappedBy = "codesalle")
    private List<Projet> projetList;

    public Salle() {
    }

    public Salle(Integer codesalle) {
        this.codesalle = codesalle;
    }

    public Integer getCodesalle() {
        return codesalle;
    }

    public void setCodesalle(Integer codesalle) {
        this.codesalle = codesalle;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    @XmlTransient
    public List<Projet> getProjetList() {
        return projetList;
    }

    public void setProjetList(List<Projet> projetList) {
        this.projetList = projetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codesalle != null ? codesalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salle)) {
            return false;
        }
        Salle other = (Salle) object;
        if ((this.codesalle == null && other.codesalle != null) || (this.codesalle != null && !this.codesalle.equals(other.codesalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rnu.fst.gestiondedepatement.entity.Salle[ codesalle=" + codesalle + " ]";
    }
    
}
