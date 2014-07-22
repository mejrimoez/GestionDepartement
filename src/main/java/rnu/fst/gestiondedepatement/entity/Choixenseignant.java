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
@Table(name = "Choixenseignant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Choixenseignant.findAll", query = "SELECT c FROM Choixenseignant c"),
    @NamedQuery(name = "Choixenseignant.findById", query = "SELECT c FROM Choixenseignant c WHERE c.choixenseignantPK.id = :id"),
    @NamedQuery(name = "Choixenseignant.findByCin", query = "SELECT c FROM Choixenseignant c WHERE c.choixenseignantPK.cin = :cin"),
    @NamedQuery(name = "Choixenseignant.findByDatechoix", query = "SELECT c FROM Choixenseignant c WHERE c.datechoix = :datechoix"),
    @NamedQuery(name = "Choixenseignant.findByNumchoix", query = "SELECT c FROM Choixenseignant c WHERE c.numchoix = :numchoix"),
    @NamedQuery(name = "Choixenseignant.findByEtat", query = "SELECT c FROM Choixenseignant c WHERE c.etat = :etat")})
public class Choixenseignant implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ChoixenseignantPK choixenseignantPK;
    @Column(name = "datechoix")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datechoix;
    @Column(name = "numchoix")
    private Integer numchoix;
    @Column(name = "etat")
    private Boolean etat;
    @JoinColumn(name = "cin", referencedColumnName = "cin", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Enseignant enseignant;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Etudiant etudiant;

    public Choixenseignant() {
    }

    public Choixenseignant(ChoixenseignantPK choixenseignantPK) {
        this.choixenseignantPK = choixenseignantPK;
    }

    public Choixenseignant(int id, String cin) {
        this.choixenseignantPK = new ChoixenseignantPK(id, cin);
    }

    public ChoixenseignantPK getChoixenseignantPK() {
        return choixenseignantPK;
    }

    public void setChoixenseignantPK(ChoixenseignantPK choixenseignantPK) {
        this.choixenseignantPK = choixenseignantPK;
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

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
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
        hash += (choixenseignantPK != null ? choixenseignantPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Choixenseignant)) {
            return false;
        }
        Choixenseignant other = (Choixenseignant) object;
        if ((this.choixenseignantPK == null && other.choixenseignantPK != null) || (this.choixenseignantPK != null && !this.choixenseignantPK.equals(other.choixenseignantPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rnu.fst.gestiondedepatement.entity.Choixenseignant[ choixenseignantPK=" + choixenseignantPK + " ]";
    }
    
}
