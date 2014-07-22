/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author moez
 */
@Entity
@Table(name = "Choixprojetpropose")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Choixprojetpropose.findAll", query = "SELECT c FROM Choixprojetpropose c"),
    @NamedQuery(name = "Choixprojetpropose.findByEtuid", query = "SELECT c FROM Choixprojetpropose c WHERE c.choixprojetproposePK.etuid = :etuid"),
    @NamedQuery(name = "Choixprojetpropose.findById", query = "SELECT c FROM Choixprojetpropose c WHERE c.choixprojetproposePK.id = :id"),
    @NamedQuery(name = "Choixprojetpropose.findByDatechoix", query = "SELECT c FROM Choixprojetpropose c WHERE c.datechoix = :datechoix"),
    @NamedQuery(name = "Choixprojetpropose.findByNumchoix", query = "SELECT c FROM Choixprojetpropose c WHERE c.numchoix = :numchoix"),
    @NamedQuery(name = "Choixprojetpropose.findByEtat", query = "SELECT c FROM Choixprojetpropose c WHERE c.etat = :etat")})
public class Choixprojetpropose implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ChoixprojetproposePK choixprojetproposePK;
    @Column(name = "datechoix")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datechoix;
    @Column(name = "numchoix")
    private Integer numchoix;
    @Column(name = "etat")
    private Boolean etat;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proposeprojet proposeprojet;
    @JoinColumn(name = "Etu_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Etudiant etudiant;

    public Choixprojetpropose() {
    }

    public Choixprojetpropose(ChoixprojetproposePK choixprojetproposePK) {
        this.choixprojetproposePK = choixprojetproposePK;
    }

    public Choixprojetpropose(int etuid, int id) {
        this.choixprojetproposePK = new ChoixprojetproposePK(etuid, id);
    }

    public ChoixprojetproposePK getChoixprojetproposePK() {
        return choixprojetproposePK;
    }

    public void setChoixprojetproposePK(ChoixprojetproposePK choixprojetproposePK) {
        this.choixprojetproposePK = choixprojetproposePK;
    }

    public Date getDatechoix() {
        return datechoix;
    }

    public void setDatechoix(Date datechoix) {
        this.datechoix = datechoix;
    }

    public Integer getNumchoix() {
        return numchoix;
    }

    public void setNumchoix(Integer numchoix) {
        this.numchoix = numchoix;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Proposeprojet getProposeprojet() {
        return proposeprojet;
    }

    public void setProposeprojet(Proposeprojet proposeprojet) {
        this.proposeprojet = proposeprojet;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (choixprojetproposePK != null ? choixprojetproposePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Choixprojetpropose)) {
            return false;
        }
        Choixprojetpropose other = (Choixprojetpropose) object;
        if ((this.choixprojetproposePK == null && other.choixprojetproposePK != null) || (this.choixprojetproposePK != null && !this.choixprojetproposePK.equals(other.choixprojetproposePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rnu.fst.gestiondedepatement.entity.Choixprojetpropose[ choixprojetproposePK=" + choixprojetproposePK + " ]";
    }
    
}
