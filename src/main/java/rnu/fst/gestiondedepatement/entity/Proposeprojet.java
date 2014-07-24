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
@Table(name = "Proposeprojet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proposeprojet.findAll", query = "SELECT p FROM Proposeprojet p"),
    @NamedQuery(name = "Proposeprojet.findById", query = "SELECT p FROM Proposeprojet p WHERE p.id = :id"),
    @NamedQuery(name = "Proposeprojet.findByNomProjet", query = "SELECT p FROM Proposeprojet p WHERE p.nomProjet = :nomProjet"),
    @NamedQuery(name = "Proposeprojet.findByDescription", query = "SELECT p FROM Proposeprojet p WHERE p.description = :description"),
    @NamedQuery(name = "Proposeprojet.findByRaisonsociale", query = "SELECT p FROM Proposeprojet p WHERE p.raisonsociale = :raisonsociale"),
    @NamedQuery(name = "Proposeprojet.findByResponsablesociete", query = "SELECT p FROM Proposeprojet p WHERE p.responsablesociete = :responsablesociete"),
    @NamedQuery(name = "Proposeprojet.findByAdressesociete", query = "SELECT p FROM Proposeprojet p WHERE p.adressesociete = :adressesociete"),
    @NamedQuery(name = "Proposeprojet.findByTelsociete", query = "SELECT p FROM Proposeprojet p WHERE p.telsociete = :telsociete"),
    @NamedQuery(name = "Proposeprojet.findByEncadreursociete", query = "SELECT p FROM Proposeprojet p WHERE p.encadreursociete = :encadreursociete"),
    @NamedQuery(name = "Proposeprojet.findByFonctionencadrant", query = "SELECT p FROM Proposeprojet p WHERE p.fonctionencadrant = :fonctionencadrant"),
    @NamedQuery(name = "Proposeprojet.findByEmailencadrant", query = "SELECT p FROM Proposeprojet p WHERE p.emailencadrant = :emailencadrant"),
    @NamedQuery(name = "Proposeprojet.findByTelencadrant", query = "SELECT p FROM Proposeprojet p WHERE p.telencadrant = :telencadrant"),
    @NamedQuery(name = "Proposeprojet.findByMethodeconception", query = "SELECT p FROM Proposeprojet p WHERE p.methodeconception = :methodeconception"),
    @NamedQuery(name = "Proposeprojet.findByLangeprog", query = "SELECT p FROM Proposeprojet p WHERE p.langeprog = :langeprog"),
    @NamedQuery(name = "Proposeprojet.findByOutilprog", query = "SELECT p FROM Proposeprojet p WHERE p.outilprog = :outilprog"),
    @NamedQuery(name = "Proposeprojet.findByRemuneration", query = "SELECT p FROM Proposeprojet p WHERE p.remuneration = :remuneration"),
    @NamedQuery(name = "Proposeprojet.findByDatepropose", query = "SELECT p FROM Proposeprojet p WHERE p.datepropose = :datepropose"),
    @NamedQuery(name = "Proposeprojet.findByProposepar", query = "SELECT p FROM Proposeprojet p WHERE p.proposepar = :proposepar")})
public class Proposeprojet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 254)
    @Column(name = "nomProjet")
    private String nomProjet;
    @Size(max = 254)
    @Column(name = "description")
    private String description;
    @Size(max = 254)
    @Column(name = "raisonsociale")
    private String raisonsociale;
    @Size(max = 254)
    @Column(name = "responsablesociete")
    private String responsablesociete;
    @Size(max = 254)
    @Column(name = "adressesociete")
    private String adressesociete;
    @Size(max = 254)
    @Column(name = "telsociete")
    private String telsociete;
    @Size(max = 254)
    @Column(name = "encadreursociete")
    private String encadreursociete;
    @Size(max = 254)
    @Column(name = "fonctionencadrant")
    private String fonctionencadrant;
    @Size(max = 254)
    @Column(name = "emailencadrant")
    private String emailencadrant;
    @Size(max = 254)
    @Column(name = "telencadrant")
    private String telencadrant;
    @Size(max = 254)
    @Column(name = "methodeconception")
    private String methodeconception;
    @Size(max = 254)
    @Column(name = "langeprog")
    private String langeprog;
    @Size(max = 254)
    @Column(name = "outilprog")
    private String outilprog;
    @Size(max = 254)
    @Column(name = "remuneration")
    private String remuneration;
    @Column(name = "datepropose")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datepropose;
    @Size(max = 254)
    @Column(name = "proposepar")
    private String proposepar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proposeprojet")
    private List<Choixprojetpropose> choixprojetproposeList;
    @JoinColumn(name = "Spe_id", referencedColumnName = "id")
    @ManyToOne
    private Specialite speid;
    @JoinColumn(name = "Ann_id", referencedColumnName = "id")
    @ManyToOne
    private Anneeuniversitaire annid;
    @JoinColumn(name = "Sec_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Section secid;
    @JoinColumn(name = "Etu_id", referencedColumnName = "id")
    @ManyToOne
    private Etudiant etuid;
    @JoinColumn(name = "Etu_id2", referencedColumnName = "id")
    @ManyToOne
    private Etudiant etuid2;
    @JoinColumn(name = "cin", referencedColumnName = "cin")
    @ManyToOne
    private Enseignant cin;

    public Proposeprojet() {
    }

    public Proposeprojet(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRaisonsociale() {
        return raisonsociale;
    }

    public void setRaisonsociale(String raisonsociale) {
        this.raisonsociale = raisonsociale;
    }

    public String getResponsablesociete() {
        return responsablesociete;
    }

    public void setResponsablesociete(String responsablesociete) {
        this.responsablesociete = responsablesociete;
    }

    public String getAdressesociete() {
        return adressesociete;
    }

    public void setAdressesociete(String adressesociete) {
        this.adressesociete = adressesociete;
    }

    public String getTelsociete() {
        return telsociete;
    }

    public void setTelsociete(String telsociete) {
        this.telsociete = telsociete;
    }

    public String getEncadreursociete() {
        return encadreursociete;
    }

    public void setEncadreursociete(String encadreursociete) {
        this.encadreursociete = encadreursociete;
    }

    public String getFonctionencadrant() {
        return fonctionencadrant;
    }

    public void setFonctionencadrant(String fonctionencadrant) {
        this.fonctionencadrant = fonctionencadrant;
    }

    public String getEmailencadrant() {
        return emailencadrant;
    }

    public void setEmailencadrant(String emailencadrant) {
        this.emailencadrant = emailencadrant;
    }

    public String getTelencadrant() {
        return telencadrant;
    }

    public void setTelencadrant(String telencadrant) {
        this.telencadrant = telencadrant;
    }

    public String getMethodeconception() {
        return methodeconception;
    }

    public void setMethodeconception(String methodeconception) {
        this.methodeconception = methodeconception;
    }

    public String getLangeprog() {
        return langeprog;
    }

    public void setLangeprog(String langeprog) {
        this.langeprog = langeprog;
    }

    public String getOutilprog() {
        return outilprog;
    }

    public void setOutilprog(String outilprog) {
        this.outilprog = outilprog;
    }

    public String getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(String remuneration) {
        this.remuneration = remuneration;
    }

    public Date getDatepropose() {
        return datepropose;
    }

    public void setDatepropose(Date datepropose) {
        this.datepropose = datepropose;
    }

    public String getProposepar() {
        return proposepar;
    }

    public void setProposepar(String proposepar) {
        this.proposepar = proposepar.toUpperCase();
    }

    @XmlTransient
    public List<Choixprojetpropose> getChoixprojetproposeList() {
        return choixprojetproposeList;
    }

    public void setChoixprojetproposeList(List<Choixprojetpropose> choixprojetproposeList) {
        this.choixprojetproposeList = choixprojetproposeList;
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

    public Section getSecid() {
        return secid;
    }

    public void setSecid(Section secid) {
        this.secid = secid;
    }

    public Etudiant getEtuid() {
        return etuid;
    }

    public void setEtuid(Etudiant etuid) {
        this.etuid = etuid;
    }

    public Etudiant getEtuid2() {
        return etuid2;
    }

    public void setEtuid2(Etudiant etuid2) {
        this.etuid2 = etuid2;
    }

    public Enseignant getCin() {
        return cin;
    }

    public void setCin(Enseignant cin) {
        this.cin = cin;
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
        if (!(object instanceof Proposeprojet)) {
            return false;
        }
        Proposeprojet other = (Proposeprojet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomProjet;
    }
    
}
