/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "Anneeuniversitaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anneeuniversitaire.findAll", query = "SELECT a FROM Anneeuniversitaire a"),
    @NamedQuery(name = "Anneeuniversitaire.findById", query = "SELECT a FROM Anneeuniversitaire a WHERE a.id = :id"),
    @NamedQuery(name = "Anneeuniversitaire.findByAnneeuniversitaire", query = "SELECT a FROM Anneeuniversitaire a WHERE a.anneeuniversitaire = :anneeuniversitaire")})
public class Anneeuniversitaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 254)
    @Column(name = "anneeuniversitaire")
    private String anneeuniversitaire;
    @OneToMany(mappedBy = "annid")
    private List<Projet> projetList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "annid")
    private List<Etudiant> etudiantList;
    @OneToMany(mappedBy = "annid")
    private List<Proposeprojet> proposeprojetList;

    public Anneeuniversitaire() {
    }

    public Anneeuniversitaire(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnneeuniversitaire() {
        return anneeuniversitaire;
    }

    public void setAnneeuniversitaire(String anneeuniversitaire) {
        this.anneeuniversitaire = anneeuniversitaire;
    }

    @XmlTransient
    public List<Projet> getProjetList() {
        return projetList;
    }

    public void setProjetList(List<Projet> projetList) {
        this.projetList = projetList;
    }

    @XmlTransient
    public List<Etudiant> getEtudiantList() {
        return etudiantList;
    }

    public void setEtudiantList(List<Etudiant> etudiantList) {
        this.etudiantList = etudiantList;
    }

    @XmlTransient
    public List<Proposeprojet> getProposeprojetList() {
        return proposeprojetList;
    }

    public void setProposeprojetList(List<Proposeprojet> proposeprojetList) {
        this.proposeprojetList = proposeprojetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anneeuniversitaire)) {
            return false;
        }
        Anneeuniversitaire other = (Anneeuniversitaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return anneeuniversitaire;
    }
    
}
