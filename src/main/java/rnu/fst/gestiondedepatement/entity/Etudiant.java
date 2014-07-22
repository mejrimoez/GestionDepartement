/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author moez
 */
@Entity
@Table(name = "Etudiant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etudiant.findAll", query = "SELECT e FROM Etudiant e"),
    @NamedQuery(name = "Etudiant.findById", query = "SELECT e FROM Etudiant e WHERE e.id = :id"),
    @NamedQuery(name = "Etudiant.findByCin", query = "SELECT e FROM Etudiant e WHERE e.cin = :cin"),
    @NamedQuery(name = "Etudiant.findByNomprenom", query = "SELECT e FROM Etudiant e WHERE e.nomprenom = :nomprenom"),
    @NamedQuery(name = "Etudiant.findByEmail", query = "SELECT e FROM Etudiant e WHERE e.email = :email"),
    @NamedQuery(name = "Etudiant.findByDatenaissance", query = "SELECT e FROM Etudiant e WHERE e.datenaissance = :datenaissance"),
    @NamedQuery(name = "Etudiant.findByIns", query = "SELECT e FROM Etudiant e WHERE e.ins = :ins")})
public class Etudiant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 254)
    @Column(name = "cin")
    private String cin;
    @Size(max = 254)
    @Column(name = "nomprenom")
    private String nomprenom;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 254)
    @Column(name = "email")
    private String email;
    @Column(name = "datenaissance")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datenaissance;
    @Size(max = 254)
    @Column(name = "ins")
    private String ins;
    @OneToMany(mappedBy = "etuid2")
    private List<Projet> projetList;
    @OneToMany(mappedBy = "etuid")
    private List<Projet> projetList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etudiant")
    private List<Choixprojetpropose> choixprojetproposeList;
    @JoinColumn(name = "Sec_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Section secid;
    @JoinColumn(name = "Spe_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Specialite speid;
    @JoinColumn(name = "Ann_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Anneeuniversitaire annid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etudiant")
    private List<Choixenseignant> choixenseignantList;
    @OneToMany(mappedBy = "etuid")
    private List<Proposeprojet> proposeprojetList;
    @OneToMany(mappedBy = "etuid2")
    private List<Proposeprojet> proposeprojetList1;

    public Etudiant() {
    }

    public Etudiant(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNomprenom() {
        return nomprenom;
    }

    public void setNomprenom(String nomprenom) {
        this.nomprenom = nomprenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getIns() {
        return ins;
    }

    public void setIns(String ins) {
        this.ins = ins;
    }

    @XmlTransient
    public List<Projet> getProjetList() {
        return projetList;
    }

    public void setProjetList(List<Projet> projetList) {
        this.projetList = projetList;
    }

    @XmlTransient
    public List<Projet> getProjetList1() {
        return projetList1;
    }

    public void setProjetList1(List<Projet> projetList1) {
        this.projetList1 = projetList1;
    }

    @XmlTransient
    public List<Choixprojetpropose> getChoixprojetproposeList() {
        return choixprojetproposeList;
    }

    public void setChoixprojetproposeList(List<Choixprojetpropose> choixprojetproposeList) {
        this.choixprojetproposeList = choixprojetproposeList;
    }

    public Section getSecid() {
        return secid;
    }

    public void setSecid(Section secid) {
        this.secid = secid;
    }

    public Specialite getSpeid() {
        return speid;
    }

    public void setSpeid(Specialite speid) {
        this.speid = speid;
    }

    public Anneeuniversitaire getAnnid() {
        return annid;
    }

    public void setAnnid(Anneeuniversitaire annid) {
        this.annid = annid;
    }

    @XmlTransient
    public List<Choixenseignant> getChoixenseignantList() {
        return choixenseignantList;
    }

    public void setChoixenseignantList(List<Choixenseignant> choixenseignantList) {
        this.choixenseignantList = choixenseignantList;
    }

    @XmlTransient
    public List<Proposeprojet> getProposeprojetList() {
        return proposeprojetList;
    }

    public void setProposeprojetList(List<Proposeprojet> proposeprojetList) {
        this.proposeprojetList = proposeprojetList;
    }

    @XmlTransient
    public List<Proposeprojet> getProposeprojetList1() {
        return proposeprojetList1;
    }

    public void setProposeprojetList1(List<Proposeprojet> proposeprojetList1) {
        this.proposeprojetList1 = proposeprojetList1;
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
        if (!(object instanceof Etudiant)) {
            return false;
        }
        Etudiant other = (Etudiant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rnu.fst.gestiondedepatement.entity.Etudiant[ id=" + id + " ]";
    }
    
}
